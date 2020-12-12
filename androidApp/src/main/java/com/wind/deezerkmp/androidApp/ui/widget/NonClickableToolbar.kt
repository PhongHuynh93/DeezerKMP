package com.wind.deezerkmp.androidApp.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.Toolbar


/**
 * Created by Phong Huynh on 12/12/2020
 */
class NonClickableToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : Toolbar(context, attrs, defStyle) {
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}