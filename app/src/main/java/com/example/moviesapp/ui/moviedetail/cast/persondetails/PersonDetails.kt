package com.example.moviesapp.ui.moviedetail.cast.persondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.moviesapp.data.model.dto.moviedetaildto.MovieCastDto
import com.example.moviesapp.databinding.FragmentPersonDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonDetails : Fragment() {

    private val binding by lazy {
        FragmentPersonDetailsBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<PersonDetailsVM>()

    private val args by navArgs<PersonDetailsArgs>()

    private var castDto: MovieCastDto? = null

    private var personID: Int? = 0

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
        castDto = args.castDto
        personID = castDto!!.id

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPersonDetails(personID!!)

            viewModel.personDetailsFlow.collect { event ->
                when (event) {
                    is PersonDetailsVM.Event.PersonDetailsSuccess -> {
                        event.details?.let { detail ->
                            binding.model = detail

                            if (detail.deathday != null) {
                                binding.date.text =
                                    "${detail.birthday} / ${detail.deathday}"
                            } else if (detail.birthday != null) {
                                binding.date.text =
                                    "${detail.birthday}"
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setClicks() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}
