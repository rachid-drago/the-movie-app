package com.gmail.eamosse.imdb.ui.tv.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.FragmentTvDetailBinding
import com.squareup.picasso.Picasso
import com.gmail.eamosse.imdb.ui.tv.TvViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvDetailFragment : Fragment() {
    private val tvViewModel: TvViewModel by viewModel()
    private val args: TvDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentTvDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(
            R.string.title_detail_tv
        ) + " - " + args.tvName
        with(tvViewModel) {
            token.observe(
                viewLifecycleOwner,
                Observer {
                    getTvById(
                        args.tvId,
                        getString(
                            R.string.rest_language
                        )
                    )
                }
            )
            tv.observe(
                viewLifecycleOwner,
                Observer {
                    binding.tvDetailName.text = tv.value?.name + " - " + tv.value?.vote + " / 10"
                    binding.tvDetailOverview.text = tv.value?.overview
                    Picasso.get()
                        .load(tv.value?.poster)
                        .into(binding.tvDetailPoster)
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
