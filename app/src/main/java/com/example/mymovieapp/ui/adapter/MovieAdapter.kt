package com.example.mymovieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieapp.data.model.Movie
import com.example.mymovieapp.databinding.ItemMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList : List<Movie> = ArrayList()
    private var onItemClickListener : OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.setUpView(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setData(newMovieList : List<Movie>) {
        val diffUtil = MovieListDiffUtil(movieList,newMovieList)
        val results = DiffUtil.calculateDiff(diffUtil)
        movieList = newMovieList
        results.dispatchUpdatesTo(this)
    }

    fun setOnItemClickListener(onItemClickListener : OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun setUpView(movie: Movie) {
                Glide.with(binding.root)
                    .load("https://image.tmdb.org/t/p/w500"+movie.posterPath)
                    .into(binding.ivMovieImage)
                binding.movieTitle.text = movie.title
                binding.movieReleaseDate.text = movie.releaseDate
                binding.root.setOnClickListener {
                    onItemClickListener?.onItemClick(movie)
                }
            }

    }


}