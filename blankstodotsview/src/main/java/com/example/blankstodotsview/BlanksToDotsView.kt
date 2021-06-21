package com.example.blankstodotsview

import android.view.View
import android.view.MotionEvent
import android.content.Context
import android.app.Activity
import android.graphics.Paint
import android.graphics.Color
import android.graphics.Canvas

val colors : Array<Int> = arrayOf(
    "#f44336",
    "#004D40",
    "#FFD600",
    "#00C853",
    "#6200EA"
).map {
    Color.parseColor(it)
}.toTypedArray()
val lines : Int = 4
val parts : Int = 3
val scGap : Float = 0.12f / (parts * lines)
val strokeFactor : Float = 90f
val rFactor : Float = 29.3f
val delay : Long = 20
val backColor : Int = Color.parseColor("#BDBDBD")
val sizeFactor : Float = 3f
val rot : Float = 180f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
