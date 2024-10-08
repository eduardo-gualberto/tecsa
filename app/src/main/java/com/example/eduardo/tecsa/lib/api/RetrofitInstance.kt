package com.example.eduardo.tecsa.lib.api

import com.example.eduardo.tecsa.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    fun retrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(
            OkHttpClient.Builder().addInterceptor { chain ->
                val url = chain.request().url.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.tmdbApiKey).build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }.build()
        ).addConverterFactory(GsonConverterFactory.create()).build()
    }
}