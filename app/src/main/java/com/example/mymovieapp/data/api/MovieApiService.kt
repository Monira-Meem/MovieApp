package com.example.mymovieapp.data.api

import com.example.mymovieapp.data.model.MovieResponse
import com.example.mymovieapp.data.model.singlemovie.MovieDetails
import com.example.mymovieapp.utils.Constants.Companion.API_KEY
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey : String,
                         @Query("page") page : Int) : Observable<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId : Int,
                        @Query("api_key") apiKey: String = API_KEY) : Observable<MovieDetails>

}