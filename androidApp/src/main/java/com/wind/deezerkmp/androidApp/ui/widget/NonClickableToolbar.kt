package com.wind.deezerkmp.androidApp.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.widget.Toolbar


/**
 * Created by Phong Huynh on 12/12/2020
 */
class NonClickableToolbar constructor(@NonNull context: Context, @Nullable attrs: AttributeSet): Toolbar(context, attrs) {

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}