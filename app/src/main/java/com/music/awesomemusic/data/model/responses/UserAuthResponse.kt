package com.music.awesomemusic.data.model.responses

import com.google.gson.annotations.SerializedName

data class UserAuthResponse(
    @SerializedName("responce") val response: Boolean,
    @SerializedName("userid") val id: Int,
    @SerializedName("username") val username: String
)