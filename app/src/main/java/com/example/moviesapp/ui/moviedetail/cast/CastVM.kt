package com.example.moviesapp.ui.moviedetail.cast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.dto.moviedetaildto.MovieCastDto
import com.example.moviesapp.data.model.mapper.toDto
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
class CastVM @Inject constructor(
    private val app: Application,
    private val useCases: UseCases,
) : AndroidViewModel(app) {

    private val _listMovieCredits = MutableStateFlow(emptyList<MovieCastDto>())
    val listMovieCredits = _listMovieCredits.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
    }

    fun getMovieCredits(movieID: Int) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            when (val response = useCases.getMovieCreditsUseCase.execute(movieID)) {
                is Resource.Success -> {
                    response.data?.let {
                        _listMovieCredits.emit(it.map { model -> model.toDto() })
                    }
                }
                else -> {}
            }
        }
}
