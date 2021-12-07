package com.gmail.eamosse.imdb.ui.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.eamosse.imdb.databinding.FragmentFilmBinding
import com.gmail.eamosse.imdb.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmDetailsFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding : FragmentFilmBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFilmBinding.inflate(inflater, container, false)

        val arguments = arguments
        if (arguments != null) {
            println("id de film :: " + arguments.get("film_id").toString())
        }

        return binding.root
    }

}