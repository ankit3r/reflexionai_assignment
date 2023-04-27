package com.mymusic.reflexionai_assignment.repository

import android.database.sqlite.SQLiteConstraintException
import com.mymusic.reflexionai_assignment.model.DbMovie
import com.mymusic.reflexionai_assignment.utils.ResponseData
import com.mymusic.reflexionai_assignment.utils.api.ApiCall
import com.mymusic.reflexionai_assignment.utils.db.MovieDao

class MovieRepo(private val movieApi: ApiCall, private val dao: MovieDao) {

    suspend fun getMovie(page: Int = 1): ResponseData {
        return try {
            val response = movieApi.getMovie(page = page)
            if (response.isSuccessful) {
                response.body()?.let {
                    try {
                        for (data in it.Movie_List) {
                            val existingData = dao.getMovieById(data.IMDBID)
                            if (existingData == null) {
                                dao.addMovies(
                                    DbMovie(
                                        data.IMDBID,
                                        data.Cast,
                                        data.Movie_Poster,
                                        data.Rating,
                                        data.Runtime,
                                        data.Short_Summary,
                                        data.Title,
                                        data.Year,dao.isFavorite(data.IMDBID),
                                        data.Director,
                                        data.Genres,data.Summary,data.Writers,data.YouTube_Trailer
                                    )
                                )
                            } else {
                                dao.updateData(existingData)
                            }

                        }
                    } catch (e: SQLiteConstraintException) {
                        ResponseData.Error("SQL: ${e.message}")
                    }
                    ResponseData.Success(it)
                } ?: ResponseData.Error("Response body is null")
            } else {
                ResponseData.Error("Failed to retrieve movies: ${response.code()}")
            }
        } catch (e: Exception) {
            ResponseData.Error("Failed to retrieve movies: ${e.message}")
        }
    }

    fun getRoomData(): List<DbMovie> {
        return dao.getMoves()
    }

    fun setFavorite(id: String, isFavorite: Boolean, callBack: (Boolean, String) -> Unit) {
        try {
            dao.setFavorite(id, isFavorite)
            callBack(true, "")
        } catch (e: SQLiteConstraintException) {
            callBack(false, e.message.toString())
        }
    }

    fun isFavorite(id: String): Boolean {
        return dao.isFavorite(id)
    }

    fun getFavoriteMovies():List<DbMovie>{
        return dao.getFavoriteMovie()
    }
    fun getRoomMovies():List<DbMovie>{
        return dao.getMoves()
    }

    fun getMoviesById(id:String):DbMovie{
        return dao.getMovieById(id)!!
    }
}