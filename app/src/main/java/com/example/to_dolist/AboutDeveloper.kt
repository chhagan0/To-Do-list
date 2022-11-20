package com.example.to_dolist

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about_developer.*

class AboutDeveloper : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_developer)
        insta.setOnClickListener {
            val i = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.instagram.com/chagan_swami/?hl=en")
            )
            startActivity(i)
        }
        linedin.setOnClickListener {
            val i = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.linkedin.com/in/chagan-swami-732659213/")
            )
            startActivity(i)
        }
        github.setOnClickListener {
            val i = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/chhagan0")
            )
            startActivity(i)
        }
    }
}