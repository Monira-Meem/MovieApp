package com.example.mymovieapp.data.api

import com.example.mymovieapp.BuildConfig
import com.example.mymovieapp.utils.Constants
import com.example.mymovieapp.utils.Constants.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {

        private fun getOkHttpClient() : OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
                .callTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(getLogInterceptor(BuildConfig.DEBUG))
                .build()
        }

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build()

        }

        private fun getGson() : Gson {

            return GsonBuilder().setLenient().create()

        }

        private fun getLogInterceptor(isDebuggingEnabled : Boolean = false) : Interceptor {

            val builder = LoggingInterceptor.Builder()
                .setLevel(if (isDebuggingEnabled) Level.BASIC else Level.NONE)
                .log(Platform.INFO)
                .tag("MY_MOVIE_APP")
                .request("Request")
                .response("Response")
                .addQueryParam("api_key", Constants.API_KEY)

            builder.isDebugAble = isDebuggingEnabled
            return builder.build()
        }

        val api: MovieApiService by lazy {
            getRetrofit().create(MovieApiService::class.java)
        }
    }
}