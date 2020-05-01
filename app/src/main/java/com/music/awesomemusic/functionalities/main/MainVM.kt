package com.music.awesomemusic.functionalities.main

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.awesomemusic.data.model.responses.MusicResponse
import com.music.awesomemusic.data.repository.UserRepository
import javax.inject.Inject

class MainVM @Inject constructor(var service: UserRepository) : ViewModel() {

    private val _TAG = MainVM::class.java.simpleName

    var listOfLetters = MutableLiveData<List<MusicResponse>>()

    var event = MutableLiveData<MainState>()

    var composerObservable = ObservableField<String>()
    var titleObservable = ObservableField<String>()


    init {
        event.value = MainState.Wait
    }


    fun fetchMusic() {
    }

}