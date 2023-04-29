package com.example.grademanagement

import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

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
    lateinit var sharedPreferences: SharedPreferences

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()


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
        sharedPreferences = this.getSharedPreferences("Shared_Prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        var UID = sharedPreferences.getString("Cred_ID","Default_User")

        val circularProgressBar = findViewById<ProgressBar>(R.id.circularProgressBar)

        // Set the progress value of the progress bar based on student's grade

        val studentGrade = 80 // Example grade
        circularProgressBar.progress = studentGrade
        circularProgressBar.isIndeterminate = false

        var result1 = db.collection("$UID").document("CSE225")
        result1.get().addOnSuccessListener {document ->
        if (document != null){
            var tgpa = document.get("TGPA")
            try {
                txtResultTGPA_1.setText("$tgpa")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }else{
            Toast.makeText(this,"Something went wrong!",Toast.LENGTH_LONG).show()
        }
        }.addOnFailureListener {exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }


        var result2 = db.collection("$UID").document("CSE423")
        result2.get().addOnSuccessListener {document ->
            if (document != null){
                var tgpa = document.get("TGPA")
                try {
                    txtResultTGPA_2.setText("$tgpa")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }else{
                Toast.makeText(this,"Something went wrong!",Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener {exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }

        var result3 = db.collection("$UID").document("CSE427")
        result3.get().addOnSuccessListener {document ->
            if (document != null){
                var tgpa = document.get("TGPA")
                try {
                    txtResultTGPA_3.setText("$tgpa")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }else{
                Toast.makeText(this,"Something went wrong!",Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener {exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }

        var result4 = db.collection("$UID").document("INT250")
        result4.get().addOnSuccessListener {document ->
            if (document != null){
                var tgpa = document.get("TGPA")
                try {
                    txtResultTGPA_4.setText("$tgpa")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }else{
                Toast.makeText(this,"Something went wrong!",Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener {exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }

        var result5 = db.collection("$UID").document("PEA308")
        result5.get().addOnSuccessListener {document ->
            if (document != null){
                var tgpa = document.get("TGPA")
                try {
                    txtResultTGPA_5.setText("$tgpa")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }else{
                Toast.makeText(this,"Something went wrong!",Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener {exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }

        var tgpa1 = txtResultTGPA_1.text.toString().toFloat()
        var tgpa2 = txtResultTGPA_2.text.toString().toFloat()
        var tgpa3 = txtResultTGPA_3.text.toString().toFloat()
        var tgpa4 = txtResultTGPA_4.text.toString().toFloat()
        var tgpa5 = txtResultTGPA_5.text.toString().toFloat()
        var CGPA = (tgpa1 + tgpa2 + tgpa3 + tgpa4 + tgpa5)/5
        txtCGPA.setText("$CGPA CGPA")


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



    }

    fun onPressBtn(code: String, title: String, imgID: Int){
        var intent = Intent(this,ResultActivity::class.java)

        intent.putExtra("code",code)
        intent.putExtra("title",title)
        intent.putExtra("imgID",imgID)
        startActivity(intent)
    }


}