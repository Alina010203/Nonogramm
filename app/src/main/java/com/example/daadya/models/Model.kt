package com.example.daadya.models
import com.example.daadya.views.GameView

class Model {
    private var w: Int = 0
    private var h: Int = 0
    private var field: Array<Array<Int>>? = null
    private var field_true: Array<Array<Int>>? = null
    private var sum_col: Array<Int>? = null
    private var sum_row: Array<Int>? = null

    constructor(w: Int, h: Int) {
        this.w = w
        this.h = h
        this.field = Array(h) { Array(w) { 0 } }
        this.field_true = Array(h) { Array(w) { (0..1).random() } }
        this.sum_col = Array(w) { 0 }
        this.sum_row = Array(h) { 0 }
        for (row in 0 until h) {
            for (col in 0 until w) {
                this.sum_col!![col] += this.field_true!![row][col]
                this.sum_row!![row] += this.field_true!![row][col]
            }
        }
    }

    public fun getW(): Int {
        return this.w
    }

    public fun getH(): Int {
        return this.h
    }

    public fun getCell(row: Int, col: Int): Int? {
        return this.field?.get(row)?.get(col)
    }

    public fun getSumRow(): Array<Int>? {
        return this.sum_row
    }

    public fun getSumCol(): Array<Int>? {
        return this.sum_col
    }
}