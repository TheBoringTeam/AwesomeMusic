package com.music.awesomemusic.functionalities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.music.awesomemusic.R
import com.music.awesomemusic.databinding.ActivityMainBinding
import com.music.awesomemusic.databinding.ActivityStartBinding
import com.music.awesomemusic.di.Injectable
import com.music.awesomemusic.di.ViewModelInjectionFactory
import com.music.awesomemusic.functionalities.start.StartActivity
import com.music.awesomemusic.functionalities.start.StartVM
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable {

    private val _TAG = MainActivity::class.java.name

    private lateinit var _binding: ActivityMainBinding
    private lateinit var _viewModel: MainVM

    private lateinit var _adapter: LettersListAdapter

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelInjectionFactory<MainVM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBinding()
        _viewModel.fetchLetters()
    }

    private fun initBinding() {
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        _viewModel = ViewModelProviders.of(this, viewModelInjectionFactory).get(MainVM::class.java)
        _binding.viewModel = _viewModel

        _adapter = LettersListAdapter(listOf())
        activity_main_rv_letters.layoutManager = GridLayoutManager(applicationContext, 1)
        activity_main_rv_letters.adapter = _adapter

        _viewModel.listOfLetters.observe(this, Observer { items ->
            Log.i(_TAG, "List has ${items.size} elements")
            _adapter.items = items
            _adapter.notifyDataSetChanged()
        })
    }
}