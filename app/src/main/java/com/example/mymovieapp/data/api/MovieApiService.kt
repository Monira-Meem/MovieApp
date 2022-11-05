package com.example.mymovieapp.data.api

import com.example.mymovieapp.data.model.MovieResponse
import com.example.mymovieapp.data.model.singlemovie.MovieDetails
import com.example.mymovieapp.utils.Constants.Companion.API_KEY
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey : String,
                         @Query("page") page : Int) : Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId : Int,
                        @Query("api_key") apiKey: String = API_KEY) : Response<MovieDetails>

}