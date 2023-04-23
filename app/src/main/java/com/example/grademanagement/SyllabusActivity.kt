package com.example.grademanagement

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class SyllabusActivity : AppCompatActivity() {

    lateinit var btnConst_1: Button
    lateinit var btnConst_2: Button
    lateinit var btnConst_3: Button
    lateinit var btnConst_4: Button
    lateinit var btnConst_5: Button
    lateinit var btnConst_6: Button

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_syllabus)

        btnConst_1 = findViewById(R.id.btnConst_1)
        btnConst_2 = findViewById(R.id.btnConst_2)
        btnConst_3 = findViewById(R.id.btnConst_3)
        btnConst_4 = findViewById(R.id.btnConst_4)
        btnConst_5 = findViewById(R.id.btnConst_5)
        btnConst_6 = findViewById(R.id.btnConst_6)


        btnConst_1.setOnClickListener {
            getSyllabus("CSE225")
        }
        btnConst_2.setOnClickListener {
            getSyllabus("CSE423")
        }
        btnConst_3.setOnClickListener {
            getSyllabus("CSE427")
        }
        btnConst_4.setOnClickListener {
            getSyllabus("INT250")
        }
        btnConst_5.setOnClickListener {
            getSyllabus("PEA308")
        }
        btnConst_6.setOnClickListener {
            getSyllabus("PES319")
        }
    }

    fun getSyllabus(course : String) {

        var mySyl = db.collection("Syllabus").document("UserSyllabus")
        mySyl.get().addOnSuccessListener { document ->
            if (document != null) {
                val docLink = document.get(course)
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("$docLink"))
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(this, "Ops! Document not found", Toast.LENGTH_LONG).show()
            }

        }.addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }

    }


}