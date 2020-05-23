package com.music.awesomemusic.utils

import android.util.Log
import com.music.awesomemusic.data.model.responses.ErrorResponse
import okhttp3.ResponseBody
import retrofit2.Response

class ErrorUtils {
    companion object {
        fun parseError(response: Response<ResponseBody>): ErrorResponse {
            val converter = ServiceGenerator.retrofit.responseBodyConverter<ErrorResponse>(ErrorResponse::class.javaObjectType, arrayOfNulls<Annotation>(0))
            return converter.convert(response.errorBody()!!)
        }
    }
}