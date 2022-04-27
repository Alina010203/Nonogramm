package com.example.daadya.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.graphics.toColor
import com.example.daadya.models.Model


class GameView: View
{
    private var model: Model? = null
    private var cell_size : Int = 0
    private val padding_cell : Int = 5
    private val color_back_dight: Int = Color.rgb(255, 255, 255)
    private val color_dight: Int = Color.rgb(0, 0, 0)
    private val color_empty: Int = Color.rgb(200, 200, 200)
    private val color_fill: Int = Color.rgb(0, 0, 0)
    private val color_wrong: Int = Color.rgb(200, 0, 0)
    private var padding_left : Int = 0
    private var padding_top : Int = 0
    private var view_w : Int = 0
    private var view_h : Int = 0
    private val paint = Paint()

    private val text_size_coeff = 0.5

    constructor(context: Context, attrs: AttributeSet):
            super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int):
            super(context, attrs, defStyle)

    public fun setModel(model: Model?) {
        this.model = model
        newSize()
        invalidate()
    }

    public fun newSize() {
        if (this.model != null) {
            val w = this.model!!.getW() + 1
            val h = this.model!!.getH() + 1
            val cell_w = this.view_w / w
            val cell_h = this.view_h / h
            this.cell_size = Math.min(cell_h, cell_w)
            this.paint.textSize = (this.cell_size * this.text_size_coeff).toFloat()
            this.padding_top = (this.view_h - h * this.cell_size) / 2
            this.padding_left = (this.view_w - w * this.cell_size) / 2
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.view_h = h
        this.view_w = w
        newSize()
    }

    public fun getCell(x: Float?, y: Float?): Pair<Int, Int>? {
        if (x == null || y == null) {
            return null
        }
        val row = (y - padding_top).toInt() / cell_size - 1
        val col = (x - padding_left).toInt() / cell_size - 1
        if (row < 0 || col < 0 || row >= this.model!!.getH() || col >= this.model!!.getW()) {
            return null
        }
        return Pair(row, col)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (this.model != null) {
            val w = this.model!!.getW()
            val h = this.model!!.getH()
            Log.d("WIDTH", w.toString())
            Log.d("HEIGHT", h.toString())
            for (i in 0 until h) {
                for (j in 0 until w) {
                    val value = this.model!!.getCell(i, j)
                    var color = when(value) {
                        1 -> this.color_fill
                        2 -> this.color_wrong
                        else -> this.color_empty
                    }
                    var text: String? = null
                    if (value == 3 || value == 2) {
                        text = "X"
                    }
                    drawCell(canvas!!, i + 1, j + 1, color, text)
                }
            }
            for (i in 0 until h) {
                var text = this.model?.getSumRow()?.get(i)?.toString()
                drawCell(canvas!!, i + 1, 0, color_back_dight, text)
            }
            for (i in 0 until w) {
                var text = this.model?.getSumCol()?.get(i)?.toString()
                drawCell(canvas!!, 0, i + 1, color_back_dight, text)
            }
        }
    }

    private fun drawCell(canvas: Canvas, row: Int, col: Int, color: Int, text: String?) {
        this.paint.color = color
        var top : Float = (padding_top + row * cell_size + padding_cell).toFloat()
        var left : Float = (padding_left + col * cell_size + padding_cell).toFloat()
        val bottom : Float = (padding_top + row * cell_size + cell_size - padding_cell).toFloat()
        val right : Float = (padding_left + col * cell_size + cell_size - padding_cell).toFloat()
        val rect = RectF(left, top, right, bottom)
        canvas.drawRect(rect, this.paint)
        if (text != null) {
            this.paint.color = this.color_dight
            this.paint.isAntiAlias = true;
            this.paint.textAlign = Paint.Align.LEFT
            var textRect: Rect = Rect()
            paint.getTextBounds(text, 0, text.length, textRect)
            canvas.drawText(
                text!!,
                left + (cell_size - textRect.width()) / 2,
                top + (cell_size + textRect.height()) / 2,
                this.paint
            )
        }
    }
}