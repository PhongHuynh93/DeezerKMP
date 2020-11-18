package com.wind.deezerkmp.androidApp.ui.artist

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.doOnLayout
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.palette.graphics.Palette
import androidx.palette.graphics.get
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.tabs.TabLayoutMediator
import com.wind.deezerkmp.androidApp.R
import com.wind.deezerkmp.androidApp.databinding.FragmentArtistDetailBinding
import com.wind.deezerkmp.androidApp.ui.adapter.ArtistDetailAdapter
import com.wind.deezerkmp.shared.domain.model.Artist
import kotlinx.coroutines.launch
import util.*

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
        setUpToolbar(viewBinding.toolBar, toolbarColorInt = Color.WHITE)
        // manually update height and padding top of toolbar
        var toolbarHeight = 0
        viewBinding.root.doOnApplyWindowInsets { _, windowInsets, _ ->
            viewBinding.toolBar.doOnLayout {
                if (toolbarHeight == 0) {
                    toolbarHeight = it.height + windowInsets.systemWindowInsetTop
                    viewBinding.toolBar.setHeight(toolbarHeight)
                    viewBinding.toolBar.updatePadding(top = windowInsets.systemWindowInsetTop)
                }
            }
            viewBinding.vPager.updatePadding(bottom = windowInsets.systemWindowInsetBottom)
        }
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

        // load image with palette
        Glide.with(this@ArtistDetailFragment)
            .asBitmap()
            .load(artist.model.pictureBig)
            .placeholder(R.drawable.image_placeholder)
            .addListener(object: RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.let {
                        val palette = Palette.from(it).generate()
                        val paletteTarget = androidx.palette.graphics.Target.DARK_MUTED
                        val selectedSwatch = palette[paletteTarget]
                        selectedSwatch?.rgb?.let { color ->
                            viewBinding.collapsingToolbar.setContentScrimColor(color)
                        }
                    }
                    return false
                }
            })
            .into(viewBinding.artistImageView)
    }
}