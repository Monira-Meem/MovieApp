package com.example.mymovieapp.data.repository

import com.example.mymovieapp.data.datasource.RemoteDataSource
import com.example.mymovieapp.data.datasource.RemoteDataSourceImpl
import com.example.mymovieapp.data.model.MovieResponse
import com.example.mymovieapp.data.model.singlemovie.MovieDetails
import io.reactivex.rxjava3.core.Observable

class MovieRepositoryImpl : MovieRepository {

    private val remoteDataSource : RemoteDataSource

    init {
        remoteDataSource = RemoteDataSourceImpl()
    }


    override fun getPopularMovies(
        page: Int
    ): Observable<MovieResponse> {
        return remoteDataSource.getPopularMovies(page)
    }

    /*
    return remoteDataSource.getPopularMovies(page,{
            successCallback(it)
        },{
            failureCallBack(it)
        })
     */

    override fun getMovieDetails(movieId: Int): Observable<MovieDetails> {
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