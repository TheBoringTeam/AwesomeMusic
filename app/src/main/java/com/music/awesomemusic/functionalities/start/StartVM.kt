package com.music.awesomemusic.functionalities.start

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.awesomemusic.data.model.responses.ResponseAuthorization
import com.music.awesomemusic.data.model.responses.UserAuthResponse
import com.music.awesomemusic.data.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class StartVM @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _TAG = StartVM::class.java.simpleName

    var event = MutableLiveData<StartState>()

    fun authorizeUser(token: String) {
        // if token is empty, then go to login
        if (token.isEmpty()) {
            event.value = StartState.UserAuthFail
            return
        }

        val tokenCall = userRepository.authorizeUser(token)

        tokenCall.enqueue(object : Callback<ResponseAuthorization> {
            override fun onFailure(call: Call<ResponseAuthorization>, t: Throwable) {
                Log.e(_TAG, "[authorizeUser] Error during pulling auth info: ${t.message}")
                event.value = StartState.Error("Servers are currently unavailable. Try later.")
            }

            override fun onResponse(call: Call<ResponseAuthorization>, response: Response<ResponseAuthorization>) {
                when (response.code()) {
                    200 -> {
                        // Auth is correct
                        Log.i(_TAG, "[authorizeUser] Token is valid.")
                        event.value = StartState.UserAuthCorrect(response.body()!!)
                    }
                    403 -> {
                        Log.i(_TAG, "[authorizeUser] Token is not valid.")
                        event.value = StartState.UserAuthFail
                    }
                    else -> {
                        Log.e(_TAG, "[authorizeUser] Unhandled response code ${response.code()}")
                        event.value = StartState.Error("Servers are currently unavailable. Try later.")
                    }
                }
            }

        })
    }
}