package com.example.moviesapp.ui.moviedetail.reviews

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.dto.moviedetaildto.MovieReviewsDto
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
class MovieReviewsVM @Inject constructor(
    private val app: Application,
    private val useCases: UseCases,
) : AndroidViewModel(app) {

    private val _listMovieReviews = MutableStateFlow(emptyList<MovieReviewsDto>())
    val listMovieReviews = _listMovieReviews.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
    }

    fun getMovieReviews(movieID: Int) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            when (val response = useCases.getMovieReviewsUseCase.execute(movieID)) {
                is Resource.Success -> {
                    response.data?.let {
                        _listMovieReviews.emit(it.map { model -> model.toDto() })
                    }
                }
                else -> {}
            }
        }
}
