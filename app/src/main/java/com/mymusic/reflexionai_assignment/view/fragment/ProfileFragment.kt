package com.mymusic.reflexionai_assignment.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mymusic.reflexionai_assignment.R
import com.mymusic.reflexionai_assignment.databinding.FragmentProfileBinding
import com.mymusic.reflexionai_assignment.utils.MyApplication
import com.mymusic.reflexionai_assignment.viewModel.MovieViewModel
import com.mymusic.reflexionai_assignment.viewModel.ViewModelFactory

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieModel: MovieViewModel
    private var totalFavorite = 0
    private var totalRoomData = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        val myRepo = (requireActivity().application as MyApplication).movieRepo
        movieModel = ViewModelProvider(this, ViewModelFactory(myRepo))[MovieViewModel::class.java]
        totalFavorite = movieModel.getFavoriteMovie().size
        totalRoomData = movieModel.getRoomMovie().size
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtName.text = getString(R.string.user_name)
        binding.txtProfile.text =getString(R.string.user_name)[0].toString()
        binding.txtTotalApi.text =getString(R.string.total_api_data,35)
        binding.txtTotalMovie.text = getString(R.string.total_data_room,totalRoomData)
        binding.txtTotalFavorite.text = getString(R.string.total_favorite,totalFavorite)
        binding.txtThanks.text = getString(R.string.thank_you)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        totalFavorite = movieModel.getFavoriteMovie().size
        totalRoomData = movieModel.getRoomMovie().size
        binding.txtTotalMovie.text = getString(R.string.total_data_room,totalRoomData)
        binding.txtTotalFavorite.text = getString(R.string.total_favorite,totalFavorite)

    }
}