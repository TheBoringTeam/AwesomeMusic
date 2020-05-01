package com.music.awesomemusic.data.repository

import com.music.awesomemusic.data.model.requests.RequestSignIn
import com.music.awesomemusic.data.model.responses.ResponseSignIn
import com.music.awesomemusic.data.model.responses.UserAuthResponse
import retrofit2.Call
import retrofit2.http.*

interface UserRepository {
    @GET("/api/user/sign-in")
    fun getAuthUser(@Body requestSignIn: RequestSignIn): Call<ResponseSignIn>
}