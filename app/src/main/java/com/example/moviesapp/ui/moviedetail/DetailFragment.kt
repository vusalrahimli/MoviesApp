package com.example.moviesapp.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.moviesapp.R
import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.data.model.dto.homepagedto.PopularDto
import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto
import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto
import com.example.moviesapp.data.model.watchlist.WatchListModel
import com.example.moviesapp.databinding.FragmentMoviesDetail2Binding
import com.example.moviesapp.ui.moviedetail.aboutmovie.AboutMovieFragment
import com.example.moviesapp.ui.moviedetail.cast.CastFragment
import com.example.moviesapp.ui.moviedetail.reviews.ReviewsFragment
import com.example.moviesapp.util.setImage
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val binding by lazy {
        FragmentMoviesDetail2Binding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<MovieDetailVM>()

    private lateinit var youtubePlayerView: YouTubePlayerView
    private val fragments = ArrayList<Fragment>()
    private var youtubeKey: String = ""
    private val args by navArgs<DetailFragmentArgs>()

    private var movieModelNowPlaying: NowPlayingDto? = null

    private var movieModelUpcoming: UpcomingDto? = null

    private var movieModelTopRated: TopRatedDto? = null

    private var movieModelPopular: PopularDto? = null

    private var watchListModel: WatchListModel? = null

    private var movieID: Int? = 0

    private val adapterVP by lazy {
        DetailViewPagerAdapter(this, fragments).also {
            binding.viewpagerDetail.adapter = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservables()
        checkSaved()
        setBottomSheet()
        setClicks()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        loadTabLayout()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    private fun init() {
        movieModelNowPlaying = args.dtoNP
        movieModelUpcoming = args.upcomingDto
        movieModelTopRated = args.topratedDto
        movieModelPopular = args.popularDto
        watchListModel = args.watchListModel

        if (movieModelNowPlaying != null) {
            movieID = movieModelNowPlaying!!.id
            binding.textDetailTitle.text = movieModelNowPlaying?.title
            binding.materialTextView2.text =
                movieModelNowPlaying?.releaseDate?.trim()?.subSequence(0, 4)
        } else if (movieModelUpcoming != null) {
            movieID = movieModelUpcoming!!.id
            binding.textDetailTitle.text = movieModelUpcoming?.title
            binding.materialTextView2.text =
                movieModelUpcoming?.releaseDate?.trim()?.subSequence(0, 4)
        } else if (movieModelTopRated != null) {
            movieID = movieModelTopRated!!.id
            binding.textDetailTitle.text = movieModelTopRated?.title
            binding.materialTextView2.text =
                movieModelTopRated?.releaseDate?.trim()?.subSequence(0, 4)
        } else if (movieModelPopular != null) {
            movieID = movieModelPopular!!.id
            binding.textDetailTitle.text = movieModelPopular?.title
            binding.materialTextView2.text =
                movieModelPopular?.releaseDate?.trim()?.subSequence(0, 4)
        } else if (watchListModel != null) {
            movieID = watchListModel!!.id
            binding.textDetailTitle.text = watchListModel?.title
            binding.materialTextView2.text =
                watchListModel?.release_date?.trim()?.subSequence(0, 4)
        }
    }

    private fun setClicks() {
        binding.toolbarDetails.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.save.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                if (movieModelNowPlaying != null) {
                    clickFromNowPlaying(movieModelNowPlaying!!)
                } else if (movieModelUpcoming != null) {
                    clickFromUpcoming(movieModelUpcoming!!)
                } else if (movieModelTopRated != null) {
                    clickFromTopRated(movieModelTopRated!!)
                } else if (movieModelPopular != null) {
                    clickFromPopular(movieModelPopular!!)
                } else if (watchListModel != null) {
                    clickFromWatchList(watchListModel!!)
                }
            }
        }
    }

    private fun subscribeToObservables() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(500)
            movieID.let { id -> viewModel.getMovieTrailer(id!!) }
            viewModel.trailerFlow.collect { event ->
                when (event) {
                    is MovieDetailVM.Event.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is MovieDetailVM.Event.TrailerSuccess -> {
                        binding.progressBar.isVisible = false
                        event.trailerList?.let { trailerList ->
                            if (!trailerList.isNullOrEmpty()) {
                                youtubeKey = trailerList[0].key
                                setMovieTrailer(youtubeKey)
                            }
                        }
                    }
                    else -> {}
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            movieID.let { id -> viewModel.getMovieGenres(id!!) }
            viewModel.genresFlow.collect { event ->
                when (event) {
                    is MovieDetailVM.Event.GenresSuccess -> {
                        event.genresList?.let { genresList ->
                            if (!genresList.isNullOrEmpty()) {
                                binding.textGenre.text = genresList[0].genreName
                            }
                        }
                    }
                    else -> {}
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            movieID.let { id -> viewModel.getMovieDuration(id!!) }
            viewModel.durationFLow.collect { event ->
                when (event) {
                    is MovieDetailVM.Event.DurationSuccess -> {
                        event.durationList?.let { duration ->
                            binding.textDuration.text = "${duration.runtime} Minutes"
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    private fun loadTabLayout() {
        val aboutFragment = AboutMovieFragment().apply {
            Bundle().apply {
                if (movieModelNowPlaying != null) {
                    putString(AboutMovieFragment.ABOUT_MOVIE, movieModelNowPlaying?.overview)
                    putString(AboutMovieFragment.RATING, movieModelNowPlaying?.rating.toString())
                } else if (movieModelUpcoming != null) {
                    putString(AboutMovieFragment.ABOUT_MOVIE, movieModelUpcoming?.overview)
                    putString(AboutMovieFragment.RATING, movieModelUpcoming?.rating.toString())
                } else if (movieModelTopRated != null) {
                    putString(AboutMovieFragment.ABOUT_MOVIE, movieModelTopRated?.overview)
                    putString(AboutMovieFragment.RATING, movieModelTopRated?.rating.toString())
                } else if (movieModelPopular != null) {
                    putString(AboutMovieFragment.ABOUT_MOVIE, movieModelPopular?.overview)
                    putString(AboutMovieFragment.RATING, movieModelPopular?.rating.toString())
                } else if (watchListModel != null) {
                    putString(AboutMovieFragment.ABOUT_MOVIE, watchListModel?.overview)
                    putString(AboutMovieFragment.RATING, watchListModel?.rating.toString())
                }
            }.let {
                this.arguments = it
            }
        }

        val reviewsFragment = ReviewsFragment().apply {
            Bundle().apply {
                putInt(ReviewsFragment.MOVIE_ID.toString(), movieID ?: 0)
            }.let {
                this.arguments = it
            }
        }

        val castFragment = CastFragment().apply {
            Bundle().apply {
                putInt(CastFragment.MOVIE_ID.toString(), movieID ?: 0)
            }.let {
                this.arguments = it
            }
        }

        fragments.add(aboutFragment)
        fragments.add(reviewsFragment)
        fragments.add(castFragment)

        adapterVP

        TabLayoutMediator(binding.tabLayoutDetail, binding.viewpagerDetail) { tab, position ->
            tab.text = when (position) {
                0 -> "Detail"
                1 -> "Reviews"
                2 -> "Cast"
                else -> ""
            }
        }.attach()
    }

    private fun setMovieTrailer(youtubeKey: String?) {
        youtubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youtubePlayerView)
        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                if (youtubeKey != null) {
                    youTubePlayer.cueVideo(youtubeKey, 0f)
                }
            }
        })
    }

    private fun setBottomSheet() {
        val bottomSheet = binding.bottomSheet

        BottomSheetBehavior.from(bottomSheet).apply {
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    suspend fun clickFromNowPlaying(movieModelNowPlaying: NowPlayingDto) {
        if (movieModelNowPlaying.isSaved) { // true
            movieModelNowPlaying.isSaved = false
            setSaved(false)
            viewModel.updateNowPlayingModel(movieModelNowPlaying)
            viewModel.removeWatchListMovie(
                viewModel.createWatchListModelFromNowPlaying(
                    movieModelNowPlaying,
                ),
            )
            Toast.makeText(requireContext(), "Removed From Watch List", Toast.LENGTH_SHORT).show()
        } else if (!movieModelNowPlaying.isSaved) { // false

            movieModelNowPlaying.isSaved = true
            setSaved(true)
            viewModel.updateNowPlayingModel(movieModelNowPlaying)
            viewModel.insertMovieToWatchList(
                viewModel.createWatchListModelFromNowPlaying(
                    movieModelNowPlaying,
                ),
            )

            Toast.makeText(requireContext(), "Added To Watch List", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun clickFromUpcoming(movieModelUpcoming: UpcomingDto) {
        if (movieModelUpcoming.isSaved) { // true
            movieModelUpcoming.isSaved = false
            setSaved(false)
            viewModel.updateUpcomingModel(movieModelUpcoming)

            viewModel.removeWatchListMovie(
                viewModel.createWatchListModelFromUpcoming(
                    movieModelUpcoming,
                ),
            )

            Toast.makeText(requireContext(), "Removed From Watch List", Toast.LENGTH_SHORT).show()
        } else if (!movieModelUpcoming.isSaved) { // false
            movieModelUpcoming.isSaved = true
            setSaved(true)
            viewModel.updateUpcomingModel(movieModelUpcoming)
            viewModel.insertMovieToWatchList(
                viewModel.createWatchListModelFromUpcoming(
                    movieModelUpcoming,
                ),
            )

            Toast.makeText(requireContext(), "Added To Watch List", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun clickFromTopRated(movieModelTopRated: TopRatedDto) {
        if (movieModelTopRated.isSaved) { // true
            movieModelTopRated.isSaved = false
            setSaved(false)
            viewModel.updateTopRatedModel(movieModelTopRated)

            viewModel.removeWatchListMovie(
                viewModel.createWatchListModelFromTopRated(
                    movieModelTopRated,
                ),
            )
            Toast.makeText(requireContext(), "Removed From Watch List", Toast.LENGTH_SHORT).show()
        } else if (!movieModelTopRated.isSaved) { // false
            movieModelTopRated.isSaved = true
            setSaved(true)
            viewModel.updateTopRatedModel(movieModelTopRated)
            viewModel.insertMovieToWatchList(
                viewModel.createWatchListModelFromTopRated(
                    movieModelTopRated,
                ),
            )

            Toast.makeText(requireContext(), "Added To Watch List", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun clickFromPopular(movieModelPopular: PopularDto) {
        if (movieModelPopular.isSaved) { // true
            movieModelPopular.isSaved = false
            setSaved(false)
            viewModel.updatePopularModel(movieModelPopular)

            viewModel.removeWatchListMovie(
                viewModel.createWatchListModelFromPopular(
                    movieModelPopular,
                ),
            )

            Toast.makeText(requireContext(), "Removed From Watch List", Toast.LENGTH_SHORT).show()
        } else if (!movieModelPopular.isSaved) { // false
            movieModelPopular.isSaved = true
            setSaved(true)
            viewModel.updatePopularModel(movieModelPopular)
            viewModel.insertMovieToWatchList(
                viewModel.createWatchListModelFromPopular(
                    movieModelPopular,
                ),
            )

            Toast.makeText(requireContext(), "Added To Watch List", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun clickFromWatchList(watchListModel: WatchListModel) {
        if (watchListModel.isSaved!!) {
            watchListModel.isSaved = false
            setSaved(false)
            viewModel.removeWatchListMovie(watchListModel)
            Toast.makeText(requireContext(), "Removed From Watch List", Toast.LENGTH_SHORT).show()
        } else if (!watchListModel.isSaved!!) {
            watchListModel.isSaved = true
            setSaved(true)
            viewModel.insertMovieToWatchList(watchListModel)
            Toast.makeText(requireContext(), "Added To Watch List", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkSaved() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            val savedModel = viewModel.getWatchListMovieByID(movieID!!)
            if (savedModel != null) {
                binding.save.setImage(true)
            } else {
                binding.save.setImage(false)
            }
        }
    }

    private fun setSaved(isSaved: Boolean) {
        binding.apply {
            if (isSaved) {
                save.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.icon_unsave,
                    ),
                )
            } else {
                save.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.favorite_icon2,
                    ),
                )
            }
        }
    }
}
