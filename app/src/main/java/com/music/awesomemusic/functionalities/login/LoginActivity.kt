package com.music.awesomemusic.functionalities.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.music.awesomemusic.R
import com.music.awesomemusic.databinding.ActivityLoginBinding
import com.music.awesomemusic.databinding.ActivityStartBinding
import com.music.awesomemusic.di.Injectable
import com.music.awesomemusic.di.ViewModelInjectionFactory
import com.music.awesomemusic.functionalities.main.MainActivity
import com.music.awesomemusic.functionalities.start.StartActivity
import com.music.awesomemusic.functionalities.start.StartState
import com.music.awesomemusic.functionalities.start.StartVM
import com.music.awesomemusic.utils.DataUtils
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), Injectable {

    private val _TAG = LoginActivity::class.java.name

    private lateinit var _binding: ActivityLoginBinding
    private lateinit var _viewModel: LoginVM

    private lateinit var _sharedPref: SharedPreferences

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelInjectionFactory<LoginVM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _sharedPref = applicationContext.getSharedPreferences(
            getString(R.string.preference_key), Context.MODE_PRIVATE
        )

        initBinding()
    }

    private fun initBinding() {
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        _viewModel = ViewModelProviders.of(this, viewModelInjectionFactory).get(LoginVM::class.java)
        _binding.viewModel = _viewModel

        _viewModel.event.observe(this, Observer { event ->
            when (event) {
                LoginState.SignUp -> {

                }
                is LoginState.Error -> {
                    Toast.makeText(applicationContext, event.message, Toast.LENGTH_LONG).show()
                    _viewModel.event.value = LoginState.Wait
                }
                is LoginState.LoginSuccessful -> {
                    DataUtils.saveUserObject(_sharedPref, event.userDetailedInfo)
                    goToMain()
                }
            }
        })
    }

    private fun goToMain() {
        val mainIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }
}