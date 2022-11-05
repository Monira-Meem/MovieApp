package com.example.mymovieapp.data.repository

import com.example.mymovieapp.data.remotesource.RemoteDataSource
import com.example.mymovieapp.data.remotesource.RemoteDataSourceImpl
import com.example.mymovieapp.data.model.MovieResponse
import com.example.mymovieapp.data.model.singlemovie.MovieDetails
import retrofit2.Response

class MovieRepositoryImpl : MovieRepository {

    private val remoteDataSource : RemoteDataSource

    init {
        remoteDataSource = RemoteDataSourceImpl()
    }


    override suspend fun getPopularMovies(
        page: Int
    ): Response<MovieResponse> {
        return remoteDataSource.getPopularMovies(page)
    }

    /*
    return remoteDataSource.getPopularMovies(page,{
            successCallback(it)
        },{
            failureCallBack(it)
        })
     */

    override suspend fun getMovieDetails(movieId: Int): Response<MovieDetails> {
       return remoteDataSource.getMovieDetails(movieId)
    }
    /*
     return remoteDataSource.getMovieDetails( movieId, {
            successCallback(it)
        } ,{
            failureCallBack(it)
        })
     */
}