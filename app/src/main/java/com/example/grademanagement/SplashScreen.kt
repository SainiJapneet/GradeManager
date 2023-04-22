package com.example.grademanagement

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashScreen : AppCompatActivity() {

    lateinit var splashImg: ImageView

    //sharedPreferences
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        sharedPreferences = this.getSharedPreferences("Shared_Prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        var cred_bool = sharedPreferences.getBoolean("Cred_Pref",false)


        //to shift to full screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        splashImg = findViewById(R.id.splashImg)
        val animation = AnimationUtils.loadAnimation(this,R.anim.animation_splash)
        splashImg.startAnimation(animation)

    Handler(Looper.getMainLooper()).postDelayed({
        if(cred_bool){
            var intent = Intent(this, Grademanagement::class.java)
            startActivity(intent)
        }else{
            var intent = Intent(this, OnBoarding1::class.java)
            startActivity(intent)
        }
        finish()
    },3000)
    }
}