package com.example.moviesapp.data.api

data class API(
    // HomePage
    val getNowPlayingAPI: NowPlayingAPI,
    val getUpcomingAPI: UpcomingAPI,
    val getTopRatedAPI: TopRatedAPI,
    val getPopularAPI: PopularAPI,

    // Details
    val getTrailerAPI: MovieTrailerAPI,
    val getReviewsAPI: MovieReviewsAPI,
    val getCreditsAPI: MovieCreditsAPI,
    val getMovieDetailsAPI: MovieDetailsAPI,
    val getPersonDetailsAPI: PersonDetailsAPI,
)
