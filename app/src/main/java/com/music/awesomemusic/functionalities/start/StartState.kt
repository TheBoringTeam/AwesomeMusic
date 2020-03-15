package com.music.awesomemusic.functionalities.start

sealed class StartState {
    data class Error(val message: String) : StartState()
    object UserAuthCorrect : StartState()
    object UserEmpty : StartState()
}