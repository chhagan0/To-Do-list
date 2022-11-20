package com.example.to_dolist

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val bottomanimm=AnimationUtils.loadAnimation(this,R.drawable.topanim)
        val topanimm=AnimationUtils.loadAnimation(this,R.drawable.bottom)

        todologo.setAnimation(topanimm)
        text.setAnimation(bottomanimm)
        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
startActivity(Intent(this@SplashScreen,MainActivity::class.java))            }
        },4000)
    }
}