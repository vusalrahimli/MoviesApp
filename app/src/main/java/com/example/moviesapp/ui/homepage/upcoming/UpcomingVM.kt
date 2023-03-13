package com.example.moviesapp.ui.homepage.upcoming

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto
import com.example.moviesapp.data.model.mapper.toDto
import com.example.moviesapp.data.model.watchlist.WatchListModel
import com.example.moviesapp.domain.usecase.UseCases
import com.example.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingVM @Inject constructor(
    private val app: Application,
    private val useCases: UseCases,
) : AndroidViewModel(app) {

    private val _listUpcoming = MutableStateFlow(emptyList<UpcomingDto>())
    val listUpcoming = _listUpcoming.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
    }

    fun getUpcoming() = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        _isLoading.emit(true)
        when (val response = useCases.getUpcomingUseCase.execute()) {
            is Resource.Success -> {
                response.data?.let {
                    it.map { model ->
                        model.id.let { id ->
                            val savedModel = useCases.getWatchListMovieByIdUseCase.execute(id)
                            if (savedModel != null) {
                                model.isSaved = savedModel.isSaved!!
                                useCases.updateWatchListModelUseCase.execute(
                                    createWatchListModel(
                                        model.toDto(),
                                    ),
                                )
                            }
                        }
                    }
                    useCases.deleteUpcomingTableUseCase.execute()
                    _listUpcoming.emit(it.map { model -> model.toDto() })
                    useCases.insertUpcomingUseCase.execute(it.map { model -> model.toDto() })
                    _isLoading.emit(false)
                }
            }
            is Resource.Error -> {
                _isLoading.emit(false)
            }
        }
    }

    fun getLocalList(): LiveData<List<UpcomingDto>> =
        useCases.getAllUpcomingUseCase.execute()

    fun insertMovieToWatchList(watchListModel: WatchListModel) {
        viewModelScope.launch {
            useCases.insertWatchListMoviesUseCase.execute(watchListModel)
        }
    }

    fun updateMovieModel(dtoUpcoming: UpcomingDto) {
        viewModelScope.launch {
            useCases.updateWatchListStatusUseCase.execute(dtoUpcoming)
        }
    }

    fun removeWatchListMovie(watchListModel: WatchListModel) =
        viewModelScope.launch {
            useCases.removeWatchListMovieUseCase.execute(watchListModel)
        }

    fun createWatchListModel(movie: UpcomingDto): WatchListModel {
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

    fun setWatchListStatus(
        dtoUpcoming: UpcomingDto,
        isSaved: Boolean,
    ): UpcomingDto {
        if (isSaved) {
            return UpcomingDto(
                dtoUpcoming.backDropPath,
                dtoUpcoming.id,
                dtoUpcoming.overview,
                dtoUpcoming.popularity,
                dtoUpcoming.posterPath,
                dtoUpcoming.releaseDate,
                dtoUpcoming.title,
                dtoUpcoming.rating,
                false,
            )
        } else {
            return UpcomingDto(
                dtoUpcoming.backDropPath,
                dtoUpcoming.id,
                dtoUpcoming.overview,
                dtoUpcoming.popularity,
                dtoUpcoming.posterPath,
                dtoUpcoming.releaseDate,
                dtoUpcoming.title,
                dtoUpcoming.rating,
                true,
            )
        }
    }
}
