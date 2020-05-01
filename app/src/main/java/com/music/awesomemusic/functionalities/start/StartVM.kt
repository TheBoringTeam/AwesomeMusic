package com.music.awesomemusic.functionalities.start

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.awesomemusic.data.model.responses.UserAuthResponse
import com.music.awesomemusic.data.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class StartVM @Inject constructor(val service: UserRepository) : ViewModel() {

    private val _TAG = StartVM::class.java.simpleName

    var event = MutableLiveData<StartState>()


    fun validateUserAuth() {
    }
}