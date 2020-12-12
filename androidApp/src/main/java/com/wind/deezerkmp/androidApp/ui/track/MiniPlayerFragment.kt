package com.wind.deezerkmp.androidApp.ui.track

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.wind.deezerkmp.androidApp.R
import com.wind.deezerkmp.androidApp.databinding.FragmentPlayerBinding
import com.wind.deezerkmp.shared.domain.model.Track
import util.getColorEx
import util.getDrawableEx
import util.tint


/**
 * Created by Phong Huynh on 11/21/2020
 */
class MiniPlayerFragment: Fragment() {
    companion object {
        fun newInstance(): MiniPlayerFragment {
            return MiniPlayerFragment()
        }
    }

    private lateinit var viewBinding: FragmentPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentPlayerBinding.inflate(inflater, container, false).apply {
//            vm = vmMiniPlayer
            rm = Glide.with(this@MiniPlayerFragment)
            lifecycleOwner = viewLifecycleOwner
            toolBar.apply {
                val appActivity = activity as AppCompatActivity
                appActivity.setSupportActionBar(this)
                collapseIcon = requireContext().getDrawableEx(R.drawable.ic_baseline_keyboard_arrow_down_24)
                navigationIcon?.tint(Color.WHITE)
                setNavigationOnClickListener {
                    motionView.transitionToStart()
                }
            }
        }
        return viewBinding.root
    }

    fun setTrack(track: Track) {
        viewBinding.track = track

        // load image with palette
        Glide.with(this)
            .asBitmap()
            .load(track.album.model.pictureMedium)
            .placeholder(R.drawable.image_placeholder)
            .addListener(object : RequestListener<Bitmap> {
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
                        // TODO: 11/22/2020 what if onloadfailed occur
                        val palette = Palette.from(it).generate()
                        val topColor = palette.getDarkVibrantColor(Color.BLACK)
                        val bottomColor = palette.getVibrantColor(topColor)
                        val gradientDrawable = GradientDrawable()
                        gradientDrawable.orientation = GradientDrawable.Orientation.BL_TR
                        gradientDrawable.shape = GradientDrawable.RECTANGLE
                        gradientDrawable.setColors(
                            intArrayOf(
                                topColor,
                                bottomColor
                            )
                        )
                        viewBinding.playerBackgroundView.background = gradientDrawable
                    }
                    return false
                }
            })
            .into(viewBinding.albumArtImageView)
    }
}