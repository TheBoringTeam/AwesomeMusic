package com.music.awesomemusic.data.model.requests

import com.google.gson.annotations.SerializedName

data class RequestSignUp(
        @SerializedName("username")
        val username: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("is_collective")
        val isCollective: Boolean
)