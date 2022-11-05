package com.example.mymovieapp.ui.popularmovies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.data.common.Resource
import com.example.mymovieapp.data.model.MovieResponse
import com.example.mymovieapp.data.repository.MovieRepository
import com.example.mymovieapp.data.repository.MovieRepositoryImpl
import com.example.mymovieapp.utils.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class PopularMovieViewModel : BaseViewModel(){

    private val repository : MovieRepository = MovieRepositoryImpl()

    val popularMovies : MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    var popularMoviesPage = 1
    private var popularMovieResponse : MovieResponse? = null

    fun getPopularMovie() = viewModelScope.launch{
        safePopularMovies()
    }

    private suspend fun safePopularMovies() {

        popularMovies.postValue(Resource.Loading())

        try {

            if (hasInternetConnection()) {
                val response = repository.getPopularMovies(popularMoviesPage)
                popularMovies.postValue(handlePopularMoviesResponse(response))
            } else {
                popularMovies.postValue(Resource.Error("No internet connection"))
            }
        } catch (throwable : Throwable) {
            when(throwable) {
                is IOException -> popularMovies.postValue(Resource.Error("Network failure"))
                else -> popularMovies.postValue(Resource.Error("Conversion error"))
            }
        }
    }


    private fun handlePopularMoviesResponse( response: Response<MovieResponse>) : Resource<MovieResponse> {

        if (response.isSuccessful) {
            response.body()?.let { movieResponse ->
                popularMoviesPage++

                if (popularMovieResponse == null) {
                    popularMovieResponse = movieResponse
                } else {
                    val oldMovies = popularMovieResponse?.movieList
                    val newMovies = movieResponse.movieList

                    oldMovies?.addAll(newMovies)
                }
                return Resource.Success(popularMovieResponse ?: movieResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun hasInternetConnection(): Boolean {

/*
        val connectivityManager = Application().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_BLUETOOTH -> true
                    else -> false
                }
            }
        }

         */
        return true
    }
}