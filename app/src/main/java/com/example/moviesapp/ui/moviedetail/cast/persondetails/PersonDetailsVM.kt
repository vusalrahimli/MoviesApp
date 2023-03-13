package com.example.moviesapp.ui.moviedetail.cast.persondetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.moviedetailspage.cast.persondetails.PersonDetailsResponse
import com.example.moviesapp.domain.usecase.UseCases
import com.example.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsVM @Inject constructor(
    private val app: Application,
    private val useCases: UseCases,
) : AndroidViewModel(app) {

    sealed class Event {
        class PersonDetailsSuccess(
            val details: PersonDetailsResponse?,
        ) : Event()
    }

    private val personDetailsChannel = Channel<Event>()
    val personDetailsFlow = personDetailsChannel.receiveAsFlow()

    fun getPersonDetails(personID: Int) = viewModelScope.launch {
        when (val response = useCases.getPersonDetailsUseCase.execute(personID)) {
            is Resource.Success -> {
                response.data?.let {
                    personDetailsChannel.send(Event.PersonDetailsSuccess(it))
                }
            }
            else -> {}
        }
    }
}
