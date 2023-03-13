package com.example.moviesapp.data.api

import com.example.moviesapp.data.model.moviedetailspage.cast.persondetails.PersonDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonDetailsAPI {

    @GET("person/{id}")
    suspend fun getPersonDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
    ): Response<PersonDetailsResponse>
}
