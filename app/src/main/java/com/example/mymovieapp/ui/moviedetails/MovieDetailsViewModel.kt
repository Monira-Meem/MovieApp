package com.example.mymovieapp.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovieapp.data.model.singlemovie.MovieDetails
import com.example.mymovieapp.data.repository.MovieRepository
import com.example.mymovieapp.data.repository.MovieRepositoryImpl
import com.example.mymovieapp.utils.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailsViewModel : BaseViewModel() {

    private val repository : MovieRepository = MovieRepositoryImpl()

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails : LiveData<MovieDetails> = _movieDetails

    fun getMovieDetails(movieId : Int) {

        val disposable = repository.getMovieDetails(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{showLoader.postValue(true)}
            .doFinally{showLoader.value = false}
            .subscribe({
                showLoader.value = false
                _movieDetails.postValue(it)
            },{
                val error = "error"
            })

    }

    /*
    showLoader.value = true
    repository.getMovieDetails(movieId, {
            showLoader.value = false
            movieDetailsLiveData.postValue(it)
        }, {
            showLoader.value = false
            toastMessage.postValue(it)
        })
     */

}