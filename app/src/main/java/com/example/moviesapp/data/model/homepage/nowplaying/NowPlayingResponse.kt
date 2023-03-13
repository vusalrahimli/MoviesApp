package com.example.moviesapp.data.model.homepage.nowplaying

data class NowPlayingResponse(
    val dates: DatesNowPlaying,
    val page: Int,
    val results: List<NowPlayingModel>,
    val total_pages: Int,
    val total_results: Int
)