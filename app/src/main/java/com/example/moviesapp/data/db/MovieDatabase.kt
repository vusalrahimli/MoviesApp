package com.example.moviesapp.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.data.model.dto.homepagedto.PopularDto
import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto
import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto
import com.example.moviesapp.data.model.watchlist.WatchListModel

@Database(
    entities = [
        NowPlayingDto::class,
        UpcomingDto::class,
        TopRatedDto::class,
        PopularDto::class,
        WatchListModel::class,
    ],
    version = 8,
    autoMigrations = [
        AutoMigration(from = 7, to = 8),
    ],
    exportSchema = true,
)
@TypeConverters
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
