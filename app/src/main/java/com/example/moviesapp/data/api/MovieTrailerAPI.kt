package com.example.moviesapp.data.api

import com.example.moviesapp.data.model.moviedetailspage.trailer.TrailerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieTrailerAPI {

    @GET("movie/{id}/videos")
    suspend fun getMovieTrailer(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
    ): Response<TrailerResponse>
}
