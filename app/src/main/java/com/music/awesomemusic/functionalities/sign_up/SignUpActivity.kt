package com.music.awesomemusic.functionalities.sign_up

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.music.awesomemusic.R
import com.music.awesomemusic.databinding.ActivitySignUpBinding
import com.music.awesomemusic.databinding.ActivityStartBinding
import com.music.awesomemusic.di.Injectable
import com.music.awesomemusic.di.ViewModelInjectionFactory
import com.music.awesomemusic.functionalities.login.LoginActivity
import com.music.awesomemusic.functionalities.start.StartActivity
import com.music.awesomemusic.functionalities.start.StartVM
import javax.inject.Inject

class SignUpActivity : AppCompatActivity(), Injectable {
    private val _TAG = SignUpActivity::class.java.name

    private lateinit var _binding: ActivitySignUpBinding
    private lateinit var _viewModel: SignUpVM

    private lateinit var _sharedPref: SharedPreferences

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelInjectionFactory<SignUpVM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initLogic()
    }

    private fun initBinding() {
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        _viewModel = ViewModelProviders.of(this, viewModelInjectionFactory).get(SignUpVM::class.java)
        _binding.viewModel = _viewModel
    }

    private fun initLogic() {
        _viewModel.event.observe(this, Observer { event ->
            when (event) {
                SignUpState.RegistrationSuccess -> {
                    showRegistrationSuccess()
                }
                is SignUpState.Error -> {
                    //TODO: Create beautiful alert
                    Toast.makeText(applicationContext, event.message, Toast.LENGTH_LONG).show()
                }
                is SignUpState.ValidationError -> {
                    //TODO: Create some kinda of message on the screen for validation errors
                    Toast.makeText(applicationContext, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showRegistrationSuccess() {
        TODO("Implement alert that shows success registration message.")
    }

    private fun goToLogin() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        finish()
    }
}