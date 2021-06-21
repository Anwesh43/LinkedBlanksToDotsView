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
val scGap : Float = 0.13f / (parts * lines)
val strokeFactor : Float = 90f
val rFactor : Float = 29.3f
val delay : Long = 20
val backColor : Int = Color.parseColor("#BDBDBD")
val sizeFactor : Float = 3f
val rot : Float = 180f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n

fun Canvas.drawBlanksToDots(scale : Float, w : Float, h : Float, paint : Paint) {
    val size : Float = Math.min(w, h) / sizeFactor
    val sc1 : Float = scale.divideScale(0, parts)
    val sc2 : Float = scale.divideScale(1, parts)
    val sc3 : Float = scale.divideScale(2, parts)
    val gap : Float = w / parts
    val lGap : Float = gap / (2 * lines)
    val r : Float = Math.min(w, h) / rFactor
    save()
    translate(w / 2, h / 2)
    for (j in 0..(lines - 1)) {
        val sc1j : Float = sc1.divideScale(j, lines)
        val sc2j : Float = sc2.divideScale(j, lines)
        val sc3j : Float = sc3.divideScale(j, lines)
        save()
        translate(-gap / 2 + lGap * (2 * j), 0f)
        drawLine(
            lGap * sc2j,
            0f,
            lGap * sc1j - lGap * 0.5f * sc2j,
            0f,
            paint
        )
        drawCircle(lGap / 2, (h / 2 + r) * sc3j, r * sc2j, paint)
        restore()
    }
    restore()
}

fun Canvas.drawBTDNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    drawBlanksToDots(scale, w, h, paint)
}