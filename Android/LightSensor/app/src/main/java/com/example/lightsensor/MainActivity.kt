package com.example.lightsensor

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity(),SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var brightness:Sensor? = null
    private lateinit var textview: TextView
    private lateinit var layout : ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview = findViewById(R.id.tvmain)
        layout = findViewById(R.id.clmain)
        setUpSensor()

    }

    private fun setUpSensor(){
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        brightness = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    private fun brightness(brightness:Float) : String{
        return when(brightness.toFloat()){
            in 0.00..1.00-> "Pitch Black"
            in 1.01..100.00 ->"Yellow"
            in 100.01..200.00->"Grey"
            in 200.01..1000.00->"Normal"

            else -> {"Incredibly Bright"}
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_LIGHT){
            val light = event.values[0]
            textview.text = "Sensor : ${light} \n ${brightness(light)}"
            when(light){
                in 0.00..1.00->layout.setBackgroundColor(Color.BLACK)
                in 2.00..100.00->layout.setBackgroundColor(Color.YELLOW)
                in 101.00..200.00->layout.setBackgroundColor(Color.GREEN)
                in 201.00..1000.00->layout.setBackgroundColor(Color.BLUE)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,brightness,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }


}