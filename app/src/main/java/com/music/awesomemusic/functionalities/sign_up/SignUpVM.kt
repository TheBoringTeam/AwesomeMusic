package com.music.awesomemusic.functionalities.sign_up

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SignUpVM @Inject constructor() : ViewModel() {
    var passwordObservable = ObservableField("")
    var passwordConfirmObservable = ObservableField("")
    var emailObservableField = ObservableField("")
    var usernameObservableField = ObservableField("")

    fun next() {

    }
}