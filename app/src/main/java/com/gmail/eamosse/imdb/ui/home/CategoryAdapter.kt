package com.gmail.eamosse.imdb.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.CategoryListItemBinding


class CategoryAdapter(private val items: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {



    inner class ViewHolder(private val binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Category) {
            binding.item = item
            binding.categoryImg.setColorFilter(R.color.colorPrimaryDark)
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
            val action =
                HomeFragmentDirections.actionHomeFragmentToHomeSecondFragment(
                    category.id.toString(),
                    category.name
                )
            Navigation.findNavController(it).navigate(action)

        }

    }
}