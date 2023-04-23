package com.example.grademanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    lateinit var imgResult: ImageView
    lateinit var txtCourseCode: TextView
    lateinit var txtCourseTitle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        imgResult = findViewById(R.id.imgResult)
        txtCourseCode = findViewById(R.id.txtCourseCode)
        txtCourseTitle = findViewById(R.id.txtCourseTitle)

        var code = intent.getStringExtra("code")
        var title = intent.getStringExtra("title")
        var imgID = intent.getIntExtra("imgID",0)

        txtCourseCode.setText(code)
        txtCourseTitle.setText(title)
        imgResult.setImageResource(imgID)
    }
}