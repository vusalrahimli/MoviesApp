package com.example.moviesapp.ui.watchlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.data.model.dto.homepagedto.PopularDto
import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto
import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto
import com.example.moviesapp.data.model.watchlist.WatchListModel
import com.example.moviesapp.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchListVM @Inject constructor(
    private val app: Application,
    private val useCases: UseCases,
) : AndroidViewModel(app) {

    fun getWatchListMovies(): LiveData<List<WatchListModel>> =
        useCases.getWatchListMoviesUseCase.execute()

    suspend fun removeWatchListMovie(watchListModel: WatchListModel) =
        useCases.removeWatchListMovieUseCase.execute(watchListModel)

    suspend fun updateNowPlayingModel(movieModel: NowPlayingDto) =
        useCases.updateWatchListStatusUseCase.execute(movieModel)

    suspend fun updateUpcomingModel(movieModel: UpcomingDto) =
        useCases.updateWatchListStatusUseCase.execute(movieModel)

    suspend fun updateTopRatedModel(movieModel: TopRatedDto) =
        useCases.updateWatchListStatusUseCase.execute(movieModel)

    suspend fun updatePopularModel(movieModel: PopularDto) =
        useCases.updateWatchListStatusUseCase.execute(movieModel)
}
