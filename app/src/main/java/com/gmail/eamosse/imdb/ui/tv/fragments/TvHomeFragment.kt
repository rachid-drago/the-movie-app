package com.gmail.eamosse.imdb.ui.tv.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.gmail.eamosse.imdb.databinding.FragmentTvHomeBinding
import com.gmail.eamosse.imdb.ui.tv.TvViewModel
import com.gmail.eamosse.imdb.ui.tv.adapters.TvCategoryAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvHomeFragment : Fragment() {

    private val tvViewModel: TvViewModel by viewModel()
    private lateinit var binding: FragmentTvHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(tvViewModel) {
            token.observe(
                viewLifecycleOwner,
                Observer {
                    // récupérer les catégories
                    getTvCategories()
                }
            )

            categories.observe(
                viewLifecycleOwner,
                Observer {
                    binding.categoryTvList.adapter = TvCategoryAdapter(it)
                }
            )

            error.observe(
                viewLifecycleOwner,
                Observer {
                    // afficher l'erreur
                }
            )
        }
    }
}
