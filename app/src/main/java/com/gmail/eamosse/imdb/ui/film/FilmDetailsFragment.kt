package com.gmail.eamosse.imdb.ui.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.eamosse.imdb.databinding.FragmentFilmBinding
import com.gmail.eamosse.imdb.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer


class FilmDetailsFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding : FragmentFilmBinding
    private lateinit var filmId : String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFilmBinding.inflate(inflater, container, false)

        val arguments = arguments
        if (arguments != null) {
            filmId = arguments.get("film_id").toString()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(homeViewModel) {
            token.observe(viewLifecycleOwner, Observer {
                //récupérer les films
                getFilm(filmId.toInt())
                println(getFilm(filmId.toInt()).toString())
            })

            films.observe(viewLifecycleOwner, Observer {
                //binding.filmName.adapter =
            })

            error.observe(viewLifecycleOwner, Observer {
                //afficher l'erreur
            })
        }
    }

}