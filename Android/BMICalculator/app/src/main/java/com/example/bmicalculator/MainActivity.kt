package com.example.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val button = findViewById<Button>(R.id.btnCalculate)

        button.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validateInput(weight,height)){
                val bmi = weight.toFloat()/((height.toFloat()/100)*(height.toFloat()/100))
                val bmi2Digit = String.format("%.2f",bmi).toFloat()
                displayResult(bmi2Digit)
            }
        }

    }

    fun validateInput(weight:String? , height:String?) : Boolean{
        return when{
            weight.isNullOrBlank()->{
                Toast.makeText(this,"Weight is Empty",Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrBlank()->{
                Toast.makeText(this,"Height is Empty",Toast.LENGTH_SHORT).show()
                return false
            }else->{
                return true
            }
        }
    }
    fun displayResult(bmi:Float){
        val textResult = findViewById<TextView>(R.id.tvResult)
        val textDescription = findViewById<TextView>(R.id.tvdescription)
        val textRange = findViewById<TextView>(R.id.tvRange)

        textResult.text = bmi.toString()
        textRange.text = "(Normal range is 18.5-24.9)"

        var textHealth = ""
        var color = 0

        when{
            bmi<18.5->{
                textHealth = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.5..24.99->{
                textHealth = "Healthy"
                color = R.color.Healthy
            }
            bmi in 25.00..29.99->{
                textHealth = "Overweight"
                color = R.color.Over_weight
            }
            bmi>29.99-> {
                textHealth = "Obese"
                color = R.color.Obese
            }
        }

        textDescription.text = textHealth
        textDescription.setTextColor(ContextCompat.getColor(this,color))

    }

}