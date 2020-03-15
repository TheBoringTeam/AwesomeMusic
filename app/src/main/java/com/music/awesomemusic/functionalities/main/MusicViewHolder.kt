package com.music.awesomemusic.functionalities.main

import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.music.awesomemusic.R
import com.music.awesomemusic.data.model.MusicResponse
import kotlinx.android.synthetic.main.card_music.view.*

class MusicViewHolder(
    val inflater: LayoutInflater,
    val parent: ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.card_music, parent, false)) {

    private val _TAG = MusicViewHolder::class.java.name

    private lateinit var player: MediaPlayer

    fun bind(musicResponse: MusicResponse, items: List<MusicResponse>) {
        var isPlaying = false
        itemView.card_music_name.text = musicResponse.title

        itemView.card_music_btn_play.setOnClickListener { it ->
            if (isPlaying) {
                it.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24)
                player.stop()
                isPlaying = false
            } else {
                it.setBackgroundResource(R.drawable.ic_baseline_stop_24)
                playMusic(musicResponse.link)
                isPlaying = true
            }
        }
    }

    private fun playMusic(link: String) {
        player = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(link)
            prepare() // might take long! (for buffering, etc)
            start()
        }
    }
}