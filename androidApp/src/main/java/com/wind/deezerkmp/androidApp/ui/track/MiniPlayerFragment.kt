package com.wind.deezerkmp.androidApp.ui.track

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
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
import timber.log.Timber
import util.*


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
                setNavigationIcon(R.drawable.ic_baseline_keyboard_arrow_down_24)
                navigationIcon?.tint(Color.WHITE)
                setNavigationOnClickListener {
                    motionView.transitionToStart()
                }
                val actionbar = appActivity.supportActionBar
                actionbar?.apply {
                    this.title = ""
                }
            }
        }
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.motionView.addTransitionListener(object: MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                if (viewBinding.motionView.targetPosition == 1f) {
                    lightStatusBar(false)
                } else {
                    lightStatusBar(resources.getBoolean(R.bool.use_light_system_bars))
                }
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }

        })
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
                        val palette = Palette.from(it).generate()
                        val bottomColor = palette.getVibrantColor(Color.BLACK)
                        viewBinding.playerBackgroundView.background = ColorDrawable(bottomColor)
                    }
                    return false
                }
            })
            .into(viewBinding.albumArtImageView)
    }
}