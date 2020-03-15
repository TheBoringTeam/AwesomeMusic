package com.music.awesomemusic.data.model

import com.google.gson.annotations.SerializedName

data class UserAuthResponse(
    @SerializedName("responce") val response: Boolean,
    @SerializedName("userid") val id: Int,
    @SerializedName("username") val username: String
)