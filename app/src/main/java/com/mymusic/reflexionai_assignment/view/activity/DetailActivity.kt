package com.mymusic.reflexionai_assignment.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mymusic.reflexionai_assignment.R
import com.mymusic.reflexionai_assignment.databinding.ActivityDetailBinding
import com.mymusic.reflexionai_assignment.utils.MyApplication
import com.mymusic.reflexionai_assignment.viewModel.MovieViewModel
import com.mymusic.reflexionai_assignment.viewModel.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var movieModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myRepo = (application as MyApplication).movieRepo
        movieModel = ViewModelProvider(this, ViewModelFactory(myRepo))[MovieViewModel::class.java]
        val id = intent.getStringExtra("id")!!
        val data = movieModel.getMovieById(id)

        binding.apply {
            txtTitle.text = getString(R.string.move_title, data.Title, data.Year)
            setImage(data.Movie_Poster)
            txtRating.text = getString(R.string.rating, data.Rating)
            if (movieModel.isFavorite(id))imageButton.setImageResource(R.drawable.ic_favorite_24)
            else imageButton.setImageResource(R.drawable.ic_favorite_border_24)
            txtRuntime.text = runtime(data.Runtime.toInt())
            txtCast.text = getString(R.string.cast,data.Cast)
            txtWriter.text =getString(R.string.writer,data.Writers)
            txtGenres.text = getString(R.string.genres,data.Genres)
            txtSummery.text = getString(R.string.summery,data.Summary)
        }

        binding.imageButton.setOnClickListener {
            if (movieModel.isFavorite(id)){
                movieModel.setFavorite(id,false)
            }else{
                movieModel.setFavorite(id,true)
            }
            if (movieModel.isFavorite(id))binding.imageButton.setImageResource(R.drawable.ic_favorite_24)
            else binding.imageButton.setImageResource(R.drawable.ic_favorite_border_24)
        }

    }
    private fun setImage(url: String) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .centerCrop()
            .into(binding.imageView4)

    }

    private fun runtime(runtimeInMinutes: Int): String {
        val totalSeconds = runtimeInMinutes * 60
        val hours = totalSeconds / 3600
        val remainingSeconds = totalSeconds % 3600
        val minutes = remainingSeconds / 60
        val seconds = remainingSeconds % 60
        return "Runtime: ${String.format("%02d:%02d:%02d", hours, minutes, seconds)}"
    }
}