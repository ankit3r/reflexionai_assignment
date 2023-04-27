package com.mymusic.reflexionai_assignment.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Pagenatation(private val layoutManager: LinearLayoutManager, val loadMore: () -> Unit)
    :RecyclerView.OnScrollListener(){
    private var isPaginationEnabled = true
    // Implement onScrolled method to detect scroll events
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        // Check if the RecyclerView has been scrolled to the bottom
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

        if (lastVisibleItem == totalItemCount - 1) {
            // Call the loadMore function to load more data
            loadMore()
        }
    }

    fun disablePagination() {
        isPaginationEnabled = false
    }
}