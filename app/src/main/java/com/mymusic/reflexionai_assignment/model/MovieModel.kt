package com.mymusic.reflexionai_assignment.model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("Movie List")
    val Movie_List: List<Movie>
)