package com.wind.deezerkmp.androidApp.ui.track

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import androidx.palette.graphics.get
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.wind.deezerkmp.androidApp.R
import com.wind.deezerkmp.androidApp.databinding.FragmentMiniPlayerBinding
import com.wind.deezerkmp.shared.domain.model.Track

/**
 * Created by Phong Huynh on 11/21/2020
 */
class MiniPlayerFragment: Fragment() {
    companion object {
        fun newInstance(): MiniPlayerFragment {
            return MiniPlayerFragment()
        }
    }

    private lateinit var viewBinding: FragmentMiniPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMiniPlayerBinding.inflate(inflater, container, false).apply {
//            vm = vmMiniPlayer
            rm = Glide.with(this@MiniPlayerFragment)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewBinding.root
    }

    fun setTrack(track: Track) {
        viewBinding.track = track

        // load image with palette
        Glide.with(this)
            .asBitmap()
            .load(track.album.model.pictureSmall)
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
                        val palette = Palette.from(it).generate().getDarkMutedColor(Color.BLACK)
                        viewBinding.miniPlayerContainer.setBackgroundColor(palette)
                    }
                    return false
                }
            })
            .into(viewBinding.imgvAvatar)
    }
}