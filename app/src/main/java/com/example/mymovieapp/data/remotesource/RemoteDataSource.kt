package com.example.mymovieapp.data.remotesource

import com.example.mymovieapp.data.model.MovieResponse
import com.example.mymovieapp.data.model.singlemovie.MovieDetails
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getPopularMovies(page : Int) : Response<MovieResponse>

    suspend fun getMovieDetails( movieId : Int) : Response<MovieDetails>
}