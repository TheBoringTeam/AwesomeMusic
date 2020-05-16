package com.music.awesomemusic.functionalities.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.music.awesomemusic.R
import com.music.awesomemusic.databinding.ActivityMainBinding
import com.music.awesomemusic.di.Injectable
import com.music.awesomemusic.di.ViewModelInjectionFactory
import com.music.awesomemusic.functionalities.login.LoginActivity
import com.music.awesomemusic.utils.DataUtils
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable {

    private val _TAG = MainActivity::class.java.name

    private lateinit var _binding: ActivityMainBinding
    private lateinit var _viewModel: MainVM

    private lateinit var _sharedPref: SharedPreferences

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelInjectionFactory<MainVM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()
        initLogic()
    }

    private fun initBinding() {
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        _viewModel = ViewModelProviders.of(this, viewModelInjectionFactory).get(MainVM::class.java)
        _binding.viewModel = _viewModel

        _sharedPref = applicationContext.getSharedPreferences(
                getString(R.string.preference_key), Context.MODE_PRIVATE
        )
    }

    private fun initLogic() {
        _viewModel.event.observe(this, Observer { event ->
            when (event) {
                MainState.LogOut -> {
                    Log.d(_TAG, "Started log out process...")
                    DataUtils.deleteAllData(_sharedPref)
                    goToLogin()
                }
                else -> {
                    Log.e(_TAG, "Unregistered state for event")
                }
            }
        })
    }

    private fun goToLogin() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        finish()
    }
}