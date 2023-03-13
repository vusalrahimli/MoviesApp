package com.example.moviesapp.data.api

import com.example.moviesapp.data.model.homepage.upcoming.UpcomingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UpcomingAPI {
    @GET("movie/upcoming")
    suspend fun getUpcomingList(@Query("api_key") apiKey: String): Response<UpcomingResponse>
}