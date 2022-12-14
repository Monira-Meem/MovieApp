package com.example.mymovieapp.data.repository

import com.example.mymovieapp.data.model.MovieResponse
import com.example.mymovieapp.data.model.singlemovie.MovieDetails
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response

interface MovieRepository {

    suspend fun getPopularMovies(page : Int) : Response<MovieResponse>

    suspend fun getMovieDetails(movieId : Int) : Response<MovieDetails>
}