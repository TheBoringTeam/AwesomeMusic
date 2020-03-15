package com.music.awesomemusic.functionalities.login

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginVM @Inject constructor() : ViewModel() {
    var usernameObservable = ObservableField<String>()
    var passwordObservable = ObservableField<String>()


    fun login() {

    }


    fun signUp() {

    }
}