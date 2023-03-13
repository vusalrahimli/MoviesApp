package com.example.moviesapp.ui.homepage.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.databinding.FragmentPopularBinding
import com.example.moviesapp.ui.homepage.HomePageFragmentDirections
import com.example.moviesapp.ui.homepage.popular.adapters.PopularAdapter
import com.example.moviesapp.util.PopUps
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularFragment : Fragment() {

    private val binding by lazy {
        FragmentPopularBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<PopularVM>()

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
                viewModel.getPopular()
            }
        }

        viewModel.listPopular
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
                HomePageFragmentDirections.actionPopularFragmentToDetails(
                    null,
                    null,
                    null,
                    it,
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
        PopularAdapter().also {
            binding.rvPopular.adapter = it
        }
    }
}
