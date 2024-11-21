package com.example.callme

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etCallerName: EditText = findViewById(R.id.etCallerName)
        val etCallerNumber: EditText = findViewById(R.id.etCallerNumber)
        val btnSetCall: Button = findViewById(R.id.btnSetCall)

        btnSetCall.setOnClickListener {
            // Show a dialog with options
            val options = arrayOf("Call Immediately", "After 10 seconds", "After 20 seconds", "After 30 seconds", "After 40 seconds", "Pick a Time")

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Schedule Fake Call")
            builder.setItems(options) { _, which ->
                when (which) {
                    0 -> { // Call Immediately
                        scheduleFakeCall(0, 0, etCallerName.text.toString(), etCallerNumber.text.toString(), immediate = true)
                    }
                    1 -> { // After 10 seconds
                        scheduleFakeCallWithDelay(10, etCallerName.text.toString(), etCallerNumber.text.toString())
                    }
                    2 -> { // After 20 seconds
                        scheduleFakeCallWithDelay(20, etCallerName.text.toString(), etCallerNumber.text.toString())
                    }
                    3 -> { // After 30 seconds
                        scheduleFakeCallWithDelay(30, etCallerName.text.toString(), etCallerNumber.text.toString())
                    }
                    4 -> { // After 40 seconds
                        scheduleFakeCallWithDelay(40, etCallerName.text.toString(), etCallerNumber.text.toString())
                    }
                    5 -> { // Pick a Time
                        openTimePickerDialog()
                    }
                }
            }
            builder.show()
        }
    }

    private fun scheduleFakeCall(hour: Int, minute: Int, callerName: String, callerNumber: String, immediate: Boolean = false) {
        if (immediate) {
            // Start Fake Call Immediately
            val intent = Intent(this, FakeCallActivity::class.java)
            intent.putExtra("caller_name", callerName)
            intent.putExtra("caller_number", callerNumber)
            startActivity(intent)
        } else {
            val currentTime = Calendar.getInstance()
            val callTime = Calendar.getInstance()
            callTime.set(Calendar.HOUR_OF_DAY, hour)
            callTime.set(Calendar.MINUTE, minute)
            callTime.set(Calendar.SECOND, 0)

            val delayMillis = callTime.timeInMillis - currentTime.timeInMillis
            if (delayMillis > 0) {
                scheduleFakeCallWithDelay((delayMillis / 1000).toInt(), callerName, callerNumber)
            } else {
                Toast.makeText(this, "Selected time is in the past!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun scheduleFakeCallWithDelay(delaySeconds: Int, callerName: String, callerNumber: String) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            // Start the Fake Call Activity after delay
            val intent = Intent(this, FakeCallActivity::class.java)
            intent.putExtra("caller_name", callerName)
            intent.putExtra("caller_number", callerNumber)
            startActivity(intent)
        }, delaySeconds * 1000L)

        Toast.makeText(this, "Fake Call scheduled in $delaySeconds seconds", Toast.LENGTH_SHORT).show()
    }

    private fun openTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { _, hourOfDay, minute ->
            // Format time as HH:mm
            val formattedTime = String.format("%02d:%02d", hourOfDay, minute)
            Toast.makeText(this, "Fake Call Set at $formattedTime", Toast.LENGTH_SHORT).show()

            // Schedule the fake call at the selected time
            scheduleFakeCall(hourOfDay, minute, findViewById<EditText>(R.id.etCallerName).text.toString(), findViewById<EditText>(R.id.etCallerNumber).text.toString())
        }, currentHour, currentMinute, true)

        timePickerDialog.show()
    }
}
