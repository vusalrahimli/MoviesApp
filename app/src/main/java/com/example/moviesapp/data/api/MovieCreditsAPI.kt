package com.example.moviesapp.data.api

import com.example.moviesapp.data.model.moviedetailspage.cast.CreditsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieCreditsAPI {
    @GET("movie/{id}/credits")
    suspend fun getCredits(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<CreditsResponse>
}