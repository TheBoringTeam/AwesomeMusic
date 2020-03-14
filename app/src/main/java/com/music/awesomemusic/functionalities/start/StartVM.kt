package com.music.awesomemusic.functionalities.start

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class StartVM @Inject constructor() : ViewModel() {

    var event = MutableLiveData<StartState>()


    fun validateUserAuth(login: String, password: String): Boolean {
        if (login.isEmpty() || password.isEmpty()) {
            return false
        }

        return true
    }
}