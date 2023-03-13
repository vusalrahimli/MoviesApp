package com.example.moviesapp.data.model.watchlist

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "watch_list_table")
@Parcelize
data class WatchListModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val backdrop_path: String? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val title: String? = null,
    val rating: Double? = null,
    var isSaved: Boolean? = null,
) : Parcelable
