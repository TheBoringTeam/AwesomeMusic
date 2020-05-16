package com.music.awesomemusic.functionalities.start

import android.animation.ObjectAnimator
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Pair
import android.view.View
import android.view.animation.Animation
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
        initLogic()

//        Handler().postDelayed(object : Runnable{
//            override fun run() {
//                initLogic()
//            }
//
//        }, 2000)
    }

    private fun initLogic() {
        _sharedPref = applicationContext.getSharedPreferences(
                getString(R.string.preference_key), Context.MODE_PRIVATE
        )

        _viewModel.authorizeUser(DataUtils.getToken(_sharedPref))
    }

    private fun initBinding() {
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_start)
        _viewModel = ViewModelProviders.of(this, viewModelInjectionFactory).get(StartVM::class.java)
        _binding.viewModel = _viewModel

        val logoAnim = startLogoAnimation()

        _viewModel.event.observe(this, Observer { event ->
            when (event) {
                is StartState.Error -> { // if error, then show error message with animation
                    stopLogoAnimation(logoAnim)
                    showErrorAnimation(event.message)
                }
                is StartState.UserAuthCorrect -> { // if correct auth, then save to cache and goToMain
                    DataUtils.saveUserObject(_sharedPref, event.response)
                    goToMain()
                }
                StartState.UserAuthFail -> { // if invalid token, then go to login
                    goToLogin()
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
//        val pair = Pair<View, String>(start_activity_logo, "start_logo")
//        val options = ActivityOptions.makeSceneTransitionAnimation(this, pair)

        startActivity(loginIntent)
        finish()
    }

    private fun startLogoAnimation(): ObjectAnimator {
        return ObjectAnimator.ofFloat(start_activity_logo, "rotation", 360f).apply {
            repeatCount = Animation.INFINITE
            duration = 5000
            start()
        }
    }

    private fun stopLogoAnimation(anim: ObjectAnimator) {
        anim.cancel()
        ObjectAnimator.ofFloat(start_activity_logo, "rotation", 360f).apply {
            duration = 2000
            start()
        }
        ObjectAnimator.ofFloat(start_activity_logo, "translationY", -200f).apply {
            duration = 2000
            start()
        }
    }

    private fun showErrorAnimation(message: String) {
        start_activity_error.text = message
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