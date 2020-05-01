package com.music.awesomemusic.functionalities.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.awesomemusic.data.model.responses.UserAuthResponse
import com.music.awesomemusic.data.model.UserDetailedInfo
import com.music.awesomemusic.data.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginVM @Inject constructor(val service: UserRepository) : ViewModel() {

    private val _TAG = LoginVM::class.java.simpleName

    var usernameObservable = ObservableField<String>()
    var passwordObservable = ObservableField<String>()

    var event = MutableLiveData<LoginState>()

    init {
        event.value = LoginState.Wait
    }


    fun login() {
    }


    fun signUp() {
        event.value = LoginState.SignUp
    }
}