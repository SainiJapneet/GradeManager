package com.example.grademanagement

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class Register : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var name: EditText
    private lateinit var regNo: EditText
    private lateinit var pass: EditText
    private lateinit var course: EditText
    private lateinit var regbtn: MaterialButton
    lateinit var relogin: TextView
    private val min_pass_len = 8
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email = findViewById(R.id.email)
        pass = findViewById(R.id.pass)
        name = findViewById(R.id.name)
        regNo = findViewById(R.id.regNo)
        course = findViewById(R.id.course)
        regbtn = findViewById(R.id.regbtn)
        relogin = findViewById(R.id.relogin)

        firebaseAuth = FirebaseAuth.getInstance()

        relogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        regbtn.setOnClickListener {
            if (validateInput()) {
                firebaseAuth.createUserWithEmailAndPassword(
                    email.text.toString(),
                    pass.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        firebaseAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
                            Toast.makeText(this, "Verify your email id", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
//                        Toast.makeText(this, "You are successfully registered", Toast.LENGTH_SHORT).show()
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                        finish()
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else
                Toast.makeText(this, "Please retry", Toast.LENGTH_SHORT).show()
        }
        supportActionBar?.hide()
    }

    private fun validateInput(): Boolean {
        if (email.text.toString() == "" || pass.text.toString() == "" || course.text.toString() == "" ||
            name.text.toString() == "" || regNo.text.toString() == ""
        ) {
            Toast.makeText(this, "Fill all the fields", Toast.LENGTH_SHORT).show()
            return false
        }
        //checking proper email format
        if (!isEmailValid(email.text.toString())) {
            email.error = "Please Enter Valid Email"
            return false
        }
        // checking minimum password length
        if (pass.text.length < min_pass_len) {
            pass.error = "Password length must be more than $min_pass_len characters"
            return false
        }
        return true
    }

    private fun isEmailValid(em: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(em).matches()
    }

    private fun addArtist() {
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser?.uid
        val dbrf = currentFirebaseUser?.let { databaseReference.child(it) }

        val name = "Enter your name"
        val eml = email.text.toString().trim()
        val reg = regNo.text.toString().trim()

        val value = currentFirebaseUser?.let {
            PModel(
                it,
                name,
                eml,
                reg
            )
        }

        // Add the artist's data to the Firebase Realtime Database
        if (dbrf != null && value != null) {
            dbrf.setValue(value)
                .addOnSuccessListener {
                    // Data successfully added to database
                    Toast.makeText(this, "Artist added!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    // Error adding data to database
                    Toast.makeText(this, "Error adding artist", Toast.LENGTH_SHORT).show()
                }
        }
    }
}