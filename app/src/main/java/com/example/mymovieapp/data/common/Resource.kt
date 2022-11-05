package com.example.mymovieapp.data.common

//sealed class Resource<out T : Any> {
//    data class Success<out T : Any>(val responseData: T) : Resource<T>()
//    data class Error(val exception: Exception, val code: Int) : Resource<Nothing>()
//    class Loading<out T : Any> : Resource<T>()
//}

sealed class Resource<T> (
    val data : T? = null,
    val message : String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String , data: T? = null) : Resource<T>(data,message)
    class Loading<T> : Resource<T>()
}