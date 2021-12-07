package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.eamosse.imdb.databinding.FragmentHomeSecondBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.ui.film.FilmsAdapter


class HomeSecondFragment : Fragment() {

    private val args: com.gmail.eamosse.imdb.ui.home.HomeSecondFragmentArgs by navArgs()
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding : FragmentHomeSecondBinding
    private lateinit var categoryId : String



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeSecondBinding.inflate(inflater, container, false)

        val arguments = arguments
        if (arguments != null) {
                //binding.text2.text = arguments.get("cat_name").toString()
            categoryId = arguments.get("cat_id").toString()
        }
            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(homeViewModel) {
            token.observe(viewLifecycleOwner, Observer {
                //récupérer les films
                getFilms(categoryId)
            })

            films.observe(viewLifecycleOwner, Observer {
                binding.filmList.adapter = FilmsAdapter(it)
            })

            error.observe(viewLifecycleOwner, Observer {
                //afficher l'erreur
            })
        }
    }
}
