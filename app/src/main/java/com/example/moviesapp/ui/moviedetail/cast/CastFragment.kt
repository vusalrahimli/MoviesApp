package com.example.moviesapp.ui.moviedetail.cast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.databinding.FragmentCastBinding
import com.example.moviesapp.ui.moviedetail.DetailFragmentDirections
import com.example.moviesapp.ui.moviedetail.cast.adapters.CastAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CastFragment : Fragment() {

    private val binding by lazy {
        FragmentCastBinding.inflate(layoutInflater)
    }

    private var movieID: Int = 0

    private val viewModel by viewModels<CastVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieID = arguments?.getInt(MOVIE_ID.toString()) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovieCast()
        setCLicks()
    }

    private fun getMovieCast() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(500)
            movieID.let { id ->
                viewModel.getMovieCredits(id)
                viewModel.listMovieCredits
                    .asLiveData()
                    .observe(viewLifecycleOwner) {
                        adapter.submitList(it)
                    }
            }
        }
    }

    private fun setCLicks() {
        adapter.setOnItemClick = {
            findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToPersonDetails(it),
            )
        }
    }

    private val adapter by lazy {
        CastAdapter().also {
            binding.rvCast.adapter = it
        }
    }

    companion object {
        const val MOVIE_ID = 0
    }
}
