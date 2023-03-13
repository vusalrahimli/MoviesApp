package com.example.moviesapp.data.repository.datasource

import com.example.moviesapp.data.model.homepage.nowplaying.NowPlayingResponse
import com.example.moviesapp.data.model.homepage.popular.PopularResponse
import com.example.moviesapp.data.model.homepage.toprated.TopRatedResponse
import com.example.moviesapp.data.model.homepage.upcoming.UpcomingResponse
import com.example.moviesapp.data.model.moviedetailspage.cast.CreditsResponse
import com.example.moviesapp.data.model.moviedetailspage.cast.persondetails.PersonDetailsResponse
import com.example.moviesapp.data.model.moviedetailspage.moviedetails.MovieDetailsResponse
import com.example.moviesapp.data.model.moviedetailspage.reviews.ReviewResponse
import com.example.moviesapp.data.model.moviedetailspage.trailer.TrailerResponse
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteDataSource {

    // HomePage
    suspend fun getNowPlaying(@Query("api_key") apiKey: String): Response<NowPlayingResponse>

    suspend fun getUpcoming(@Query("api_key") apiKey: String): Response<UpcomingResponse>

    suspend fun getTopRated(@Query("api_key") apiKey: String): Response<TopRatedResponse>

    suspend fun getPopular(@Query("api_key") apiKey: String): Response<PopularResponse>

    // Details
    suspend fun getMovieTrailer(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
    ): Response<TrailerResponse>

    suspend fun getMovieReviews(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
    ): Response<ReviewResponse>

    suspend fun getMovieCredits(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
    ): Response<CreditsResponse>

    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
    ): Response<MovieDetailsResponse>

    suspend fun getPersonDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
    ): Response<PersonDetailsResponse>
}
