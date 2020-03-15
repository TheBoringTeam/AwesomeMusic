package com.music.awesomemusic.utils

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.music.awesomemusic.data.model.UserDetailedInfo

class DataUtils {

    companion object {
        fun getUserObject(sharedPreference: SharedPreferences): UserDetailedInfo? {
            val gson = Gson()
            val json: String = sharedPreference.getString("userDetails", "").toString()
            val userInfo = gson.fromJson(json, UserDetailedInfo::class.java)
            if (userInfo != null) {
                Log.i(
                    DataUtils::class.java.simpleName,
                    "User was pulled from shared pref : username : ${userInfo.username}, id : ${userInfo.id}"
                )
            } else {
                Log.i(
                    DataUtils::class.java.simpleName,
                    "User Auth is empty"
                )
            }

            return userInfo
        }
    }
}