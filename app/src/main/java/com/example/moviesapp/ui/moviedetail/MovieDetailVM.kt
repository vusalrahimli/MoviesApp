package com.example.moviesapp.ui.moviedetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.data.model.dto.homepagedto.PopularDto
import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto
import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto
import com.example.moviesapp.data.model.dto.moviedetaildto.MovieGenresDto
import com.example.moviesapp.data.model.dto.moviedetaildto.MovieTrailerDto
import com.example.moviesapp.data.model.mapper.toDto
import com.example.moviesapp.data.model.moviedetailspage.moviedetails.MovieDetailsResponse
import com.example.moviesapp.data.model.watchlist.WatchListModel
import com.example.moviesapp.domain.usecase.UseCases
import com.example.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailVM @Inject constructor(
    private val app: Application,
    private val useCases: UseCases,
) : AndroidViewModel(app) {

    sealed class Event {

        class TrailerSuccess(
            val trailerList: List<MovieTrailerDto>?,
        ) : Event()

        class GenresSuccess(
            val genresList: List<MovieGenresDto>?,
        ) : Event()

        class DurationSuccess(
            val durationList: MovieDetailsResponse?,
        ) : Event()

        object Loading : Event()
    }

    private val trailerChannel = Channel<Event>()
    val trailerFlow = trailerChannel.receiveAsFlow()

    private val genresChannel = Channel<Event>()
    val genresFlow = genresChannel.receiveAsFlow()

    private val durationChannel = Channel<Event>()
    val durationFLow = durationChannel.receiveAsFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
    }

    fun getMovieTrailer(movieID: Int) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            when (val response = useCases.getMovieTrailerUseCase.execute(movieID)) {
                is Resource.Success -> {
                    response.data?.let {
                        trailerChannel.send(Event.TrailerSuccess(it.map { model -> model.toDto() }))
                    }
                }
                else -> {}
            }
        }

    fun getMovieGenres(movieID: Int) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            when (val response = useCases.getMovieGenresUseCase.execute(movieID)) {
                is Resource.Success -> {
                    response.data?.let {
                        genresChannel.send(Event.GenresSuccess(it.map { model -> model.toDto() }))
                    }
                }
                else -> {}
            }
        }

    fun getMovieDuration(movieID: Int) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            when (val response = useCases.getMovieDurationUseCase.execute(movieID)) {
                is Resource.Success -> {
                    response.data?.let {
                        durationChannel.send(Event.DurationSuccess(it))
                    }
                }
                else -> {}
            }
        }

    suspend fun updateNowPlayingModel(movieModel: NowPlayingDto) =
        useCases.updateWatchListStatusUseCase.execute(movieModel)

    suspend fun updateUpcomingModel(movieModel: UpcomingDto) =
        useCases.updateWatchListStatusUseCase.execute(movieModel)

    suspend fun updateTopRatedModel(movieModel: TopRatedDto) =
        useCases.updateWatchListStatusUseCase.execute(movieModel)

    suspend fun updatePopularModel(movieModel: PopularDto) =
        useCases.updateWatchListStatusUseCase.execute(movieModel)

    suspend fun removeWatchListMovie(watchListModel: WatchListModel) =
        useCases.removeWatchListMovieUseCase.execute(watchListModel)

    fun insertMovieToWatchList(watchListModel: WatchListModel) {
        viewModelScope.launch {
            useCases.insertWatchListMoviesUseCase.execute(watchListModel)
        }
    }

    suspend fun getWatchListMovieByID(movieId: Int): WatchListModel? =
        useCases.getWatchListMovieByIdUseCase.execute(movieId)

    fun createWatchListModelFromNowPlaying(movie: NowPlayingDto): WatchListModel {
        val movieReleaseDate = movie.releaseDate?.trim()?.subSequence(0, 4).toString()
        return WatchListModel(
            movie.id,
            movie.backDropPath,
            movie.overview,
            movie.posterPath,
            movieReleaseDate,
            movie.title,
            movie.rating,
            movie.isSaved,
        )
    }

    fun createWatchListModelFromUpcoming(movie: UpcomingDto): WatchListModel {
        val movieReleaseDate = movie.releaseDate?.trim()?.subSequence(0, 4).toString()
        return WatchListModel(
            movie.id,
            movie.backDropPath,
            movie.overview,
            movie.posterPath,
            movieReleaseDate,
            movie.title,
            movie.rating,
            movie.isSaved,
        )
    }

    fun createWatchListModelFromTopRated(movie: TopRatedDto): WatchListModel {
        val movieReleaseDate = movie.releaseDate?.trim()?.subSequence(0, 4).toString()
        return WatchListModel(
            movie.id,
            movie.backDropPath,
            movie.overview,
            movie.posterPath,
            movieReleaseDate,
            movie.title,
            movie.rating,
            movie.isSaved,
        )
    }

    fun createWatchListModelFromPopular(movie: PopularDto): WatchListModel {
        val movieReleaseDate = movie.releaseDate?.trim()?.subSequence(0, 4).toString()
        return WatchListModel(
            movie.id,
            movie.backDropPath,
            movie.overview,
            movie.posterPath,
            movieReleaseDate,
            movie.title,
            movie.rating,
            movie.isSaved,
        )
    }
}
