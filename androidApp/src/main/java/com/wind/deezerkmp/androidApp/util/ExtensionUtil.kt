package com.wind.deezerkmp.androidApp.util

import android.content.Context
import android.view.View
import androidx.databinding.BindingAdapter
import com.wind.deezerkmp.androidApp.R
import util.doOnApplyWindowInsets
import util.getDimen

/**
 * Created by Phong Huynh on 10/6/2020
 */
val Context.spacePrettySmall
    get() = getDimen(R.dimen.space_pretty_small).toInt()
val Context.spaceNormal
    get() = getDimen(R.dimen.space_normal).toInt()
val Context.spaceSmall
    get() = getDimen(R.dimen.space_small).toInt()

@BindingAdapter(
    "paddingLeftSystemWindowInsets",
    "paddingTopSystemWindowInsets",
    "paddingRightSystemWindowInsets",
    "paddingBottomSystemWindowInsets",
    requireAll = false
)
fun applySystemWindows(
    view: View,
    applyLeft: Boolean,
    applyTop: Boolean,
    applyRight: Boolean,
    applyBottom: Boolean
) {
    view.doOnApplyWindowInsets { view, insets, padding ->
        val left = if (applyLeft) insets.systemWindowInsetLeft else 0
        val top = if (applyTop) insets.systemWindowInsetTop else 0
        val right = if (applyRight) insets.systemWindowInsetRight else 0
        val bottom = if (applyBottom) insets.systemWindowInsetBottom else 0

        view.setPadding(
            padding.left + left,
            padding.top + top,
            padding.right + right,
            padding.bottom + bottom
        )
    }
}

