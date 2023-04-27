package com.mymusic.reflexionai_assignment.utils.api

import com.mymusic.reflexionai_assignment.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCall {
    @GET("{page}.json")
    suspend fun getMovie(@Path("page") page: Int): Response<MovieModel>
}