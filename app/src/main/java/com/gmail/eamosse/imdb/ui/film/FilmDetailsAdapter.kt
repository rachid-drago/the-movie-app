package com.gmail.eamosse.imdb.ui.film

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Film
import com.gmail.eamosse.imdb.databinding.FilmListItemBinding

class FilmDetailsAdapter(private val items: Film) :
    RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: FilmListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Film) {
            binding.item = item

        }
    }

    override fun onBindViewHolder(holder: FilmsAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsAdapter.ViewHolder {
        TODO("Not yet implemented")
    }


}