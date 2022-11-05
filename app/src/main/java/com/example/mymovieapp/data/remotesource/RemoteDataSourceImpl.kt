package com.example.mymovieapp.data.remotesource

import com.example.mymovieapp.data.api.ApiClient
import com.example.mymovieapp.data.model.MovieResponse
import com.example.mymovieapp.data.model.singlemovie.MovieDetails
import com.example.mymovieapp.utils.Constants.Companion.API_KEY
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {

    override suspend fun getPopularMovies(
        page : Int
    ): Response<MovieResponse> {
        val apiService = ApiClient.api
        return apiService.getPopularMovies(API_KEY,page)
    }

    /*val call = apiService.getPopularMovies(apiKey = API_KEY, page)
        call.enqueue(object  : retrofit2.Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        successCallback(it)
                    }
                } else {
                    failureCallBack("Failed")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                failureCallBack(t.localizedMessage)
            }
        })

     */

    override suspend fun getMovieDetails(movieId: Int): Response<MovieDetails> {
        val  api = ApiClient.api
        return api.getMovieDetails(movieId)
    }

    /*
    val apiService = ApiClient.api
        val call = apiService.getMovieDetails(movieId)
        call.enqueue(object  : retrofit2.Callback<MovieDetails>{
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        successCallback(it)
                    }
                } else {
                    failureCallBack("Failed")
                }
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                failureCallBack(t.localizedMessage)
            }
        })
     */

}