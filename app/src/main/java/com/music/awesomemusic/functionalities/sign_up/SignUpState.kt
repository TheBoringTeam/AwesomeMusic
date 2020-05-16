package com.music.awesomemusic.functionalities.sign_up

sealed class SignUpState {
    data class ValidationError(val message: String) : SignUpState()
    data class Error(val message: String) : SignUpState()
    object RegistrationSuccess : SignUpState()
}