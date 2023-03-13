package com.example.moviesapp.data.repository.datasourceimpl

import androidx.lifecycle.LiveData
import com.example.moviesapp.data.db.MovieDao
import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.data.model.dto.homepagedto.PopularDto
import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto
import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto
import com.example.moviesapp.data.model.watchlist.WatchListModel
import com.example.moviesapp.data.repository.datasource.LocalDataSource

class LocalDataSourceImpl(private val movieDao: MovieDao) : LocalDataSource {

    // Now Playing
    override fun getAllNowPlaying(): LiveData<List<NowPlayingDto>> = movieDao.getAllNowPlaying()

    override suspend fun insertNowPlayingList(listNP: List<NowPlayingDto>) =
        movieDao.insertNowPlayingList(listNP)

    override suspend fun updateWatchListStatus(dtoNP: NowPlayingDto) =
        movieDao.updateWatchListStatus(dtoNP)

    override suspend fun deleteNowPlayingTable() =
        movieDao.deleteNowPlayingTable()

    // Upcoming
    override fun getAllUpcoming(): LiveData<List<UpcomingDto>> = movieDao.getAllUpcoming()

    override suspend fun insertUpcomingList(listUpcoming: List<UpcomingDto>) =
        movieDao.insertUpcomingList(listUpcoming)

    override suspend fun updateWatchListStatus(dtoUpcoming: UpcomingDto) =
        movieDao.updateWatchListStatus(dtoUpcoming)

    override suspend fun deleteUpcomingTable() = movieDao.deleteUpcomingTable()

    // Top Rated
    override fun getAllTopRated(): LiveData<List<TopRatedDto>> = movieDao.getAllTopRated()

    override suspend fun insertTopRatedList(listTopRated: List<TopRatedDto>) =
        movieDao.insertTopRatedList(listTopRated)

    override suspend fun updateWatchListStatus(dtoTopRated: TopRatedDto) =
        movieDao.updateWatchListStatus(dtoTopRated)

    override suspend fun deleteTopRatedTable() = movieDao.deleteTopRatedTable()

    // Popular
    override fun getAllPopular(): LiveData<List<PopularDto>> = movieDao.getAllPopular()

    override suspend fun insertPopularList(listPopular: List<PopularDto>) =
        movieDao.insertPopularList(listPopular)

    override suspend fun updateWatchListStatus(dtoPopular: PopularDto) =
        movieDao.updateWatchListStatus(dtoPopular)

    override suspend fun deletePopularTable() = movieDao.deletePopularTable()

    // Watch List
    override fun getWatchListMovies(): LiveData<List<WatchListModel>> =
        movieDao.getWatchListMovies()

    override suspend fun insertMovieToWatchList(watchListMovie: WatchListModel) =
        movieDao.insertMovieToWatchList(watchListMovie)

    override suspend fun removeWatchListMovie(watchListMovie: WatchListModel) =
        movieDao.removeWatchListMovie(watchListMovie)

    override suspend fun getWatchListMovieById(movieID: Int): WatchListModel =
        movieDao.getWatchListMovieById(movieID)

    override suspend fun updateWatchListModel(watchListModel: WatchListModel) =
        movieDao.updateWatchListModel(watchListModel)
}
