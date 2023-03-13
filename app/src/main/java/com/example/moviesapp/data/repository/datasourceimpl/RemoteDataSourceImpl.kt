package com.example.moviesapp.data.repository.datasourceimpl

import com.example.moviesapp.data.api.API
import com.example.moviesapp.data.model.homepage.nowplaying.NowPlayingResponse
import com.example.moviesapp.data.model.homepage.popular.PopularResponse
import com.example.moviesapp.data.model.homepage.toprated.TopRatedResponse
import com.example.moviesapp.data.model.homepage.upcoming.UpcomingResponse
import com.example.moviesapp.data.model.moviedetailspage.cast.CreditsResponse
import com.example.moviesapp.data.model.moviedetailspage.cast.persondetails.PersonDetailsResponse
import com.example.moviesapp.data.model.moviedetailspage.moviedetails.MovieDetailsResponse
import com.example.moviesapp.data.model.moviedetailspage.reviews.ReviewResponse
import com.example.moviesapp.data.model.moviedetailspage.trailer.TrailerResponse
import com.example.moviesapp.data.repository.datasource.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl(private val api: API) : RemoteDataSource {

    // HomePage
    override suspend fun getNowPlaying(apiKey: String): Response<NowPlayingResponse> =
        api.getNowPlayingAPI.getNowPlayingList(apiKey)

    override suspend fun getUpcoming(apiKey: String): Response<UpcomingResponse> =
        api.getUpcomingAPI.getUpcomingList(apiKey)

    override suspend fun getTopRated(apiKey: String): Response<TopRatedResponse> =
        api.getTopRatedAPI.getTopRatedList(apiKey)

    override suspend fun getPopular(apiKey: String): Response<PopularResponse> =
        api.getPopularAPI.getPopularList(apiKey)

    // Details
    override suspend fun getMovieTrailer(id: Int, apiKey: String): Response<TrailerResponse> =
        api.getTrailerAPI.getMovieTrailer(id, apiKey)

    override suspend fun getMovieReviews(id: Int, apiKey: String): Response<ReviewResponse> =
        api.getReviewsAPI.getMovieReviews(id, apiKey)

    override suspend fun getMovieCredits(id: Int, apiKey: String): Response<CreditsResponse> =
        api.getCreditsAPI.getCredits(id, apiKey)

    override suspend fun getMovieDetails(id: Int, apiKey: String): Response<MovieDetailsResponse> =
        api.getMovieDetailsAPI.getMovieDetails(id, apiKey)

    override suspend fun getPersonDetails(
        id: Int,
        apiKey: String,
    ): Response<PersonDetailsResponse> =
        api.getPersonDetailsAPI.getPersonDetails(id, apiKey)
}
