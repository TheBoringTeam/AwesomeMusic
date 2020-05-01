package com.music.awesomemusic.data.model.responses

import com.google.gson.annotations.SerializedName
import java.util.*

data class LetterResponse(
    @SerializedName("LetterID") val id: Int,
    @SerializedName("LetterName") val name: String,
    @SerializedName("Link") val link: String,
    @SerializedName("Comment") val comment: String,
    @SerializedName("CreateDate") val createDate: Date,
    @SerializedName("ChangeDate") val changeDate: Date
)

