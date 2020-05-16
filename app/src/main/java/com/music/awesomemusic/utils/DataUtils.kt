package com.music.awesomemusic.utils

import android.content.SharedPreferences
import com.google.gson.Gson
import com.music.awesomemusic.data.model.responses.ResponseAuthorization

class DataUtils {

    companion object {
        fun getUserObject(sharedPreference: SharedPreferences): ResponseAuthorization? {
            val gson = Gson()
            val json: String = sharedPreference.getString("userDetails", "").toString()

            return gson.fromJson(json, ResponseAuthorization::class.java)
        }

        fun getToken(sharedPreference: SharedPreferences): String {
            return sharedPreference.getString("token", "").toString()
        }

        fun saveToken(sharedPreference: SharedPreferences, token: String) {
            sharedPreference.edit().putString("token", token).apply()
        }

        fun saveUserObject(sharedPreference: SharedPreferences,
                           userDetailedInfo: ResponseAuthorization) {
            val gson = Gson()
            sharedPreference.edit()
                    .putString("userDetails", gson.toJson(userDetailedInfo)).apply()
        }

        fun deleteAllData(sharedPreference: SharedPreferences) {
            sharedPreference.edit().clear().apply()
        }
    }
}