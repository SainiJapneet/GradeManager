package com.example.grademanagement

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var pass: EditText
    lateinit var btn: MaterialButton
    lateinit var forget: TextView

    lateinit var sharedPreferences: SharedPreferences

    private lateinit var register: TextView
    private val minpasslength = 8
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        firebaseAuth = FirebaseAuth.getInstance()

        email = findViewById(R.id.email)
        pass = findViewById(R.id.pass)
        btn = findViewById(R.id.btn)
        forget = findViewById(R.id.forget)
        register = findViewById(R.id.register)

        sharedPreferences = this.getSharedPreferences("Shared_Prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        register.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }
        btn.setOnClickListener {
            if (validateInput()) {
                firebaseAuth.signInWithEmailAndPassword(email.text.toString(), pass.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            editor.putString("Cred_ID",email.text.toString())
                            editor.putBoolean("Cred_Pref",true)
                            editor.commit()
                            val intent = Intent(this, Grademanagement::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

            }
        }

        forget.setOnClickListener {
            val intent = Intent(this, ForgetActivity::class.java)
            startActivity(intent)
        }
    }
    private fun validateInput(): Boolean {
        if (email.text.toString() == "" && pass.text.toString() == "") {
            email.error = "Please Enter Email"
            pass.error = "Please Enter Password"
            return false
        }
        //checking proper email format
        if (!isEmailValid(email.text.toString())) {
            email.error = "Please Enter Valid Email"
            return false
        }

        // checking minimum password length
        if (pass.text.length < minpasslength) {
            pass.error = "Password length must be more than $minpasslength characters"
            return false
        }
        return true
    }

    private fun isEmailValid(em: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(em).matches()
    }
}
