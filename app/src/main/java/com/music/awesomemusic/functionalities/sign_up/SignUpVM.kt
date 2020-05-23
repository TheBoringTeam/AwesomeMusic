package com.music.awesomemusic.functionalities.sign_up

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.awesomemusic.data.model.requests.RequestSignUp
import com.music.awesomemusic.data.model.responses.ErrorResponse
import com.music.awesomemusic.data.repository.AccountApiService
import com.music.awesomemusic.utils.ErrorUtils
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import javax.inject.Inject


class SignUpVM @Inject constructor(private val _accountService: AccountApiService) : ViewModel() {

    private val _TAG = SignUpVM::class.java.simpleName

    var event = MutableLiveData<SignUpState>()


    var passwordObservable = ObservableField("")
    var passwordConfirmObservable = ObservableField("")
    var emailObservableField = ObservableField("")
    var usernameObservableField = ObservableField("")

    fun signUp() {
        if (!isValidCredentials()) {
            return
        }

        // TODO: Change isCollective to some state after specification gonna be ready
        val signUpCall = _accountService.signUp(
                RequestSignUp(
                        usernameObservableField.get().toString(),
                        passwordObservable.get().toString(), emailObservableField.get().toString(), false
                )
        )

        signUpCall.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e(_TAG, "[signUp] Error during registration process")
                event.value = SignUpState.Error("Server error. Please again try later.")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                when (response.code()) {
                    201 -> { //CREATED - user signed up OK
                        Log.d(_TAG, "[signUp] User was created successfully")
                        event.value = SignUpState.RegistrationSuccess
                    }
                    400 -> {
                        //Note: DO NOT FUCKING INVOKE response.errorBody().string() . It converts errorBody to string forever. I spend 2 hours to find this bug :(
                        Log.i(_TAG, "[signUp] Got status code: ${response.code()}")
                        val error = ErrorUtils.parseError(response)
                        event.value = SignUpState.ValidationError(error.message)
                    }
                    else -> {
                        Log.e(_TAG, "[signUp] Unhandled error code: ${response.code()}")
                        event.value = SignUpState.Error("Server error. Please again try later.")
                    }
                }
            }
        })
    }

    // Idk, may be it's kinda wise decision to move this part of code to some separate class. MAY BE
    private fun isValidCredentials(): Boolean {
        val password = passwordObservable.get().toString()
        val passwordConfirm = passwordConfirmObservable.get().toString()
        val email = emailObservableField.get().toString()
        val username = usernameObservableField.get().toString()

        if (password != passwordConfirm) {
            Log.d(_TAG, "[validate] provided password are not the same")
            event.value = SignUpState.ValidationError("Passwords are not the same")
            return false
        }

        if (username.length < 6) {
            event.value = SignUpState.ValidationError("Username has to be at least 6 characters")
            return false
        }

        if (username.length > 16) {
            event.value =
                    SignUpState.ValidationError("Username has to be no more than 16 characters")
            return false
        }

        if (email.length < 3 || email.length > 254) {
            event.value = SignUpState.ValidationError("Email is not valid")
            return false
        }

        return true
    }
}