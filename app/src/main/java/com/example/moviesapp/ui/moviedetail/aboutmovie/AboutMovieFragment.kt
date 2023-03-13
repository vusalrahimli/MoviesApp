package com.example.moviesapp.ui.moviedetail.aboutmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviesapp.databinding.FragmentAboutMovieBinding

class AboutMovieFragment : Fragment() {

    private val binding by lazy {
        FragmentAboutMovieBinding.inflate(layoutInflater)
    }

    private var aboutMovie: String = ""
    private var rating: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        aboutMovie = arguments?.getString(ABOUT_MOVIE) ?: ""
        rating = arguments?.getString(RATING) ?: ""
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

        binding.materialTextView4.text = aboutMovie
        binding.tv.text = rating
    }

    companion object {
        const val ABOUT_MOVIE = "about_movie"
        const val RATING = "rating"
    }
}
