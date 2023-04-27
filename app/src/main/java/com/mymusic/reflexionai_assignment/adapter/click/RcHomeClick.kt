package com.mymusic.reflexionai_assignment.adapter.click


interface RcHomeClick {
    fun onFavoriteButtonClick(id: String):Boolean
    fun isFavorite(id: String):Boolean
    fun onDeleteButtonClick(id: String):Boolean
    fun onItemClick(id: String)
}