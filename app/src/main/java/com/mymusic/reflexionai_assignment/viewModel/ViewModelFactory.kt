package com.mymusic.reflexionai_assignment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mymusic.reflexionai_assignment.repository.MovieRepo

class ViewModelFactory(private val apiRepo: MovieRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(apiRepo) as T
    }
}