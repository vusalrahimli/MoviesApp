package com.example.moviesapp.data.api

import com.example.moviesapp.data.model.homepage.toprated.TopRatedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedAPI {
    @GET("movie/top_rated")
    suspend fun getTopRatedList(@Query("api_key") apiKey: String): Response<TopRatedResponse>
}