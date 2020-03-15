package com.music.awesomemusic.functionalities.start

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.awesomemusic.data.model.UserAuthResponse
import com.music.awesomemusic.data.repository.AwesomeMusicApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class StartVM @Inject constructor(val service: AwesomeMusicApiService) : ViewModel() {

    private val _TAG = StartVM::class.java.simpleName

    var event = MutableLiveData<StartState>()


    fun validateUserAuth(login: String, password: String) {
        val callUserAuth = service.getAuthUser(login, password)
        callUserAuth.enqueue(object : Callback<List<UserAuthResponse>> {
            override fun onFailure(call: Call<List<UserAuthResponse>>, t: Throwable) {
                Log.e(_TAG, "Error while pulling user authentication : ${t.message}")
            }

            override fun onResponse(
                call: Call<List<UserAuthResponse>>,
                response: Response<List<UserAuthResponse>>
            ) {
                when (response.code()) {
                    200 -> {
                        Log.i(_TAG, "Successful pulling user authentication")
                        event.value = StartState.UserAuthCorrect
                    }
                    else -> {
                        Log.i(_TAG, "User is empty")
                        event.value = StartState.UserEmpty
                    }
                }
            }
        })
    }
}