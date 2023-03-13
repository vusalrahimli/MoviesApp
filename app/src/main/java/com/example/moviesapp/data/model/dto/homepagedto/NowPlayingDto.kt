package com.example.moviesapp.data.model.dto.homepagedto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "now_playing_table")
@Parcelize
data class NowPlayingDto(
    val backDropPath: String? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val rating: Double? = null,
    var isSaved: Boolean,
) : Parcelable
