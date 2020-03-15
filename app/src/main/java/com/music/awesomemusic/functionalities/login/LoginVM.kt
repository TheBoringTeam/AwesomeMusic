package com.music.awesomemusic.functionalities.login

import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.awesomemusic.data.model.UserAuthResponse
import com.music.awesomemusic.data.model.UserDetailedInfo
import com.music.awesomemusic.data.repository.AwesomeMusicApiService
import com.music.awesomemusic.functionalities.start.StartState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginVM @Inject constructor(val service: AwesomeMusicApiService) : ViewModel() {

    private val _TAG = LoginVM::class.java.simpleName

    var usernameObservable = ObservableField<String>()
    var passwordObservable = ObservableField<String>()

    var event = MutableLiveData<LoginState>()

    init {
        event.value = LoginState.Wait
    }


    fun login() {
        val username = usernameObservable.get()!!
        val password = passwordObservable.get()!!

        val callUserAuth =
            service.getAuthUser(username, password)
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
                        val responseAuthUser = response.body()!![0]

                        event.value = LoginState.LoginSuccessful(
                            UserDetailedInfo(
                                responseAuthUser.id,
                                username,
                                password
                            )
                        )
                    }
                    else -> {
                        Log.i(_TAG, "User is empty")
                        event.value = LoginState.Error("Wrong credentials")
                    }
                }
            }
        })
    }


    fun signUp() {
        event.value = LoginState.SignUp
    }
}