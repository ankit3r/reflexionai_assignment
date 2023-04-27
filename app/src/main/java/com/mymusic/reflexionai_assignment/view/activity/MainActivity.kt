package com.mymusic.reflexionai_assignment.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.mymusic.reflexionai_assignment.R
import com.mymusic.reflexionai_assignment.adapter.PageAdapter
import com.mymusic.reflexionai_assignment.databinding.ActivityMainBinding
import com.mymusic.reflexionai_assignment.view.fragment.MovieFragment
import com.mymusic.reflexionai_assignment.view.fragment.FavoriteFragment
import com.mymusic.reflexionai_assignment.view.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPage()
    }
    private fun setPage() {
        val adapter = PageAdapter(this)
        adapter.addFragment(MovieFragment(), getString(R.string.home))
        adapter.addFragment(FavoriteFragment(), getString(R.string.movie))
        adapter.addFragment(ProfileFragment(), getString(R.string.profile))
        binding.pageView.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.pageView) { tab, position ->
            tab.text = adapter.getTitle(position)
        }.attach()

    }
}

