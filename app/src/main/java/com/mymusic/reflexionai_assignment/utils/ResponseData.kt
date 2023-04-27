package com.mymusic.reflexionai_assignment.utils

import com.mymusic.reflexionai_assignment.model.MovieModel

sealed class ResponseData {
    data class Success(val data: MovieModel) : ResponseData()
    data class Error(val message: String) : ResponseData()
}