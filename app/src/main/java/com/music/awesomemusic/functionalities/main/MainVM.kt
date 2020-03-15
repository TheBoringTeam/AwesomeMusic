package com.music.awesomemusic.functionalities.main

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.awesomemusic.data.model.LetterResponse
import com.music.awesomemusic.data.model.MusicResponse
import com.music.awesomemusic.data.repository.AwesomeMusicApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainVM @Inject constructor(var service: AwesomeMusicApiService) : ViewModel() {

    private val _TAG = MainVM::class.java.simpleName

    var listOfLetters = MutableLiveData<List<MusicResponse>>()

    var event = MutableLiveData<MainState>()

    var composerObservable = ObservableField<String>()
    var titleObservable = ObservableField<String>()


    init {
        event.value = MainState.Wait
    }


    fun fetchMusic() {

        var composer = composerObservable.get()
        var title = titleObservable.get()
        if (title != null && composer != null) {
            if (title.isEmpty()) {
                title = " "
            }

            if (composer.isEmpty()) {
                composer = " "
            }

            val callLetters = service.getMusic("1", composer, title, 1, 10)
            callLetters.enqueue(object : Callback<List<MusicResponse>> {
                override fun onFailure(call: Call<List<MusicResponse>>, t: Throwable) {
                    Log.i(_TAG, "Some error ${t.message}")
                }

                override fun onResponse(
                    call: Call<List<MusicResponse>>,
                    response: Response<List<MusicResponse>>
                ) {
                    when (response.code()) {
                        200 -> {
                            Log.i(_TAG, "Music list successful")
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

}