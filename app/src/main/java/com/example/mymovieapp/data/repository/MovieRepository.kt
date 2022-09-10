package com.example.mymovieapp.data.repository

import com.example.mymovieapp.data.model.MovieResponse
import com.example.mymovieapp.data.model.singlemovie.MovieDetails
import io.reactivex.rxjava3.core.Observable

interface MovieRepository {

    fun getPopularMovies(page : Int) : Observable<MovieResponse>

    fun getMovieDetails(movieId : Int) : Observable<MovieDetails>
}