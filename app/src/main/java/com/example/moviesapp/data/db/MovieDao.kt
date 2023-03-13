package com.example.moviesapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.data.model.dto.homepagedto.PopularDto
import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto
import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto
import com.example.moviesapp.data.model.watchlist.WatchListModel

@Dao
interface MovieDao {
    // NowPlaying
    @Query("SELECT * FROM now_playing_table ORDER BY rating DESC")
    fun getAllNowPlaying(): LiveData<List<NowPlayingDto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNowPlayingList(listNP: List<NowPlayingDto>)

    @Update
    suspend fun updateWatchListStatus(dtoNP: NowPlayingDto)

    @Query("DELETE FROM now_playing_table")
    suspend fun deleteNowPlayingTable()

    // Upcoming
    @Query("SELECT * FROM upcoming ORDER BY rating DESC")
    fun getAllUpcoming(): LiveData<List<UpcomingDto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUpcomingList(listUpcoming: List<UpcomingDto>)

    @Update
    suspend fun updateWatchListStatus(dtoUpcoming: UpcomingDto)

    @Query("DELETE FROM upcoming")
    suspend fun deleteUpcomingTable()

    // Top Rated
    @Query("SELECT * FROM top_rated ORDER BY rating DESC")
    fun getAllTopRated(): LiveData<List<TopRatedDto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTopRatedList(listUpcoming: List<TopRatedDto>)

    @Update
    suspend fun updateWatchListStatus(dtoTopRated: TopRatedDto)

    @Query("DELETE FROM top_rated")
    suspend fun deleteTopRatedTable()

    // Popular
    @Query("SELECT * FROM popular ORDER BY rating DESC")
    fun getAllPopular(): LiveData<List<PopularDto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPopularList(listPopular: List<PopularDto>)

    @Update
    suspend fun updateWatchListStatus(dtoPopular: PopularDto)

    @Query("DELETE FROM popular")
    suspend fun deletePopularTable()

    // Watch List
    @Query("SELECT * FROM watch_list_table ORDER BY rating DESC")
    fun getWatchListMovies(): LiveData<List<WatchListModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieToWatchList(watchListMovie: WatchListModel)

    @Delete
    suspend fun removeWatchListMovie(watchListMovie: WatchListModel)

    @Query("SELECT * FROM watch_list_table WHERE id=:id")
    suspend fun getWatchListMovieById(id: Int): WatchListModel

    @Update
    suspend fun updateWatchListModel(watchListModel: WatchListModel)
}
