package com.music.awesomemusic.functionalities.login

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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
import com.music.awesomemusic.functionalities.sign_up.SignUpActivity
import com.music.awesomemusic.functionalities.start.StartActivity
import com.music.awesomemusic.functionalities.start.StartState
import com.music.awesomemusic.functionalities.start.StartVM
import com.music.awesomemusic.utils.DataUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_start.*
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
//        initLogic()
        initStartAnimation()
    }

    private fun initLogic() {
        TODO("Not yet implemented")
    }

    private fun initBinding() {
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        _viewModel = ViewModelProviders.of(this, viewModelInjectionFactory).get(LoginVM::class.java)
        _binding.viewModel = _viewModel

        _viewModel.event.observe(this, Observer { event ->
            when (event) {
                LoginState.SignUp -> {
                    goToSignUp()
                }
                is LoginState.Error -> {
                    // TODO: Create some beautiful alert for error message
                    Toast.makeText(applicationContext, event.message, Toast.LENGTH_LONG).show()
                    _viewModel.event.value = LoginState.Wait
                }
                is LoginState.LoginSuccessful -> {
                    DataUtils.saveToken(_sharedPref, token = event.token)
                    goToMain()
                }
                is LoginState.WrongCredentials -> {
                    showBadCredentials()
                }
            }
        })
    }

    private fun initStartAnimation() {
        login_activity_card_login.translationY = 300f
        ObjectAnimator.ofFloat(login_activity_card_login, "translationY", 0f).apply {
            duration = 1000
            start()
        }
    }

    private fun showBadCredentials() {
        login_activity_tv_bad_credentials.visibility = View.VISIBLE
        login_activity_tv_bad_credentials.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake))
    }

    private fun goToSignUp() {
        val signUpIntent = Intent(applicationContext, SignUpActivity::class.java)
        startActivity(signUpIntent)
    }

    private fun goToMain() {
        val mainIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }
}