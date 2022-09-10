package com.example.mymovieapp.ui.adapter

import com.example.mymovieapp.data.model.Movie

interface OnItemClickListener {
    fun onItemClick(movie : Movie)
}