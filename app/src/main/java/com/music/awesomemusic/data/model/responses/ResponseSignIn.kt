package com.music.awesomemusic.data.model.responses

import com.google.gson.annotations.SerializedName

data class ResponseSignIn(
        @SerializedName("token")
        val token: String
)