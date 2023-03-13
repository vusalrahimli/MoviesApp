package com.example.moviesapp.data.api

import com.example.moviesapp.data.model.moviedetailspage.moviedetails.MovieDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsAPI {

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
    ): Response<MovieDetailsResponse>
}
