package com.mymusic.reflexionai_assignment.utils.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mymusic.reflexionai_assignment.model.DbMovie


@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(data: DbMovie)

    @Query("SELECT * FROM MovieDB ")
    fun getMoves(): List<DbMovie>

    @Query("SELECT * FROM MovieDB WHERE IMDBID = :id")
    fun getMovieById(id: String): DbMovie?

    @Query("UPDATE MovieDB SET favorite = :favorite WHERE IMDBID = :id")
    fun setFavorite(id: String, favorite: Boolean)

    @Query("SELECT favorite FROM MovieDB WHERE IMDBID = :itemId")
    fun isFavorite(itemId: String): Boolean

    @Query("SELECT * FROM MovieDB WHERE favorite == 1")
    fun getFavoriteMovie(): List<DbMovie>

    @Update
    fun updateData(data: DbMovie)

}