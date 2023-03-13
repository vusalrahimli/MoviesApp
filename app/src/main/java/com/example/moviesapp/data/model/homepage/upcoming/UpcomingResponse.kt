package com.example.moviesapp.data.model.homepage.upcoming

data class UpcomingResponse(
    val dates: DatesUpcoming,
    val page: Int,
    val results: List<UpcomingModel>,
    val total_pages: Int,
    val total_results: Int
)