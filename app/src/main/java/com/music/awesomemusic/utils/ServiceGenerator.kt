package com.music.awesomemusic.utils

import okhttp3.OkHttpClient

import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory


object ServiceGenerator {
    private const val BASE_URL = "http://34.90.124.7:8082"

    private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    val retrofit: Retrofit = builder.build()

    private val httpClient = OkHttpClient.Builder()

    fun <S> createService(serviceClass: Class<S>): S {
        builder.client(httpClient.build())
        return retrofit.create(serviceClass)
    }
}