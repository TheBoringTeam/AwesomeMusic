package com.music.awesomemusic.data.repository

import com.music.awesomemusic.data.model.requests.RequestSignIn
import com.music.awesomemusic.data.model.requests.RequestSignUp
import com.music.awesomemusic.data.model.responses.ResponseAuthorization
import com.music.awesomemusic.data.model.responses.ResponseSignIn
import com.music.awesomemusic.data.model.responses.UserAuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserApiService {
    @POST("/api/user/sign-in")
    fun signIn(@Body requestSignIn: RequestSignIn): Call<ResponseSignIn>

    @GET("/api/user/me")
    fun authorizeUser(@Header("Authorization") token: String): Call<ResponseAuthorization>

    @GET("/api/user/registration")
    fun signUp(@Body requestSignUp: RequestSignUp) : Call<ResponseBody>
}