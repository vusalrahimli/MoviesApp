package com.example.moviesapp.data.model.moviedetailspage.cast

data class CreditsResponse(
    val cast: List<CastModel>,
    val crew: List<Crew>,
    val id: Int
)