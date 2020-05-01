package com.music.awesomemusic.functionalities.main

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
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable {

    private val _TAG = MainActivity::class.java.name

    private lateinit var _binding: ActivityMainBinding
    private lateinit var _viewModel: MainVM

    private lateinit var _adapter: MusicListAdapter

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelInjectionFactory<MainVM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBinding()

        _viewModel.fetchMusic()
    }

    private fun initBinding() {
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        _viewModel = ViewModelProviders.of(this, viewModelInjectionFactory).get(MainVM::class.java)
        _binding.viewModel = _viewModel

        _adapter = MusicListAdapter(listOf())

        _viewModel.listOfLetters.observe(this, Observer { items ->
            Log.i(_TAG, "List has ${items.size} elements")
            if (items.isEmpty()) {
                Toast.makeText(applicationContext, "Not found", Toast.LENGTH_SHORT).show()
            }
            _adapter.items = items
            _adapter.notifyDataSetChanged()
        })
    }
}