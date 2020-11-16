package com.wind.deezerkmp.androidApp.ui.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.wind.deezerkmp.androidApp.databinding.FragmentArtistDetailBinding
import com.wind.deezerkmp.shared.domain.model.Artist

/**
 * Created by Phong Huynh on 11/4/2020
 */
private const val EXTRA_DATA = "xData"

class ArtistDetailFragment : Fragment() {
    companion object {
        fun newInstance(artist: Artist): ArtistDetailFragment {
            return ArtistDetailFragment().apply {
                arguments = bundleOf(EXTRA_DATA to artist)
            }
        }
    }

    private lateinit var viewBinding: FragmentArtistDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentArtistDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            rm = Glide.with(this@ArtistDetailFragment)
            item = requireArguments().getParcelable<Artist>(EXTRA_DATA)
        }
        return viewBinding.root
    }
}