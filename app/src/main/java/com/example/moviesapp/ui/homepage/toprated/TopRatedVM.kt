package com.example.moviesapp.ui.homepage.toprated

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto
import com.example.moviesapp.data.model.mapper.toDto
import com.example.moviesapp.data.model.watchlist.WatchListModel
import com.example.moviesapp.domain.usecase.UseCases
import com.example.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedVM @Inject constructor(
    private val app: Application,
    private val useCases: UseCases,
) : AndroidViewModel(app) {

    private val _listTopRated = MutableStateFlow(emptyList<TopRatedDto>())
    val listTopRated = _listTopRated.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
    }

    fun getTopRated() = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        _isLoading.emit(true)
        when (val response = useCases.getTopRatedUseCase.execute()) {
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
                    useCases.deleteTopRatedTableUseCase.execute()
                    _listTopRated.emit(it.map { model -> model.toDto() })
                    useCases.insertTopRatedListUseCase.execute(it.map { model -> model.toDto() })
                    _isLoading.emit(false)
                }
            }
            is Resource.Error -> {
                _isLoading.emit(false)
            }
        }
    }

    fun getLocalList(): LiveData<List<TopRatedDto>> =
        useCases.getAllTopRatedUseCase.execute()

    fun insertMovieToWatchList(watchListModel: WatchListModel) {
        viewModelScope.launch {
            useCases.insertWatchListMoviesUseCase.execute(watchListModel)
        }
    }

    fun updateMovieModel(dtoTopRated: TopRatedDto) {
        viewModelScope.launch {
            useCases.updateWatchListStatusUseCase.execute(dtoTopRated)
        }
    }

    fun removeWatchListMovie(watchListModel: WatchListModel) =
        viewModelScope.launch {
            useCases.removeWatchListMovieUseCase.execute(watchListModel)
        }

    fun createWatchListModel(movie: TopRatedDto): WatchListModel {
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
        dtoTopRated: TopRatedDto,
        isSaved: Boolean,
    ): TopRatedDto {
        if (isSaved) {
            return TopRatedDto(
                dtoTopRated.backDropPath,
                dtoTopRated.id,
                dtoTopRated.overview,
                dtoTopRated.popularity,
                dtoTopRated.posterPath,
                dtoTopRated.releaseDate,
                dtoTopRated.title,
                dtoTopRated.rating,
                false,
            )
        } else {
            return TopRatedDto(
                dtoTopRated.backDropPath,
                dtoTopRated.id,
                dtoTopRated.overview,
                dtoTopRated.popularity,
                dtoTopRated.posterPath,
                dtoTopRated.releaseDate,
                dtoTopRated.title,
                dtoTopRated.rating,
                true,
            )
        }
    }
}
