package com.music.awesomemusic.functionalities.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.music.awesomemusic.R
import com.music.awesomemusic.data.model.LetterResponse
import kotlinx.android.synthetic.main.card_letter.view.*

class LettersViewHolder(
    val inflater: LayoutInflater,
    val parent: ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.card_letter, parent, false)) {

    private val _TAG = LettersViewHolder::class.java.name

    fun bind(letterResponse: LetterResponse) {
        itemView.card_letter_name.text = letterResponse.name
    }
}