package com.example.mymovieapp.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun loadImage(view : AppCompatImageView, url : String) {
    Glide.with(view).load("https://image.tmdb.org/t/p/w500$url").into(view)
}