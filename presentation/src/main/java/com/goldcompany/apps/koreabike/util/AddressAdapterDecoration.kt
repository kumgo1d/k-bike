package com.goldcompany.apps.koreabike.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class AddressAdapterDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val offset = 20

        if (position == 0) {
            outRect.top = offset
            outRect.bottom = offset
        } else {
            outRect.bottom = offset
        }
    }
}