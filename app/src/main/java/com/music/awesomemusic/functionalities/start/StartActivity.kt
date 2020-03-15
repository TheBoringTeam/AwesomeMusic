package com.music.awesomemusic.functionalities.start

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
import com.music.awesomemusic.databinding.ActivityStartBinding
import com.music.awesomemusic.di.Injectable
import com.music.awesomemusic.di.ViewModelInjectionFactory
import com.music.awesomemusic.functionalities.login.LoginActivity
import com.music.awesomemusic.functionalities.main.MainActivity
import com.music.awesomemusic.utils.DataUtils
import javax.inject.Inject

class StartActivity : AppCompatActivity(), Injectable {

    private val _TAG = StartActivity::class.java.name

    private lateinit var _binding: ActivityStartBinding
    private lateinit var _viewModel: StartVM

    private lateinit var _sharedPref: SharedPreferences

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelInjectionFactory<StartVM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        initBinding()

        _sharedPref = applicationContext.getSharedPreferences(
            getString(R.string.preference_key), Context.MODE_PRIVATE
        )

        val userDetails = DataUtils.getUserObject(_sharedPref)
        if (userDetails != null) {
            _viewModel.validateUserAuth(userDetails.username, userDetails.password)
        }

        goToLogin()

    }

    private fun initBinding() {
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_start)
        _viewModel = ViewModelProviders.of(this, viewModelInjectionFactory).get(StartVM::class.java)
        _binding.viewModel = _viewModel

        _viewModel.event.observe(this, Observer { event ->
            when (event) {
                is StartState.Error -> {
                    Toast.makeText(applicationContext, event.message, Toast.LENGTH_SHORT).show()
                }
                StartState.UserEmpty -> {
                    goToLogin()
                }
                StartState.UserAuthCorrect -> {
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

    private fun goToLogin() {
        val loginIntent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(loginIntent)
        finish()
    }
}