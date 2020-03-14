package com.music.awesomemusic.functionalities.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.awesomemusic.data.model.LetterResponse
import com.music.awesomemusic.data.repository.AwesomeMusicApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainVM @Inject constructor(var service: AwesomeMusicApiService) : ViewModel() {

    private val _TAG = MainVM::class.java.simpleName

    var listOfLetters = MutableLiveData<List<LetterResponse>>()

    var event = MutableLiveData<MainState>()


    init {
        event.value = MainState.Wait
    }


    fun fetchLetters() {
        val callLetters = service.getAllLetters()
        callLetters.enqueue(object : Callback<List<LetterResponse>> {
            override fun onFailure(call: Call<List<LetterResponse>>, t: Throwable) {
                Log.i(_TAG, "Some error ${t.message}")
            }

            override fun onResponse(
                call: Call<List<LetterResponse>>,
                response: Response<List<LetterResponse>>
            ) {
                when (response.code()) {
                    200 -> {
                        Log.i(_TAG, "Letters list successful")
                        listOfLetters.value = response.body()!!
                    }
                    else -> {
                        Log.e(_TAG, "Unhandled status code")
                        event.value = MainState.Error("Unhandled code ${response.code()}")
                    }
                }
            }

        })

    }

}