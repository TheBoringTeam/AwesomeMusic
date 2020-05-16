package com.music.awesomemusic.functionalities.start

import com.music.awesomemusic.data.model.responses.ResponseAuthorization

sealed class StartState {
    data class Error(val message: String) : StartState()
    data class UserAuthCorrect(val response: ResponseAuthorization) : StartState()
    object UserEmpty : StartState()
    object UserAuthFail : StartState()
}