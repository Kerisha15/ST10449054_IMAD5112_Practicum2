package com.example.st10449054_imad5112_practicum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val textHeading = findViewById<TextView>(R.id.textHeading)
        val textViewName =findViewById<TextView>(R.id.textViewName)
        val textViewStudentNumber = findViewById<TextView>(R.id.textViewStudentNumber)
        val imageViewWeather = findViewById<ImageView>(R.id.imageViewWeather)
        val btnMain = findViewById<Button>(R.id.btnMain)
        val btnExit = findViewById<Button>(R.id.btnExit)

        btnMain.setOnClickListener {
            val intent= Intent(this,MainScreen::class.java)
            startActivity(intent)
        }
        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}