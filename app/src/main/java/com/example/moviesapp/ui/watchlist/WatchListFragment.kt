package com.example.moviesapp.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.data.model.dto.homepagedto.PopularDto
import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto
import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto
import com.example.moviesapp.data.model.watchlist.WatchListModel
import com.example.moviesapp.databinding.WatchListFragmentBinding
import com.example.moviesapp.ui.watchlist.adapters.WatchListAdapter
import com.example.moviesapp.util.PopUps
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WatchListFragment : Fragment() {

    private val binding by lazy {
        WatchListFragmentBinding.inflate(layoutInflater)
    }

    private var removeAlertDialog: AlertDialog.Builder? = null

    private val viewModel by viewModels<WatchListVM>()

    private val adapter by lazy {
        WatchListAdapter().also {
            binding.rvWatchList.adapter = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservables()
        setClicks()
    }

    private fun subscribeToObservables() {
        viewModel.getWatchListMovies()
            .observe(viewLifecycleOwner) { list ->
                binding.noWatchList.isVisible = false
                adapter.submitList(list)
                if (list.isNullOrEmpty()) {
                    binding.noWatchList.isVisible = true
                }
            }
    }

    private fun setClicks() {
        adapter.setOnItemClick = {
            findNavController().navigate(
                WatchListFragmentDirections.actionWatchListFragmentToDetails(
                    null,
                    null,
                    null,
                    null,
                    it,
                ),
            )
        }
        adapter.setOnUnSaveClick = { movie ->
            initRemoveDialog(movie)
        }
    }

    private fun initRemoveDialog(watchListModel: WatchListModel) {
        removeAlertDialog = PopUps.createAlertDialog(requireContext(), R.style.RemoveAlertDialog)

        removeAlertDialog.let { remove ->
            remove?.setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
            remove?.setTitle("Remove")
            remove?.setMessage("Are you sure you want to remove?")
            remove?.setPositiveButton("Yes") { _, _ ->
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.updateNowPlayingModel(castToNowPlayingModel(watchListModel))
                    viewModel.updateUpcomingModel(castToUpcomingModel(watchListModel))
                    viewModel.updateTopRatedModel(castToTopRatedModel(watchListModel))
                    viewModel.updatePopularModel(castToPopularModel(watchListModel))
                    viewModel.removeWatchListMovie(watchListModel)
                }
            }
            val alertDialog: AlertDialog = removeAlertDialog!!.create()
            alertDialog.show()
        }
    }

    private fun castToNowPlayingModel(movie: WatchListModel): NowPlayingDto {
        return NowPlayingDto(
            movie.backdrop_path!!,
            movie.id!!,
            movie.overview!!,
            0.0,
            movie.poster_path!!,
            movie.release_date!!,
            movie.title!!,
            movie.rating!!,
            false,
        )
    }

    private fun castToUpcomingModel(movie: WatchListModel): UpcomingDto {
        return UpcomingDto(
            movie.backdrop_path!!,
            movie.id!!,
            movie.overview!!,
            0.0,
            movie.poster_path!!,
            movie.release_date!!,
            movie.title!!,
            movie.rating!!,
            false,
        )
    }

    private fun castToTopRatedModel(movie: WatchListModel): TopRatedDto {
        return TopRatedDto(
            movie.backdrop_path!!,
            movie.id!!,
            movie.overview!!,
            0.0,
            movie.poster_path!!,
            movie.release_date!!,
            movie.title!!,
            movie.rating!!,
            false,
        )
    }

    private fun castToPopularModel(movie: WatchListModel): PopularDto {
        return PopularDto(
            movie.backdrop_path!!,
            movie.id!!,
            movie.overview!!,
            0.0,
            movie.poster_path!!,
            movie.release_date!!,
            movie.title!!,
            movie.rating!!,
            false,
        )
    }
}
