package com.music.awesomemusic.functionalities.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.awesomemusic.data.model.requests.RequestSignIn
import com.music.awesomemusic.data.model.responses.ResponseSignIn
import com.music.awesomemusic.data.repository.AccountApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginVM @Inject constructor(private val _accountApiService: AccountApiService) : ViewModel() {

    private val _TAG = LoginVM::class.java.simpleName

    var usernameObservable = ObservableField<String>()
    var passwordObservable = ObservableField<String>()

    var event = MutableLiveData<LoginState>()

    init {
        event.value = LoginState.Wait
    }


    fun login() {
        val username = usernameObservable.get().toString()
        val password = passwordObservable.get().toString()
        Log.i(_TAG, "Username : $username, password $password")
        val signInCall = _accountApiService.signIn(RequestSignIn(username, password))
        signInCall.enqueue(object : Callback<ResponseSignIn> {
            override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                Log.e(_TAG, "[login] Connection error: ${t.message}")
                event.value = LoginState.Error("Servers are currently unavailable. Try later")
            }

            override fun onResponse(call: Call<ResponseSignIn>, response: Response<ResponseSignIn>) {

                when (response.code()) {
                    200 -> {
                        Log.i(_TAG, "[login] Login successful.")
                        event.value = LoginState.LoginSuccessful(response.body()!!.token)
                    }
                    403 -> {
                        Log.i(_TAG, "[login] User provided wrong credentials.")
                        event.value = LoginState.WrongCredentials
                    }
                    else -> {
                        Log.e(_TAG, "[login] Unhandled response code: ${response.code()}")
                        event.value = LoginState.Error("Servers are currently unavailable. Try later")
                    }
                }
            }

        })
    }


    fun signUp() {
        event.value = LoginState.SignUp
    }
}