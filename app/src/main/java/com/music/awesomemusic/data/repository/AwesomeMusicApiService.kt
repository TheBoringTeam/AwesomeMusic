package com.music.awesomemusic.data.repository

import com.music.awesomemusic.data.model.LetterResponse
import com.music.awesomemusic.data.model.MusicResponse
import com.music.awesomemusic.data.model.UserAuthResponse
import retrofit2.Call
import retrofit2.http.*

interface AwesomeMusicApiService {
    @GET("/letters/")
    fun getAllLetters(): Call<List<LetterResponse>>

    @GET("/authorize")
    fun getAuthUser(
        @Query("user") user: String,
        @Query("hash") password: String
    ): Call<List<UserAuthResponse>>

    // http://52.47.202.76:4000/search/?token=1&mask_composer=Абако&mask_opus=Каприччио&page=1&count=10
    @GET("/search")
    fun getMusic(
        @Query("token") token: String = "1",
        @Query("mask_composer") composer: String,
        @Query("opus") opus: String,
        @Query("page") page: Int,
        @Query("count") amount: Int = 10
    ): Call<List<MusicResponse>>
}