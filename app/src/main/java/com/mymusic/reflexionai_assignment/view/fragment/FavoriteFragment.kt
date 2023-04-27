package com.mymusic.reflexionai_assignment.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mymusic.reflexionai_assignment.adapter.FavoriteAdapter
import com.mymusic.reflexionai_assignment.adapter.click.FavoriteClick
import com.mymusic.reflexionai_assignment.databinding.FragmentMovieBinding
import com.mymusic.reflexionai_assignment.utils.MyApplication
import com.mymusic.reflexionai_assignment.view.activity.DetailActivity
import com.mymusic.reflexionai_assignment.viewModel.MovieViewModel
import com.mymusic.reflexionai_assignment.viewModel.ViewModelFactory

class FavoriteFragment : Fragment() , FavoriteClick {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoriteAdapter
    private lateinit var movieModel: MovieViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        val myRepo = (requireActivity().application as MyApplication).movieRepo
        movieModel = ViewModelProvider(this, ViewModelFactory(myRepo))[MovieViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FavoriteAdapter(requireContext(), mutableListOf(),this)
       adapter.addData(movieModel.getFavoriteMovie())
        binding.rcRoom.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDeleteClick(id: String) {
        movieModel.setFavorite(id,false)
        adapter.addData(movieModel.getFavoriteMovie())
    }

    override fun onItemClick(id: String) {
        val intent = Intent(requireActivity(),DetailActivity::class.java)
        intent.putExtra("id",id)
        requireActivity().startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        adapter.addData(movieModel.getFavoriteMovie())
    }


}