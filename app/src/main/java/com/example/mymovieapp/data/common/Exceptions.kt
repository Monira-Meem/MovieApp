package com.example.mymovieapp.data.common

open class NetworkException(override val message: String = "") : Exception(message)

open class ConnectionException(override val message: String = "Check your internet connection") : Exception(message)

class UnknownHostException(override val message: String = "Unable to resolve host") : Exception(message)

class EmptyResponseBodyException(override val message: String = "Response body can not be empty") : Exception(message)

class RequestException(var httpCode: Int = 500, override var message: String = "") : NetworkException(message)

class UnknownException(message: String) : NetworkException(message)
