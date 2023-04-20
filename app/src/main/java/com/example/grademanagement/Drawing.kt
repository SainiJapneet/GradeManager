package com.example.grademanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.grademanagement.CustomDrawing2

class Drawing : AppCompatActivity() {

    lateinit var customDrawing2 :CustomDrawing2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawing)

        customDrawing2 =  CustomDrawing2(this)
        setContentView(customDrawing2)
    }
}