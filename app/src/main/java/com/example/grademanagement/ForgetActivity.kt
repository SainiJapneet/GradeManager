package com.example.grademanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth

class ForgetActivity : AppCompatActivity() {
    private lateinit var emph: EditText;
    private lateinit var sendbtn: MaterialButton
    private lateinit var backbtn: MaterialButton
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget)
        auth = FirebaseAuth.getInstance()
        emph = findViewById(R.id.emph)
        sendbtn = findViewById(R.id.sendbtn)
        backbtn = findViewById(R.id.backbtn)

        backbtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        sendbtn.setOnClickListener {
            val email = emph.text.toString()
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Please Check Email inbox", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, it.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}