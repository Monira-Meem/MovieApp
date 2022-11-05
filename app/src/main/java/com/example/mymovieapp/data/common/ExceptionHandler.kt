package com.example.mymovieapp.data.common

import java.net.UnknownHostException

object ExceptionHandler {

    fun parseHttpException(throwable: Throwable): Exception {
        throwable.printStackTrace()
        return when (throwable) {
            is UnknownHostException -> UnknownHostException()
            else -> {
                throwable.message?.let { message ->
                    return@let NetworkException(message)
                } ?: run {
                    return@run UnknownException("")
                }
            }
        }
    }

}
