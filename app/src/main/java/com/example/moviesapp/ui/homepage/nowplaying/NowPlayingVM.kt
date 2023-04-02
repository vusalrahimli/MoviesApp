package com.example.moviesapp.ui.homepage.nowplaying

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.data.model.mapper.toDto
import com.example.moviesapp.data.model.watchlist.WatchListModel
import com.example.moviesapp.domain.usecase.UseCases
import com.example.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NowPlayingVM @Inject constructor(
    private val app: Application,
    private val useCases: UseCases,
) : AndroidViewModel(app) {

    private val _listNowPlaying = MutableStateFlow<List<NowPlayingDto>>(emptyList())
    val listNowPlaying = _listNowPlaying.asStateFlow()

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage = _errorMessage.asSharedFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
    }

    fun getNowPlaying() = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        _isLoading.emit(true)
        when (val response = useCases.getNowPlayingUseCase.execute()) {
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
                    useCases.deleteNowPlayingTableUseCase.execute()
                    _listNowPlaying.emit(it.map { model -> model.toDto() })
                    useCases.insertNowPlayingListUseCase.execute(it.map { model -> model.toDto() })
                    _isLoading.emit(false)
                }
            }
            is Resource.Error -> {
                _isLoading.emit(false)
            }
        }
    }

    fun getLocalList(): LiveData<List<NowPlayingDto>> =
        useCases.getAllNowPlayingUseCase.execute()

    fun insertMovieToWatchList(watchListModel: WatchListModel) {
        viewModelScope.launch {
            useCases.insertWatchListMoviesUseCase.execute(watchListModel)
        }
    }

    fun updateMovieModel(dtoNP: NowPlayingDto) {
        viewModelScope.launch {
            useCases.updateWatchListStatusUseCase.execute(dtoNP)
        }
    }

    fun removeWatchListMovie(watchListModel: WatchListModel) =
        viewModelScope.launch {
            useCases.removeWatchListMovieUseCase.execute(watchListModel)
        }

    fun createWatchListModel(movie: NowPlayingDto): WatchListModel {
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
        dtoNP: NowPlayingDto,
        isSaved: Boolean,
    ): NowPlayingDto {
        if (isSaved) {
            return NowPlayingDto(
                dtoNP.backDropPath,
                dtoNP.id,
                dtoNP.overview,
                dtoNP.popularity,
                dtoNP.posterPath,
                dtoNP.releaseDate,
                dtoNP.title,
                dtoNP.rating,
                false,
            )
        } else {
            return NowPlayingDto(
                dtoNP.backDropPath,
                dtoNP.id,
                dtoNP.overview,
                dtoNP.popularity,
                dtoNP.posterPath,
                dtoNP.releaseDate,
                dtoNP.title,
                dtoNP.rating,
                true,
            )
        }
    }
}
