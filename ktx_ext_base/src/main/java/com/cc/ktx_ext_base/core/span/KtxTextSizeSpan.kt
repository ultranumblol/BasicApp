package com.cc.ktx_ext_base.core.span

import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.ReplacementSpan


class KtxTextSizeSpan(val textSize: Int, val verticalOffset: Int) : ReplacementSpan() {

    private lateinit var mPaint: Paint

    override fun getSize(paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        mPaint = Paint(paint)
        mPaint.textSize = textSize.toFloat()

        fm?.let {
            if (textSize > paint.textSize) {
                val newFm = mPaint.fontMetricsInt
                fm.descent = newFm.descent
                fm.ascent = newFm.ascent
                fm.top = newFm.top
                fm.bottom = newFm.bottom
            }
        }

        return mPaint.measureText(text, start, end).toInt()
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        val baseLine = (y + verticalOffset).toFloat()
        canvas.drawText(text, start, end, x, baseLine, mPaint)
    }
}