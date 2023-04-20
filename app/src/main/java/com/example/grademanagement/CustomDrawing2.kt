package com.example.grademanagement

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import com.example.grademanagement.R

class CustomDrawing2 : View {
    lateinit var p: Paint
    lateinit var mpath: Path
    var clearbutton = findViewById<Button>(R.id.clearbut)

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    fun init() {
        p = Paint()
        mpath = Path()
        p.color = Color.BLACK
        p.style = Paint.Style.STROKE
        p.strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas != null) {
            canvas.drawColor(Color.WHITE)
        }
        if (canvas != null) {
            canvas.drawPath(mpath, p)
        }
    }

    fun clearCanvas() {
        mpath.reset()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> mpath.moveTo(event.x, event.y)
                MotionEvent.ACTION_MOVE -> mpath.lineTo(event.x, event.y)

            }
        }
        invalidate()
        return true
    }


}
