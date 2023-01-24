package com.example.notifications_broadcastreceiver.notifications
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.toBitmapOrNull
import com.example.notifications_broadcastreceiver.R
import com.example.notifications_broadcastreceiver.activities.SecondActivity
import com.example.notifications_broadcastreceiver.receivers.AlarmReceiver

class MyNotification(private val context : Context) {

    fun cancelAlarm() {
        val alarmManager : AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val penIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            0
        )
        alarmManager.cancel(penIntent)
    }

    fun setAlarm(time: Long) {
        val alarmManager : AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val penIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            0
        )
        alarmManager.set(AlarmManager.RTC, time, penIntent)
    }

    fun showNotification (text:String){
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val vibrations =arrayOf(100L, 200L).toLongArray()
        val sound: Uri = Uri.parse(
            "android.resource://" +
                    context.packageName + "/"
                    + R.raw.the_lion_sleep_to_night
        )
        val intent = Intent(context, SecondActivity::class.java)
        val penIntent = PendingIntent.getActivity(
            context,
            100,
            intent,
            0
        )

        val builder : NotificationCompat.Builder =
            NotificationCompat.Builder(context,  context.getString(R.string.notification_chanel_id))
                .setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
                .setContentTitle("ALARM")
                .setVibrate(vibrations)
                .setLights(Color.GREEN, 100, 500)
                .setContentText(text)
                .setAutoCancel(true)
                .setContentIntent(penIntent)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        context.getDrawable(R.drawable.img)?.toBitmapOrNull()?.also {
            builder.setLargeIcon(it)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                context.getString(R.string.notification_chanel_id),
                context.getString(R.string.notification_chanel_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                lightColor = Color.GREEN
                vibrationPattern = vibrations
                setSound(sound, audioAttributes)
            }.also {
                notificationManager.createNotificationChannel(it)
            }
        } else {
            builder
                .setVibrate(vibrations)
                .setSound(sound)
                .setLights(Color.BLUE, 100, 500)
        }

        notificationManager.notify(1010, builder.build())

    }
}
