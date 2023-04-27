package com.mymusic.reflexionai_assignment.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val Cast: String,
    val Director: String,
    val Genres: String,
    val IMDBID: String,
    @SerializedName("Movie Poster")
    val Movie_Poster: String,
    val Rating: String,
    val Runtime: String,
    @SerializedName("Short Summary")
    val Short_Summary: String,
    val Summary: String,
    val Title: String,
    val Writers: String,
    val Year: String,
    @SerializedName("YouTube Trailer")
    val YouTube_Trailer: String,
    val favorite: Boolean = false
)