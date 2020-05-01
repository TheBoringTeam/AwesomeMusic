package com.music.awesomemusic.data.model.responses

import com.google.gson.annotations.SerializedName

data class ResponseAuthorization(
        @SerializedName("username")
        val username: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("is_activated")
        val isActivated: Boolean,
        @SerializedName("is_admin")
        val isAdmin: Boolean
)