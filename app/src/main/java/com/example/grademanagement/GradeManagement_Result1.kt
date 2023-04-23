package com.example.grademanagement

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GradeManagement_Result1 : AppCompatActivity() {

    lateinit var txtCGPA: TextView
    lateinit var txtResultTGPA_1: TextView
    lateinit var txtResultTGPA_2: TextView
    lateinit var txtResultTGPA_3: TextView
    lateinit var txtResultTGPA_4: TextView
    lateinit var txtResultTGPA_5: TextView
    lateinit var btnResult_1: Button
    lateinit var btnResult_2: Button
    lateinit var btnResult_3: Button
    lateinit var btnResult_4: Button
    lateinit var btnResult_5: Button

    /*
    var tgpa1 = txtResultTGPA_1.text.toString().toFloat()
    var tgpa2 = txtResultTGPA_2.text.toString().toFloat()
    var tgpa3 = txtResultTGPA_3.text.toString().toFloat()
    var tgpa4 = txtResultTGPA_4.text.toString().toFloat()
    var tgpa5 = txtResultTGPA_5.text.toString().toFloat()
    var cgpa = txtCGPA.text.toString().toFloat()
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade_management_result1)

        txtCGPA = findViewById(R.id.txtCGPA)
        txtResultTGPA_1 = findViewById(R.id.txtResult_TGPA_1)
        txtResultTGPA_2 = findViewById(R.id.txtResult_TGPA_2)
        txtResultTGPA_3 = findViewById(R.id.txtResult_TGPA_3)
        txtResultTGPA_4 = findViewById(R.id.txtResult_TGPA_4)
        txtResultTGPA_5 = findViewById(R.id.txtResult_TGPA_5)
        btnResult_1 = findViewById(R.id.btnResult_1)
        btnResult_2 = findViewById(R.id.btnResult_2)
        btnResult_3 = findViewById(R.id.btnResult_3)
        btnResult_4 = findViewById(R.id.btnResult_4)
        btnResult_5 = findViewById(R.id.btnResult_5)

        val circularProgressBar = findViewById<ProgressBar>(R.id.circularProgressBar)

        // Set the progress value of the progress bar based on student's grade

        val studentGrade = 80 // Example grade
        circularProgressBar.progress = studentGrade
        circularProgressBar.isIndeterminate = false

        btnResult_1.setOnClickListener {
            onPressBtn("CSE225","Developing Android Application",R.drawable.android)
        }
        btnResult_2.setOnClickListener {
            onPressBtn("CSE423","Virtualization and Cloud Computing",R.drawable.web_d)
        }
        btnResult_3.setOnClickListener {
            onPressBtn("CSE427","Virtualization and Cloud Computing Laboratory",R.drawable.web_d)
        }
        btnResult_4.setOnClickListener {
            onPressBtn("INT250","Digital Evidence Analysis",R.drawable.virtual)
        }
        btnResult_5.setOnClickListener {
            onPressBtn("PEA308","Advanced Analytical Skills - II",R.drawable.virtual_lab)

        }

        //cgpa = (tgpa1 + tgpa2 + tgpa3 + tgpa4 + tgpa5)/5
        //txtCGPA.setText("${cgpa} CGPA")



    }

    fun onPressBtn(title: String, code: String, imgID: Int){
        var intent = Intent(this,ResultActivity::class.java)

        intent.putExtra("code",code)
        intent.putExtra("title",title)
        intent.putExtra("imgID",imgID)
        startActivity(intent)
    }
}