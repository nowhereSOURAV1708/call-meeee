package com.example.callme

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.widget.ImageView
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide

class FakeCallActivity : AppCompatActivity() {

    private var ringtone: Ringtone? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fake_call)

        // Get caller name and number from the Intent extras
        val callerName = intent.getStringExtra("caller_name")
        val callerNumber = intent.getStringExtra("caller_number")

        // Initialize views
        val tvCallerName: TextView = findViewById(R.id.callerName)
        val tvCallerNumber: TextView = findViewById(R.id.callerNumber)
        val btnAcceptCall: ImageView = findViewById(R.id.btnAcceptCall)
        val btnRejectCall: ImageView = findViewById(R.id.btnRejectCall)

        // Set caller name and number
        tvCallerName.text = callerName
        tvCallerNumber.text = callerNumber

        // Load GIF into the accept call button using Glide
        Glide.with(this)
            .asGif()
            .load(R.drawable.callg) // Replace with your GIF file
            .into(btnAcceptCall)

        Glide.with(this)
            .asGif()
            .load(R.drawable.callrrr) // Replace with your GIF file
            .into(btnRejectCall)

        // Button actions
        btnAcceptCall.setOnClickListener {
            Toast.makeText(this, "Call Accepted!", Toast.LENGTH_SHORT).show()
            finish() // Close the fake call activity
        }

        btnRejectCall.setOnClickListener {
            Toast.makeText(this, "Call Rejected!", Toast.LENGTH_SHORT).show()
            finish() // Close the fake call activity
        }

        // Play ringtone
        playRingtone()
    }

    private fun playRingtone() {
        // Get the default ringtone URI
        val ringtoneUri: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)

        // Check if URI is valid
        if (ringtoneUri != null) {
            ringtone = RingtoneManager.getRingtone(this, ringtoneUri)
            ringtone?.play()
        } else {
            Toast.makeText(this, "No ringtone found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop the ringtone when the activity is destroyed
        ringtone?.stop()
    }
}
