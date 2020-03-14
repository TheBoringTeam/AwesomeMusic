package com.music.awesomemusic.functionalities.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.music.awesomemusic.data.model.Letter

class LettersListAdapter(var items: List<Letter>) :
    RecyclerView.Adapter<LettersViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LettersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LettersViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: LettersViewHolder, position: Int) {
        val obj = items[position]
        holder.bind(obj)
    }
}