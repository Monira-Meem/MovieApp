package com.example.mymovieapp.utils

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadFromResource(imageName : String) {

    val resource = context.resources
    val imageRes = resource.getIdentifier(imageName,"drawable",context.packageName)

    Glide.with(context).load(imageRes).into(this)
}