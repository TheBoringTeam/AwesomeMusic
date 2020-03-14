package com.music.awesomemusic.data.repository

import com.music.awesomemusic.data.model.Letter
import retrofit2.Call
import retrofit2.http.*

interface AwesomeMusicApiService {
    @GET("/letters/")
    fun getAllLetters(): Call<List<Letter>>
}