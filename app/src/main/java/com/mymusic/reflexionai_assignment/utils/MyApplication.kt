package com.mymusic.reflexionai_assignment.utils

import android.app.Application
import com.mymusic.reflexionai_assignment.repository.MovieRepo
import com.mymusic.reflexionai_assignment.utils.api.ApiCall
import com.mymusic.reflexionai_assignment.utils.api.ApiHelper
import com.mymusic.reflexionai_assignment.utils.db.RoomDB
import java.util.Properties

class MyApplication : Application() {
    lateinit var movieRepo: MovieRepo

    override fun onCreate() {
        super.onCreate()
        val assetManager = assets
        val inputStream = assetManager.open("config.properties")
        val properties = Properties()
        inputStream.use {
            properties.load(it)
            initialize(properties.getProperty("BASE_URL"))
        }
        inputStream.close()
    }

    private fun initialize(baseUrl: String) {
        val dao = RoomDB.getDatabase(applicationContext).DatabaseDao()
        val movieApi = ApiHelper.getInstance(baseUrl).create(ApiCall::class.java)
        movieRepo = MovieRepo(movieApi,dao)
    }
}