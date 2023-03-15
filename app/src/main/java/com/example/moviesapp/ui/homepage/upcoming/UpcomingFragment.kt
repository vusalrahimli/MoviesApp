package com.example.moviesapp.ui.homepage.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.databinding.FragmentUpcomingBinding
import com.example.moviesapp.ui.homepage.HomePageFragmentDirections
import com.example.moviesapp.ui.homepage.upcoming.adapters.UpcomingAdapter
import com.example.moviesapp.util.PopUps
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpcomingFragment : Fragment() {

    private val binding by lazy {
        FragmentUpcomingBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<UpcomingVM>()

    private val progressBar by lazy { PopUps.progressDialog(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservables()
        setClicks()
    }

    private fun subscribeToObservables() {
        viewModel.getLocalList().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
            } else {
                viewModel.getUpcoming()
            }
        }

        viewModel.listUpcoming
            .asLiveData()
            .observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }

        viewModel.isLoading
            .asLiveData()
            .observe(viewLifecycleOwner) {
                if (it) {
                    progressBar.show()
                } else {
                    progressBar.dismiss()
                }
            }
    }

    private fun setClicks() {
        adapter.setOnItemClick = {
            findNavController().navigate(
                HomePageFragmentDirections.actionUpcomingFragmentToDetails(
                    null,
                    it,
                    null,
                    null,
                    null,
                ),
            )
        }

        adapter.setOnSaveClick = { movie ->

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.updateMovieModel(
                    viewModel.setWatchListStatus(
                        movie,
                        movie.isSaved,
                    ),
                )

                if (!movie.isSaved) {
                    viewModel.insertMovieToWatchList(
                        viewModel.createWatchListModel(
                            viewModel.setWatchListStatus(
                                movie,
                                movie.isSaved,
                            ),
                        ),
                    )

                    PopUps.customSnackBar("Added To Watch List", requireView())
                } else {
                    viewModel.removeWatchListMovie(
                        viewModel.createWatchListModel(
                            viewModel.setWatchListStatus(
                                movie,
                                movie.isSaved,
                            ),
                        ),
                    )
                    PopUps.customSnackBar("Removed From Watch List", requireView())
                }
            }
        }
    }

    private val adapter by lazy {
        UpcomingAdapter().also {
            binding.rvUpcoming.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rvUpcoming.adapter = it
        }
    }
}
