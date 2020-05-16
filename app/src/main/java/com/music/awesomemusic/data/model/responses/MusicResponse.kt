package com.music.awesomemusic.data.model.responses

import com.google.gson.annotations.SerializedName

data class MusicResponse(
    @SerializedName("OpusTitle") val title: String,
    @SerializedName("ComposerName") val author: String,
    @SerializedName("PerformerName") val performerName: String,
    @SerializedName("Link") val link: String
)