package com.goldcompany.apps.koreabike.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
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
        val count = state.itemCount
        val offset = 20

        if(position == 0) {
            outRect.top = offset
        } else if(position == count-1) {
            outRect.bottom = offset
        } else {
            outRect.top = offset
            outRect.bottom = offset
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val paint = Paint()
        paint.color = Color.GRAY

        val left = parent.paddingStart.toFloat()
        val right = (parent.width - parent.paddingEnd).toFloat()

        for(i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = (child.bottom + layoutParams.bottomMargin + 20).toFloat()
            val bottom = top + 2f

            c.drawRect(left, top, right, bottom, paint)
        }
    }
}