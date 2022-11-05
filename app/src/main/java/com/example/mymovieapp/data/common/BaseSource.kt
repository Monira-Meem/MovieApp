package com.example.mymovieapp.data.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import org.json.JSONTokener

import retrofit2.Response
import java.io.IOException

abstract class BaseSource {
/*
    protected suspend fun <T : Any> safeApiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO, call: suspend () -> Response<T>): Resource<T> {
        var response: Response<T>? = null
        return withContext(dispatcher) {
            try {
                response = call()
            } catch (ioException: IOException) {
                return@withContext Resource.Error(ConnectionException(), response?.code() ?: -1)
            } catch (nullPointer: NullPointerException) {
                Resource.Error(nullPointer, response?.code() ?: -1)
            } catch (throwable: Throwable) {
                return@withContext Resource.Error(
                    ExceptionHandler.parseHttpException(throwable),
                    response?.code() ?: -1
                )
            }
            response.let {res->
                res?.let { it ->
                    if (it.isSuccessful) {
                        response?.body()?.let {body->
                            Resource.Success(body)
                        } ?: Resource.Error(EmptyResponseBodyException(), it.code())
                    } else{
                        //Resource.Error(UnknownException( it.message()), it.code())
                        Resource.Error(UnknownException(getParsedErrorMessage(it.errorBody()) ?: it.message()), it.code())
                    }
                } ?: Resource.Error(ConnectionException(), response?.code() ?: -1)
            }
        }
    }

    private fun getParsedErrorMessage(responseBody: ResponseBody?): String? {
        responseBody?.string()?.let {
            val json = JSONTokener(it).nextValue()
            if (json is JSONObject) {
                if(json.has("message")) {
                    return json.getString("message")
                }
            }
        }
        return null
    }

 */

}

