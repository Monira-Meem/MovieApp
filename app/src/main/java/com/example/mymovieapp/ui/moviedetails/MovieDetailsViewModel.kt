package com.example.mymovieapp.ui.moviedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.data.common.Resource
import com.example.mymovieapp.data.model.singlemovie.MovieDetails
import com.example.mymovieapp.data.repository.MovieRepository
import com.example.mymovieapp.data.repository.MovieRepositoryImpl
import com.example.mymovieapp.utils.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MovieDetailsViewModel : BaseViewModel() {

    private val repository : MovieRepository = MovieRepositoryImpl()

    val movieDetails : MutableLiveData<Resource<MovieDetails>> = MutableLiveData()
    var movieDetailsResponse : MovieDetails? = null


    fun getMovieDetails(movieId : Int) =  viewModelScope.launch{
        safeMovieDetails(movieId)
    }

    private suspend fun safeMovieDetails(movieId: Int) {

        movieDetails.postValue(Resource.Loading())

        try {

            if (hasInternetConnection()) {
                val response = repository.getMovieDetails(movieId)
                movieDetails.postValue(handleMovieDetailsResponse(response))
            } else {
                movieDetails.postValue(Resource.Error("No internet connection"))
            }
        } catch (throwable : Throwable) {
            when(throwable) {
                is IOException -> movieDetails.postValue(Resource.Error("Network failure"))
                else -> movieDetails.postValue(Resource.Error("Conversion error"))
            }
        }
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
                capabilities.hasTransport(NetworkoiCapabilities.TRANSPORT_CELLULAR) -> true
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

    private fun handleMovieDetailsResponse(response : Response<MovieDetails>) : Resource<MovieDetails> {

        if (response.isSuccessful) {
            response.body()?.let { movieResponse ->
                if (movieDetailsResponse == null) {
                    movieDetailsResponse = movieResponse
                }
                return Resource.Success(movieDetailsResponse ?: movieResponse)
            }
        }
        return Resource.Error(response.message())
    }

}