package com.example.st10449054_imad5112_practicum

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedViewScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val textViewArray = findViewById<TextView>(R.id.textViewArray)
        val btnClose2 = findViewById<Button>(R.id.btnClose2)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val dates = intent.getStringArrayExtra("dates")
        val min= intent.getStringArrayExtra("mins")
        val max= intent.getStringArrayExtra("maxs")
        val condition= intent.getStringArrayExtra("conditions")

        btnBack.setOnClickListener {
            System.exit(0)
        }
        btnClose2.setOnClickListener {
            finishAffinity()
        }
        textViewArray.text= buildString(dates,min,max,condition)

    }
    private fun buildString(
        dates: Array<String>?,
        min: Array<String>?,
        max: Array<String>?,
        conditions:Array<String>?
    ): String{
        val stringBuilder= StringBuilder()
        dates?.indices?.forEach { index ->
            stringBuilder.append(
                "Date: ${dates[index]}, Min: ${min?.getOrNull(index)}, Max: ${
                    max?.getOrNull(
                        index
                    )
                }, Conditions: ${conditions?.getOrNull(index)}\n"

            )
        }
        return stringBuilder.toString()
    }
}