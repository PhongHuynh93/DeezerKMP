package com.wind.deezerkmp.androidApp.util

import android.widget.ImageView
import androidx.core.view.doOnLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestManager
import com.wind.deezerkmp.androidApp.R

/**
 * Created by Phong Huynh on 9/26/2020
 */
@BindingAdapter("requestManager", "image", "useOverride", requireAll = false)
fun ImageView.loadImage(requestManager: RequestManager, url: String?, useOverride: Boolean = false) {
    val builder = requestManager
        .load(url)
        .placeholder(R.drawable.image_placeholder)
    if (useOverride) {
        doOnLayout {
            builder
                .override(it.width, it.height)
                .into(this)
        }
    } else {
        builder.into(this)
    }
}
