package com.example.mymovieapp.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.mymovieapp.data.model.Movie

class MovieListDiffUtil(private val oldList : List<Movie>, private val newList : List<Movie>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}