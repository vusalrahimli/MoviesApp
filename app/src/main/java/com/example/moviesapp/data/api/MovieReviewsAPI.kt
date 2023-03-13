package com.example.moviesapp.data.api

import com.example.moviesapp.data.model.moviedetailspage.reviews.ReviewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieReviewsAPI {

    @GET("movie/{id}/reviews")
    suspend fun getMovieReviews(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<ReviewResponse>
}