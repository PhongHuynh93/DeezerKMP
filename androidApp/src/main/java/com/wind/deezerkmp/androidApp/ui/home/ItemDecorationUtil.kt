package com.wind.deezerkmp.androidApp.ui.home

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wind.deezerkmp.androidApp.ui.adapter.TitleHeaderAdapter
import com.wind.deezerkmp.androidApp.util.spaceLarge
import com.wind.deezerkmp.androidApp.util.spaceNormal
import com.wind.deezerkmp.androidApp.util.spaceSmall
import util.recyclerview.GridItemDecoration

/**
 * Created by Phong Huynh on 11/16/2020
 */

class HomeItemDecoration(
    private val context: Context,
    space: Int,
    includeEdge: Boolean,
    spanCount: Int
) : GridItemDecoration(space, includeEdge, spanCount) {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildViewHolder(view) is TitleHeaderAdapter.TitleHeaderViewHolder) {
            outRect.top = context.spaceLarge
            outRect.bottom = context.spaceSmall
            return
        } else {
            super.getItemOffsets(outRect, view, parent, state)
        }
    }
}