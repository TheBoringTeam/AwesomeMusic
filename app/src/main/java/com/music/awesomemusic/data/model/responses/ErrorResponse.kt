package com.music.awesomemusic.data.model.responses

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
        @SerializedName("timestamp")
        val time: String,

        @SerializedName("status")
        val status: Int,

        @SerializedName("error")
        val errorType: String,

        @SerializedName("message")
        val message: String,

        @SerializedName("path")
        val path: String
)