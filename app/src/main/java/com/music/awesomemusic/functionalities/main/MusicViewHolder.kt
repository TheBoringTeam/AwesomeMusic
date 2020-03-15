package com.music.awesomemusic.functionalities.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.music.awesomemusic.R
import com.music.awesomemusic.data.model.LetterResponse
import kotlinx.android.synthetic.main.card_music.view.*

class MusicViewHolder(
    val inflater: LayoutInflater,
    val parent: ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.card_music, parent, false)) {

    private val _TAG = MusicViewHolder::class.java.name

    fun bind(letterResponse: LetterResponse) {
        itemView.card_music_name.text = letterResponse.name
    }
}