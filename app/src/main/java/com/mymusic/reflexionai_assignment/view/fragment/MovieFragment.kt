package com.mymusic.reflexionai_assignment.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mymusic.reflexionai_assignment.adapter.MovieAdapter
import com.mymusic.reflexionai_assignment.adapter.Pagenatation
import com.mymusic.reflexionai_assignment.adapter.click.RcHomeClick
import com.mymusic.reflexionai_assignment.databinding.FragmentHomeBinding
import com.mymusic.reflexionai_assignment.model.DbMovie
import com.mymusic.reflexionai_assignment.utils.MyApplication
import com.mymusic.reflexionai_assignment.view.activity.DetailActivity
import com.mymusic.reflexionai_assignment.viewModel.MovieViewModel
import com.mymusic.reflexionai_assignment.viewModel.ViewModelFactory

class MovieFragment : Fragment(), RcHomeClick {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieModel: MovieViewModel
    private lateinit var pagination: Pagenatation
    private lateinit var adapter: MovieAdapter
    private lateinit var roomData: List<DbMovie>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val myRepo = (requireActivity().application as MyApplication).movieRepo
        movieModel = ViewModelProvider(this, ViewModelFactory(myRepo))[MovieViewModel::class.java]
        movieModel.getMovies(requireContext(), 1)
        movieModel.getMovie()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MovieAdapter(requireContext(), mutableListOf(), this)
        roomData = emptyList()


        movieModel.movies.observe(viewLifecycleOwner) {
            adapter.addData(it.Movie_List)
            adapter.roomData(roomData)
        }
        movieModel.movie.observe(viewLifecycleOwner){
            roomData = it
        }

        binding.rcHome.adapter = adapter
        pagination = Pagenatation(LinearLayoutManager(requireContext())) {
            if (movieModel.currentPage == movieModel.totalPage) pagination.disablePagination()
            else movieModel.getMovies(requireContext(), movieModel.currentPage + 1)
        }
        binding.rcHome.addOnScrollListener(pagination)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onFavoriteButtonClick(id: String): Boolean {
      return movieModel.setFavorite(id,true)
    }

    override fun isFavorite(id: String): Boolean {
       return movieModel.isFavorite(id)
    }

    override fun onDeleteButtonClick(id: String): Boolean {
        return movieModel.setFavorite(id, false)
    }

    override fun onItemClick(id: String) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra("id",id)
        requireActivity().startActivity(intent)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}