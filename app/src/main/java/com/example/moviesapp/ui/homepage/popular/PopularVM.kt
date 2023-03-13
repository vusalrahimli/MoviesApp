package com.example.moviesapp.ui.homepage.popular

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.dto.homepagedto.PopularDto
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
class PopularVM @Inject constructor(
    private val app: Application,
    private val useCases: UseCases,
) : AndroidViewModel(app) {

    private val _listPopular = MutableStateFlow(emptyList<PopularDto>())
    val listPopular = _listPopular.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
    }

    fun getPopular() = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        _isLoading.emit(true)
        when (val response = useCases.getPopularUseCase.execute()) {
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
                    useCases.deletePopularTableUseCase.execute()
                    _listPopular.emit(it.map { model -> model.toDto() })
                    useCases.insertPopularListUseCase.execute(it.map { model -> model.toDto() })
                    _isLoading.emit(false)
                }
            }
            is Resource.Error -> {
                _isLoading.emit(false)
            }
        }
    }

    fun getLocalList(): LiveData<List<PopularDto>> =
        useCases.getAllPopularUseCase.execute()

    fun insertMovieToWatchList(watchListModel: WatchListModel) {
        viewModelScope.launch {
            useCases.insertWatchListMoviesUseCase.execute(watchListModel)
        }
    }

    fun updateMovieModel(dtoPopular: PopularDto) {
        viewModelScope.launch {
            useCases.updateWatchListStatusUseCase.execute(dtoPopular)
        }
    }

    fun removeWatchListMovie(watchListModel: WatchListModel) =
        viewModelScope.launch {
            useCases.removeWatchListMovieUseCase.execute(watchListModel)
        }

    fun createWatchListModel(movie: PopularDto): WatchListModel {
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
        dtoPopular: PopularDto,
        isSaved: Boolean,
    ): PopularDto {
        if (isSaved) {
            return PopularDto(
                dtoPopular.backDropPath,
                dtoPopular.id,
                dtoPopular.overview,
                dtoPopular.popularity,
                dtoPopular.posterPath,
                dtoPopular.releaseDate,
                dtoPopular.title,
                dtoPopular.rating,
                false,
            )
        } else {
            return PopularDto(
                dtoPopular.backDropPath,
                dtoPopular.id,
                dtoPopular.overview,
                dtoPopular.popularity,
                dtoPopular.posterPath,
                dtoPopular.releaseDate,
                dtoPopular.title,
                dtoPopular.rating,
                true,
            )
        }
    }
}
