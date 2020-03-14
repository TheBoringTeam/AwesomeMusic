package com.music.awesomemusic.functionalities.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.music.awesomemusic.R
import com.music.awesomemusic.databinding.ActivityStartBinding
import com.music.awesomemusic.di.Injectable
import com.music.awesomemusic.di.ViewModelInjectionFactory
import com.music.awesomemusic.functionalities.main.MainActivity
import javax.inject.Inject

class StartActivity : AppCompatActivity(), Injectable {

    private val _TAG = StartActivity::class.java.name

    private lateinit var _binding: ActivityStartBinding
    private lateinit var _viewModel: StartVM

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelInjectionFactory<StartVM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        initBinding()

        goToMain()
    }

    private fun goToMain() {
        val mainIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    private fun initBinding() {
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_start)
        _viewModel = ViewModelProviders.of(this, viewModelInjectionFactory).get(StartVM::class.java)
        _binding.viewModel = _viewModel
    }
}