package com.example.moviesapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.data.model.dto.homepagedto.PopularDto
import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto
import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto
import com.example.moviesapp.data.model.homepage.nowplaying.NowPlayingModel
import com.example.moviesapp.data.model.homepage.popular.PopularModel
import com.example.moviesapp.data.model.homepage.toprated.TopRatedModel
import com.example.moviesapp.data.model.homepage.upcoming.UpcomingModel
import com.example.moviesapp.data.model.moviedetailspage.cast.CastModel
import com.example.moviesapp.data.model.moviedetailspage.cast.persondetails.PersonDetailsResponse
import com.example.moviesapp.data.model.moviedetailspage.moviedetails.Genre
import com.example.moviesapp.data.model.moviedetailspage.moviedetails.MovieDetailsResponse
import com.example.moviesapp.data.model.moviedetailspage.reviews.ReviewModel
import com.example.moviesapp.data.model.moviedetailspage.trailer.TrailerModel
import com.example.moviesapp.data.model.watchlist.WatchListModel
import com.example.moviesapp.util.Resource

interface MovieRepository {

    // Remote
    suspend fun getNowPlaying(): Resource<List<NowPlayingModel>>

    suspend fun getUpcoming(): Resource<List<UpcomingModel>>

    suspend fun getTopRated(): Resource<List<TopRatedModel>>

    suspend fun getPopular(): Resource<List<PopularModel>>

    suspend fun getMovieTrailer(movieID: Int): Resource<List<TrailerModel>>

    suspend fun getMovieReviews(movieID: Int): Resource<List<ReviewModel>>

    suspend fun getMovieCredits(movieID: Int): Resource<List<CastModel>>

    suspend fun getMovieGenres(movieID: Int): Resource<List<Genre>>

    suspend fun getMovieDuration(movieID: Int): Resource<MovieDetailsResponse>

    suspend fun getPersonDetails(personID: Int): Resource<PersonDetailsResponse>

    // Local
    // Now Playing
    fun getAllNowPlaying(): LiveData<List<NowPlayingDto>>

    suspend fun insertNowPlayingList(listNP: List<NowPlayingDto>)

    suspend fun updateWatchListStatus(dtoNP: NowPlayingDto)

    suspend fun deleteNowPlayingTable()

    // Upcoming
    fun getAllUpcoming(): LiveData<List<UpcomingDto>>

    suspend fun insertUpcomingList(listUpcoming: List<UpcomingDto>)

    suspend fun updateWatchListStatus(dtoUpcoming: UpcomingDto)

    suspend fun deleteUpcomingTable()

    // Top Rated
    fun getAllTopRated(): LiveData<List<TopRatedDto>>

    suspend fun insertTopRatedList(listTopRated: List<TopRatedDto>)

    suspend fun updateWatchListStatus(dtoTopRated: TopRatedDto)

    suspend fun deleteTopRatedTable()

    // Popular
    fun getAllPopular(): LiveData<List<PopularDto>>

    suspend fun insertPopularList(listPopular: List<PopularDto>)

    suspend fun updateWatchListStatus(dtoPopular: PopularDto)

    suspend fun deletePopularTable()

    // Watch List
    fun getWatchListMovies(): LiveData<List<WatchListModel>>

    suspend fun insertMovieToWatchList(watchListMovie: WatchListModel)

    suspend fun removeWatchListMovie(watchListModel: WatchListModel)

    suspend fun getWatchListMovieById(movieID: Int): WatchListModel

    suspend fun updateWatchListModel(watchListModel: WatchListModel)
}
