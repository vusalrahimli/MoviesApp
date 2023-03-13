package com.example.moviesapp.data.api

import com.example.moviesapp.data.model.homepage.nowplaying.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NowPlayingAPI {

    @GET("movie/now_playing")
    suspend fun getNowPlayingList(@Query("api_key") apiKey: String): Response<NowPlayingResponse>

}
