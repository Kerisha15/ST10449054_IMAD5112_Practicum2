package com.example.st10449054_imad5112_practicum

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
private var dates = arrayOf("Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
    "Sunday")
private var min = arrayOf("30","27","20","15","9","14","28")
private var max = arrayOf("34","32","26","25","20","27","32")
private var conditions = arrayOf("Sunny",
    "Sunny",
    "Cloudy",
    "Rainy",
    "Cloudy",
    "Rainy",
    "Sunny")

private fun clearArrays(){
    dates = emptyArray()
    min = emptyArray()
    max = emptyArray()
    conditions = emptyArray()
}
class MainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val textViewEdt = findViewById<TextView>(R.id.textViewEdt)
        val edtDate = findViewById<EditText>(R.id.edtDate)
        val edtMinTemp = findViewById<EditText>(R.id.edtMinTemp)
        val edtMaxTemp = findViewById<EditText>(R.id.edtMaxTemp)
        val edtCondition = findViewById<EditText>(R.id.edtCondition)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnEnter = findViewById<Button>(R.id.btnEnter)
        val btnAverage = findViewById<Button>(R.id.btnAverage)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val btnClose = findViewById<Button>(R.id.btnClose)



        btnEnter.setOnClickListener {
            val date = edtDate.text.toString().trim()
            val mins = edtMinTemp.text.toString().trim()
            val maxs = edtMaxTemp.text.toString().trim()
            val condition = edtCondition.text.toString().trim()
            if (!condition.equals("Hot",ignoreCase = true)&&!date.equals("Sunny",ignoreCase = true)&&!date.equals("cold",ignoreCase = true)&&date.equals("rain",ignoreCase = true)&&!date.equals("windy",ignoreCase = true)&&!date.equals("cloudy",ignoreCase = true)&&date.equals("hot", ignoreCase = true)){
                textViewEdt.text = "Error: Please vaild conditions only"
                return@setOnClickListener
            }

            if (!date.equals("Monday",ignoreCase = true)&&!date.equals("Tuesday",ignoreCase = true)&&!date.equals("Wednesday",ignoreCase = true)&&date.equals("Thursday",ignoreCase = true)&&!date.equals("Friday",ignoreCase = true)&&!date.equals("Saturday",ignoreCase = true)&&date.equals("Sunday")){
                textViewEdt.text = "Error: Please input days of the week"
                return@setOnClickListener
            }
            if (!mins.matches("\\d+".toRegex())|| !maxs.matches("\\d+".toRegex())) {// checks if the min and max is a number
                textViewEdt.text = "Error: min and max must be numbers"
                return@setOnClickListener
            }
            if (date.isEmpty()|| mins.isEmpty()|| maxs.isEmpty()|| condition.isEmpty()){
                textViewEdt.text = "Error: All fields are required"
                return@setOnClickListener
            }
            repeat(1){
                dates += date
                min += mins
                max += maxs
                conditions += condition
            }
            if (dates.size >=7) {
                btnEnter.visibility = View.GONE
                textViewEdt.text = "Array populated successfully"
            } else{
                textViewEdt.text= "Data added to array"
            }
        }
        btnClear.setOnClickListener {
            clearArrays()
            btnEnter.visibility = View.VISIBLE
            Toast.makeText(this,"Array cleared", Toast.LENGTH_SHORT).show()
        }
        btnClose.setOnClickListener {
            finishAffinity()
        }

        btnNext.setOnClickListener {
            val intent= Intent(this,DetailedViewScreen::class.java)
            intent.putExtra("dates", dates)
            intent.putExtra("min", min)
            intent.putExtra("max", max)
            intent.putExtra("condition", conditions)
            startActivity(intent)
        }
        btnAverage.setOnClickListener {
            var minSum = 0
            var maxSum = 0
            var size = dates?.size?:0

            val intent=Intent(this,MainScreen::class.java)
            intent.putExtra("minTemp",min)
            intent.putExtra("maxTemp",max)

            val min = intent.getStringArrayExtra("minTemp")
            val max = intent.getStringArrayExtra("maxTemp")


            min?.forEach { time->
                minSum += time.toIntOrNull() ?: 0
            }
            max?.forEach { time->
                maxSum += time.toIntOrNull() ?: 0
            }
            val minAverage = if (size > 0) minSum / size else 0
            val maxAverage = if (size > 0) maxSum / size else 0
            val combineAverage = (minSum + maxSum) / (2*size)

            val result = "Min Average: $minAverage\nmaxAverage: $maxAverage\nCombineAverage:$combineAverage"
            textViewEdt.text = result
        }




    }
}