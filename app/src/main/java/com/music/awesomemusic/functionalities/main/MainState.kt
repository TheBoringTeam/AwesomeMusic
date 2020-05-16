package com.music.awesomemusic.functionalities.main

sealed class MainState {
    data class Error(val message: String) : MainState()
    object Wait : MainState()
    object LogOut : MainState()
}