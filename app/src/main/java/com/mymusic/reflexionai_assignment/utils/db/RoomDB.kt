package com.mymusic.reflexionai_assignment.utils.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mymusic.reflexionai_assignment.model.DbMovie


@Database(entities = [DbMovie::class], version =1)
abstract class RoomDB :RoomDatabase(){
    abstract fun DatabaseDao(): MovieDao
    companion object {

        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabase(context: Context): RoomDB {
            synchronized(this) {
                if (INSTANCE == null) {
                    synchronized(this) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            RoomDB::class.java,
                            "MyShopDb"
                        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}