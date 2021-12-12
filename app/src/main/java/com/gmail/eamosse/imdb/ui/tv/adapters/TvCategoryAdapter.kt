package com.gmail.eamosse.imdb.ui.tv.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.imdb.databinding.CategoryListItemBinding
import com.gmail.eamosse.imdb.ui.tv.fragments.TvHomeFragmentDirections


class TvCategoryAdapter(private val items: List<Category>) :
    RecyclerView.Adapter<TvCategoryAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Category) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(CategoryListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            val action =
                TvHomeFragmentDirections.actionTvHomeFragmentToTvFragment(
                    items[position].id.toString(),
                    items[position].name
                )
            findNavController(it).navigate(action)
        }
    }
}
