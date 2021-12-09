package com.gmail.eamosse.imdb.ui.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gmail.eamosse.idbdata.data.Film
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.FilmListItemBinding
import com.gmail.eamosse.imdb.ui.home.HomeFragmentDirections
import com.gmail.eamosse.imdb.ui.home.HomeSecondFragmentDirections

class FilmsAdapter(private val items: List<Film>) :
    RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: FilmListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Film) {
            binding.item = item
            val context = binding.filmImg
            Glide.with(context)
                .load(item.poster_path)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_film_load)
                .skipMemoryCache(false)
                .into(context)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(FilmListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        /*
        holder.itemView.setOnClickListener{
            val appCompatActivity = it.context as AppCompatActivity
            val filmDetailsFragment = FilmDetailsFragment()
            val bundle = Bundle()
                bundle.putString("film_id", items[position].id)
            filmDetailsFragment.arguments = bundle;
            appCompatActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, filmDetailsFragment)
                .addToBackStack(null)
                .commit()

        }*/

        holder.itemView.setOnClickListener{
            val action =
                HomeSecondFragmentDirections.actionNavigationHomeSecondToNavigationFilmDetails(
                    items[position].id,
                )
            Navigation.findNavController(it).navigate(action)

        }


    }


}