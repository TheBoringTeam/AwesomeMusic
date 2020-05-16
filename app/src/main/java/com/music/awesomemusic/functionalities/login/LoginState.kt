package com.music.awesomemusic.functionalities.login

import com.music.awesomemusic.data.model.UserDetailedInfo

sealed class LoginState {
    object Wait : LoginState()
    object SignUp : LoginState()
    data class Error(val message: String) : LoginState()
    data class LoginSuccessful(val token: String) : LoginState()
    object WrongCredentials : LoginState()
}