package com.music.awesomemusic.functionalities.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.music.awesomemusic.data.model.LetterResponse

class MusicListAdapter(var items: List<LetterResponse>) :
    RecyclerView.Adapter<MusicViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MusicViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val obj = items[position]
        holder.bind(obj)
    }
}