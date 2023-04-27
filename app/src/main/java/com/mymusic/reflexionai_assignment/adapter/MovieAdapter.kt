package com.mymusic.reflexionai_assignment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mymusic.reflexionai_assignment.R
import com.mymusic.reflexionai_assignment.adapter.click.RcHomeClick
import com.mymusic.reflexionai_assignment.databinding.LayoutMovieCardBinding
import com.mymusic.reflexionai_assignment.model.DbMovie
import com.mymusic.reflexionai_assignment.model.Movie

class MovieAdapter(
    private val context: Context,
    private val movieList: MutableList<Movie>,
    val click: RcHomeClick
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val roomMovieList: MutableList<DbMovie> = mutableListOf()
    inner class MovieViewHolder(private val binding: LayoutMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(data: Movie) {
            binding.apply {
                setImage(data.Movie_Poster)
                txtMoviName.text = context.getString(R.string.move_title, data.Title, data.Year)
                txtRunTime.text = runtime(data.Runtime.toInt())
                txtMoviDiscription.text = data.Short_Summary
                txtRating.text = context.getString(R.string.rating, data.Rating)
                txtCastCard.text = context.getString(R.string.cast,data.Cast)
                imageButton.setOnClickListener {
                    if (click.onFavoriteButtonClick(data.IMDBID))notifyDataSetChanged()
                }
                imgButtonDelete.setOnClickListener {
                    if (click.onDeleteButtonClick(data.IMDBID))notifyDataSetChanged()
                }
            }
            if (click.isFavorite(data.IMDBID)){
                binding.imgButtonDelete.visibility = View.VISIBLE
                binding.imageButton.setImageResource(R.drawable.ic_favorite_24)
            }else{
                binding.imgButtonDelete.visibility = View.GONE
                binding.imageButton.setImageResource(R.drawable.ic_favorite_border_24)
            }
            binding.root.setOnClickListener { click.onItemClick(data.IMDBID) }

        }


        private fun setImage(url: String) {
            Glide.with(context)
                .asBitmap()
                .load(url)
                .centerCrop()
                .into(binding.imgPoster)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = LayoutMovieCardBinding.inflate(inflate, parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun addData(newItems: List<Movie>) {
        val start = movieList.size
        movieList.addAll(newItems)
        notifyItemRangeInserted(start, newItems.size)
        Log.d("ANKIT","Api data ${movieList.size}")
    }
    @SuppressLint("NotifyDataSetChanged")
    fun roomData(data:List<DbMovie>){
        Log.d("ANKIT","Room data ${data.size}")
        roomMovieList.clear()
        roomMovieList.addAll(data)
        notifyDataSetChanged()
    }
}