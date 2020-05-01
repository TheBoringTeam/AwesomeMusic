package com.music.awesomemusic.functionalities.start

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
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
import kotlinx.android.synthetic.main.activity_start.*
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

        initBinding()

        _sharedPref = applicationContext.getSharedPreferences(
            getString(R.string.preference_key), Context.MODE_PRIVATE
        )

        val userDetails = DataUtils.getUserObject(_sharedPref)
        if (userDetails != null) {

        } else {
            goToLogin()
        }
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

        initAnimations()
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


    private fun initAnimations() {
        val animation = startLoadingAnimation()

        test_btn.setOnClickListener {
            animation.cancel()
            stopLoadingAnimation()
            showError()
        }
    }

    private fun startLoadingAnimation(): ObjectAnimator {
        return ObjectAnimator.ofFloat(start_activity_logo, "rotation", 360f).apply {
            repeatCount = Animation.INFINITE
            duration = 5000
            start()
        }
    }

    private fun stopLoadingAnimation(){
        start_activity_logo.clearAnimation()
        ObjectAnimator.ofFloat(start_activity_logo, "rotation", 360f).apply {
            duration = 2000
            start()
        }
        ObjectAnimator.ofFloat(start_activity_logo, "translationY", -200f).apply {
            duration = 2000
            start()
        }
    }
    private fun showError(){
        ObjectAnimator.ofFloat(start_activity_error, "alpha", 1f).apply {
            duration = 2000
            start()
        }
        ObjectAnimator.ofFloat(start_activity_error, "translationY", -100f).apply {
            duration = 2000
            start()
        }
    }
}