package com.wind.deezerkmp.androidApp.ui.track

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import com.wind.deezerkmp.androidApp.R

/**
 * Created by Phong Huynh on 11/22/2020
 */
class PlayerMotionLayout(
    context: Context,
    attributeSet: AttributeSet? = null
) : MotionLayout(context, attributeSet) {

    private val viewToDetectTouch by lazy {
        findViewById<View>(R.id.player_background_view)
    }
    private val viewRect = Rect()
    private var hasTouchStarted = false
    private val gestureDetector =
        GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                transitionToEnd()
                return super.onSingleTapUp(e)
            }
        })

    init {
        addTransitionListener(object : TransitionListener {
            override fun onTransitionTrigger(
                p0: MotionLayout?,
                p1: Int,
                p2: Boolean,
                p3: Float
            ) {
            }

            override fun onTransitionStarted(
                p0: MotionLayout?,
                p1: Int,
                p2: Int
            ) {
            }

            override fun onTransitionChange(
                p0: MotionLayout?,
                p1: Int,
                p2: Int,
                p3: Float
            ) {
            }

            override fun onTransitionCompleted(
                p0: MotionLayout?,
                p1: Int
            ) {
                hasTouchStarted = false
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)   //This ensures the Mini Player is maximised on single tap
        when (event.actionMasked) {
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                hasTouchStarted = false
                return super.onTouchEvent(event)
            }
        }
        if (!hasTouchStarted) {
            viewToDetectTouch.getHitRect(viewRect)
            hasTouchStarted = viewRect.contains(event.x.toInt(), event.y.toInt())
        }
        return hasTouchStarted && super.onTouchEvent(event)
    }
}