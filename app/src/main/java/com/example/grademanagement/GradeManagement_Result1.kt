package com.example.grademanagement

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity


class GradeManagement_Result1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade_management_result1)

        val circularProgressBar = findViewById<ProgressBar>(R.id.circularProgressBar)

        // Set the progress value of the progress bar based on student's grade

        val studentGrade = 80 // Example grade
        circularProgressBar.progress = studentGrade
        circularProgressBar.isIndeterminate = false
    }
}