package util.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Phong Huynh on 7/26/2020.
 */
class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = space
        outRect.bottom = space
        outRect.left = space
        outRect.right = space
    }
}

open class GridItemDecoration(
    private val space: Int,
    private val includeEdge: Boolean,
    private val spanCount: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val posBinding = parent.getChildViewHolder(view).bindingAdapterPosition

        val column: Int = posBinding % spanCount // item column

        if (includeEdge) {
            outRect.left =
                space - column * space / spanCount // spaceSmall - column * ((1f / spanCount) * spaceSmall)
            outRect.right =
                (column + 1) * space / spanCount // (column + 1) * ((1f / spanCount) * spaceSmall)
            if (posBinding < spanCount) { // top edge
                outRect.top = space
            }
            outRect.bottom = space // item bottom
        } else {
            outRect.left =
                column * space / spanCount // column * ((1f / spanCount) * spaceSmall)
            outRect.right =
                space - (column + 1) * space / spanCount // spaceSmall - (column + 1) * ((1f /    spanCount) * spaceSmall)
            if (posBinding >= spanCount) {
                outRect.top = space // item top
            }
        }
    }
}