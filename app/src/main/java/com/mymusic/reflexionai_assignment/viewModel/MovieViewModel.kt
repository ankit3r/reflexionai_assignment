package com.mymusic.reflexionai_assignment.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mymusic.reflexionai_assignment.model.DbMovie
import com.mymusic.reflexionai_assignment.model.MovieModel
import com.mymusic.reflexionai_assignment.repository.MovieRepo
import com.mymusic.reflexionai_assignment.utils.ResponseData
import com.mymusic.reflexionai_assignment.view.PrograssBar
import kotlinx.coroutines.launch

class MovieViewModel(private val apiRepo: MovieRepo) : ViewModel() {
    val totalPage: Int = 2
    var currentPage: Int = 1

    private val _listOfMovie = MutableLiveData<MovieModel>()
    val movies: LiveData<MovieModel> = _listOfMovie

    fun getMovies(context: Context, page: Int = 1) {
        val progressBar = PrograssBar(context)
        progressBar.loading("Loading Data...")
        progressBar.showLoder()
        currentPage = page
        viewModelScope.launch {
            when (val response = apiRepo.getMovie(page)) {
                is ResponseData.Success -> {
                    _listOfMovie.value = response.data
                    progressBar.hideLoder()
                }
                is ResponseData.Error -> {
                    progressBar.hideLoder()
                    Toast.makeText(context, "Error ${response.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val _listOfMovies = MutableLiveData<List<DbMovie>>()
    val movie: LiveData<List<DbMovie>> = _listOfMovies
    fun getMovie() {
        _listOfMovies.value = apiRepo.getRoomData()
    }

    fun setFavorite(id: String, isFavorite: Boolean): Boolean {
        var isAdded = false
        apiRepo.setFavorite(id, isFavorite) { s, _ -> isAdded = s }
        return isAdded
    }

    fun isFavorite(id: String): Boolean {
        return apiRepo.isFavorite(id)
    }

    fun getFavoriteMovie(): List<DbMovie> {
        return apiRepo.getFavoriteMovies()
    }

    fun getRoomMovie(): List<DbMovie> {
        return apiRepo.getRoomMovies()
    }

    fun getMovieById(id:String):DbMovie {
        return apiRepo.getMoviesById(id)
    }

}