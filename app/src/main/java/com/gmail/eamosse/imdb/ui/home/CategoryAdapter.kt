package com.gmail.eamosse.imdb.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.imdb.R

import com.gmail.eamosse.imdb.databinding.CategoryListItemBinding
import android.R.color
import java.util.*
import kotlin.collections.ArrayList


class CategoryAdapter(private val items: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {



    inner class ViewHolder(private val binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Category) {
            binding.item = item
            binding.categoryImg.setColorFilter(Color.BLUE)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(CategoryListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(items[position])
        val category : Category = items[position]
        holder.itemView.setOnClickListener{
            val appCompatActivity = it.context as AppCompatActivity
            val homeSecondFragment = HomeSecondFragment()

            val bundle = Bundle()
            bundle.putString("cat_name", category.name.toString())

            homeSecondFragment.arguments = bundle;
            appCompatActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, homeSecondFragment)
                .addToBackStack(null)
                .commit()

        }

    }
}