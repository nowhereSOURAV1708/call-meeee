import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.app.Service
import android.os.IBinder
import com.example.callme.FakeCallActivity
import com.example.callme.R

class CallService : Service() {

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val callerName = intent.getStringExtra("caller_name")
        val callerNumber = intent.getStringExtra("caller_number")

        // Show a fake incoming call screen
        showIncomingCallScreen(callerName, callerNumber)

        // Create a PendingIntent to open your activity when the notification is clicked
        val notificationIntent = Intent(this, FakeCallActivity::class.java)
        notificationIntent.putExtra("caller_name", callerName)
        notificationIntent.putExtra("caller_number", callerNumber)

        // PendingIntent to trigger the activity when the notification is tapped
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Create a persistent notification to keep the service running in the background
        val notification = NotificationCompat.Builder(this, "CALL_CHANNEL")
            .setContentTitle("Fake Call")
            .setContentText("Incoming call from $callerName")
            .setSmallIcon(R.drawable.green) // Change to your own icon
            .setContentIntent(pendingIntent) // Set the PendingIntent for the notification click
            .setAutoCancel(true) // Dismiss the notification when clicked
            .build()

        // Start the service in the foreground with the notification
        startForeground(1, notification)

        return START_STICKY
    }

    private fun showIncomingCallScreen(callerName: String?, callerNumber: String?) {
        // Show your fake call screen (use an Activity to simulate the call)
        val intent = Intent(this, FakeCallActivity::class.java)
        intent.putExtra("caller_name", callerName)
        intent.putExtra("caller_number", callerNumber)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
