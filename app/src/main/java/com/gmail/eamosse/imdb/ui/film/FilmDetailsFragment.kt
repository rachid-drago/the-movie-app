package com.gmail.eamosse.imdb.ui.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.eamosse.imdb.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.databinding.FragmentFilmDetailsBinding


class FilmDetailsFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding : FragmentFilmDetailsBinding
    private val args: com.gmail.eamosse.imdb.ui.film.FilmDetailsFragmentArgs by navArgs()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(homeViewModel) {
            token.observe(viewLifecycleOwner, Observer {
                //récupérer les films
                 getFilm(args.filmId.toInt())
            })

            film.observe(viewLifecycleOwner, Observer {
                binding.filmName.text = film.value?.name

            })

            error.observe(viewLifecycleOwner, Observer {
                //afficher l'erreur
                println("error" + error.value)
            })
        }
    }

}