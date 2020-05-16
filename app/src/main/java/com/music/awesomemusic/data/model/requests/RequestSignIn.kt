package com.music.awesomemusic.data.model.requests

import com.google.gson.annotations.SerializedName

data class RequestSignIn(
        @SerializedName("username")
        var username: String,
        @SerializedName("password")
        var password: String)