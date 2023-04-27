package com.mymusic.reflexionai_assignment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mymusic.reflexionai_assignment.R
import com.mymusic.reflexionai_assignment.adapter.click.FavoriteClick
import com.mymusic.reflexionai_assignment.databinding.LayoutMovieCardBinding
import com.mymusic.reflexionai_assignment.model.DbMovie

class FavoriteAdapter(
    private val context: Context,
    private val movieList: MutableList<DbMovie>,
    val click: FavoriteClick
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(private val binding: LayoutMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(data: DbMovie) {
            binding.apply {
                setImage(data.Movie_Poster)
                txtMoviName.text = context.getString(R.string.move_title, data.Title, data.Year)
                txtRunTime.text = runtime(data.Runtime.toInt())
                txtMoviDiscription.text = data.Short_Summary
                txtRating.text = context.getString(R.string.rating, data.Rating)
                txtCastCard.text = context.getString(R.string.cast,data.Cast)
                imageButton.setImageResource(R.drawable.ic_favorite_24)
                imgButtonDelete.visibility = View.VISIBLE
                imageButton.setOnClickListener {
                    click.onDeleteClick(data.IMDBID)
                }
                imgButtonDelete.setOnClickListener {
                    click.onDeleteClick(data.IMDBID)
                }
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = LayoutMovieCardBinding.inflate(inflate, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(movieList[position])
    }



    override fun getItemCount(): Int = movieList.size



    @SuppressLint("NotifyDataSetChanged")
    fun addData(newItems: List<DbMovie>) {
        val start = movieList.size
        movieList.clear()
        movieList.addAll(newItems)
        notifyItemRangeInserted(start, newItems.size)
        notifyDataSetChanged()
    }

}