package com.example.notifications_broadcastreceiver.receivers
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.notifications_broadcastreceiver.activities.MainActivity.Companion.KEY_NAME
import com.example.notifications_broadcastreceiver.activities.MainActivity.Companion.NAME_SHARED_PREF
import com.example.notifications_broadcastreceiver.notifications.MyNotification

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent1: Intent?) {
        Log.e("actionassa", intent1?.action.toString())
        if(intent1?.action == Intent.ACTION_BOOT_COMPLETED) {
            val sheredPref = context?.getSharedPreferences(NAME_SHARED_PREF, Context.MODE_PRIVATE)
            val time : Long? = sheredPref?.getLong(KEY_NAME, 0)
            MyNotification(context!!).setAlarm(time!!)
        }
        MyNotification(context!!).showNotification("WAKE UP")

    }
}

