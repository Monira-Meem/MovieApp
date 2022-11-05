package com.example.mymovieapp.data.model.singlemovie


import com.google.gson.annotations.SerializedName

data class MovieDetails(
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String?,
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val status: String?,
    val tagline: String?,
    val title: String?,
)