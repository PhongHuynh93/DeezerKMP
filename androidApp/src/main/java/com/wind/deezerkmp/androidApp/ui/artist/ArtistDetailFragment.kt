package com.wind.deezerkmp.androidApp.ui.artist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.wind.deezerkmp.androidApp.R
import com.wind.deezerkmp.androidApp.databinding.FragmentArtistDetailBinding
import com.wind.deezerkmp.androidApp.ui.adapter.ArtistDetailAdapter
import com.wind.deezerkmp.shared.domain.model.Artist
import util.getColorEx
import util.lightStatusBar
import util.statusBarColor

/**
 * Created by Phong Huynh on 11/4/2020
 */
private const val EXTRA_DATA = "xData"
const val ARTIST_DETAIL_COUNT = 1
const val ARTIST_DETAIL_ALBUM_POS = 0
class ArtistDetailFragment : Fragment() {
    companion object {
        fun newInstance(artist: Artist): ArtistDetailFragment {
            return ArtistDetailFragment().apply {
                arguments = bundleOf(EXTRA_DATA to artist)
            }
        }
    }

    private var statusBarColor: Int = 0
    private lateinit var artist: Artist

    private lateinit var viewBinding: FragmentArtistDetailBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        statusBarColor = requireActivity().statusBarColor()
        requireActivity().statusBarColor(getColorEx(R.color.transparent))
        lightStatusBar(false)
    }

    override fun onDetach() {
        requireActivity().statusBarColor(statusBarColor)
        lightStatusBar(resources.getBoolean(R.bool.use_light_system_bars))
        super.onDetach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        artist = requireArguments().getParcelable<Artist>(EXTRA_DATA)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentArtistDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            rm = Glide.with(this@ArtistDetailFragment)
            item = artist
        }
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.vPager.apply {
            adapter = ArtistDetailAdapter(this@ArtistDetailFragment, artist)
        }
        TabLayoutMediator(viewBinding.tabs, viewBinding.vPager) { tab, pos ->
            when (pos) {
                ARTIST_DETAIL_ALBUM_POS -> {
                    tab.text = getString(R.string.album_title)
                }
            }
        }.attach()
    }
}