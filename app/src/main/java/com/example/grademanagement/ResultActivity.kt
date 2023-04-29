package com.example.grademanagement

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class ResultActivity : AppCompatActivity() {
    lateinit var imgResult: ImageView
    lateinit var txtCourseCode: TextView
    lateinit var txtCourseTitle: TextView
    lateinit var edtCA: EditText
    lateinit var edtCA2: EditText
    lateinit var edtMTE: EditText
    lateinit var edtMTE2: EditText
    lateinit var edtETE: EditText
    lateinit var edtETE2: EditText
    lateinit var btnUpdate: Button
    lateinit var sharedPreferences: SharedPreferences

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var caPercent = 0f
    var mtePercent = 0f
    var etePercent = 0f
    var tgpa = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        imgResult = findViewById(R.id.imgResult)
        txtCourseCode = findViewById(R.id.txtCourseCode)
        txtCourseTitle = findViewById(R.id.txtCourseTitle)
        edtCA = findViewById(R.id.edtCA)
        edtCA2 = findViewById(R.id.edtCA2)
        edtMTE = findViewById(R.id.edtMTE)
        edtMTE2 = findViewById(R.id.edtMTE2)
        edtETE = findViewById(R.id.edtETE)
        edtETE2 = findViewById(R.id.edtETE2)
        btnUpdate = findViewById(R.id.btnUpdate)

        sharedPreferences = this.getSharedPreferences("Shared_Prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        var UID = sharedPreferences.getString("Cred_ID","Default_User")

        var code = intent.getStringExtra("code")
        var title = intent.getStringExtra("title")
        var imgID = intent.getIntExtra("imgID",0)

        txtCourseCode.setText(code)
        txtCourseTitle.setText(title)
        imgResult.setImageResource(imgID)

        btnUpdate.setOnClickListener {
            caPercent =
                (edtCA.text.toString().toFloat() / edtCA2.text.toString().toFloat()) * 100
            mtePercent =
                (edtMTE.text.toString().toFloat() / edtMTE2.text.toString().toFloat()) * 100
            etePercent =
                (edtETE.text.toString().toFloat() / edtETE2.text.toString().toFloat()) * 100
            tgpa = (caPercent * 0.3f + mtePercent * 0.2f + etePercent * 0.5f) / 10
            
            if(caPercent > 100 || mtePercent > 100 || etePercent > 100){
                Toast.makeText(this,"Max marks can't be less than obtained marks",Toast.LENGTH_LONG).show()
            }
            else {
                val result = hashMapOf(
                    "CA" to caPercent,
                    "MTE" to mtePercent,
                    "ETE" to etePercent,
                    "TGPA" to tgpa
                )
                db.collection("$UID").document("$code").set(result)
                    .addOnCompleteListener {
                        Toast.makeText(
                            this,
                            "Updated successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Updation Failed",
                            Toast.LENGTH_LONG
                        ).show()
                    }

            }
        }
    }
}