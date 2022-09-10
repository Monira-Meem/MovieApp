package com.example.mymovieapp.ui.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovieapp.data.model.Movie
import com.example.mymovieapp.data.model.MovieResponse
import com.example.mymovieapp.data.repository.MovieRepository
import com.example.mymovieapp.data.repository.MovieRepositoryImpl
import com.example.mymovieapp.utils.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.awaitResponse

class PopularMovieViewModel : BaseViewModel(){

    private val repository : MovieRepository = MovieRepositoryImpl()

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

    private var movieResponse : MovieResponse? = null
    var popularMoviesPage = 1

    fun getPopularMovie() {
        val disposable = repository.getPopularMovies(popularMoviesPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{showLoader.postValue(true)}
            .doFinally{showLoader.value = false}
            .subscribe({
                showLoader.value = false
                _movieList.postValue(it.movieList)


            },{
                val error = "error"
            })
    }



    fun getDummyData() : List<Movie> {
        val list = ArrayList<Movie>()

        val movie = Movie(title = "Hello World", releaseDate = "2020")
        list.add(movie)
        list.add(movie)
        list.add(movie)

        return list
    }



}