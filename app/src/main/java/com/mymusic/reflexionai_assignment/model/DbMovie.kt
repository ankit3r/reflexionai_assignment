package com.mymusic.reflexionai_assignment.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("MovieDB")
data class DbMovie(
    @PrimaryKey
    val IMDBID: String,
    val Cast: String,
    val Movie_Poster: String,
    val Rating: String,
    val Runtime: String,
    val Short_Summary: String,
    val Title: String,
    val Year: String,
    val favorite: Boolean = false,
    val Director: String,
    val Genres: String,
    val Summary: String,
    val Writers: String,
    @ColumnInfo(defaultValue = "not Available")
    val YouTube_Trailer: String?,
)