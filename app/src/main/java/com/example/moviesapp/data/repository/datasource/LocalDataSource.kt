package com.example.moviesapp.data.repository.datasource

import androidx.lifecycle.LiveData
import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.data.model.dto.homepagedto.PopularDto
import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto
import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto
import com.example.moviesapp.data.model.watchlist.WatchListModel

interface LocalDataSource {

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

    suspend fun removeWatchListMovie(watchListMovie: WatchListModel)

    suspend fun getWatchListMovieById(movieID: Int): WatchListModel

    suspend fun updateWatchListModel(watchListModel: WatchListModel)
}
