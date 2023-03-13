package com.example.moviesapp.data.api

import com.example.moviesapp.data.model.homepage.popular.PopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularAPI {

    @GET("movie/popular")
    suspend fun getPopularList(@Query("api_key") apiKey: String): Response<PopularResponse>
}