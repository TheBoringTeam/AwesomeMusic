package com.music.awesomemusic.di.modules

import com.music.awesomemusic.data.repository.UserApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RepositoryModule {
    //    private val baseUrl = "http://10.0.2.2:8080"
    private val baseUrl = "http://34.90.124.7:8082"

    @Provides
    @Singleton
    fun providesApiService(): UserApiService {
        val retrofit: Retrofit
        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(UserApiService::class.java)
    }
}