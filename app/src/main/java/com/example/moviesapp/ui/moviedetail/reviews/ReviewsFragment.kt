package com.example.moviesapp.ui.moviedetail.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.databinding.FragmentReviewsBinding
import com.example.moviesapp.ui.moviedetail.reviews.adapters.ReviewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReviewsFragment : Fragment() {

    private val binding by lazy {
        FragmentReviewsBinding.inflate(layoutInflater)
    }

    private var movieId: Int = 0

    private val viewModel by viewModels<MovieReviewsVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = arguments?.getInt(MOVIE_ID.toString()) ?: 0
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
        getMovieReviews()
        reviewOnClick()
    }

    private fun getMovieReviews() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(300)
            movieId.let { id ->
                viewModel.getMovieReviews(id)
                viewModel.listMovieReviews
                    .asLiveData()
                    .observe(viewLifecycleOwner) {
                        adapter.submitList(it)
                    }
            }
        }
    }

    private fun reviewOnClick() {
        adapter.setOnItemClick = {
            findNavController().navigate(
                ReviewsFragmentDirections.actionReviewsFragmentToReviewBottomSheet(it),
            )
        }
    }

    private val adapter by lazy {
        ReviewsAdapter().also {
            binding.rvReview.adapter = it
        }
    }

    companion object {
        const val MOVIE_ID = 0
    }
}
