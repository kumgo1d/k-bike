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
        val offset = 20

        if(position == 0) {
            outRect.top = offset
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
            //divider를 그리기 위한 top, bottom. 높이 2f.
            val top = child.bottom.toFloat()
            val bottom = top + 2f

            c.drawRect(left, top, right, bottom, paint)
        }
    }
}