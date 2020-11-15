package com.wind.deezerkmp.androidApp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestManager
import com.wind.deezerkmp.androidApp.R

/**
 * Created by Phong Huynh on 9/26/2020
 */
@BindingAdapter("requestManager", "image")
fun ImageView.loadImage(requestManager: RequestManager, url: String?) {
    requestManager
        .load(url)
        .placeholder(R.drawable.image_placeholder)
        .into(this)
}
