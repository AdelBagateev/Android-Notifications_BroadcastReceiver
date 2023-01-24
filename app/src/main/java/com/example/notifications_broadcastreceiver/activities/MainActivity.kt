package com.example.notifications_broadcastreceiver.activities

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notifications_broadcastreceiver.notifications.MyNotification
import com.example.notifications_broadcastreceiver.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding ?= null
    private var notification : MyNotification?= null
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




//        binding = ActivityMainBinding.inflate(layoutInflater).also {
//            setContentView(it.root)
//        }
        val cal = Calendar.getInstance()
        binding?.run {
            tvTime.setOnClickListener {
                val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    cal.set(Calendar.MINUTE, minute)
                    cal.set(Calendar.SECOND, 0)
                    tvTime.text = SimpleDateFormat("HH:mm").format(cal.time)
                }
                TimePickerDialog(this@MainActivity,
                    timeSetListener,
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    false)
                    .show()
            }
            tvDate.setOnClickListener {
                val dpd = DatePickerDialog(this@MainActivity, { view, year, monthOfYear, dayOfMonth ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    tvDate.text = dayOfMonth.toString() + "." +  monthOfYear + "." + year

                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) , cal.get(Calendar.DAY_OF_MONTH))
                dpd.show()
            }
            btnStartAlarm.setOnClickListener {
                saveInSharedPref(cal.timeInMillis)
                MyNotification(this@MainActivity).setAlarm(cal.timeInMillis)
            }
            btnStopAlarm.setOnClickListener {
                MyNotification(this@MainActivity).cancelAlarm()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        notification = null
    }
    companion object{
        const val NAME_SHARED_PREF = "time_for_the_alarm"
        const val KEY_NAME = "time_in_millis"
    }

    private fun saveInSharedPref(time : Long) {
        val sheredPref = applicationContext.getSharedPreferences(NAME_SHARED_PREF, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sheredPref.edit()
        editor.putLong(KEY_NAME, time)
        editor.apply()
    }

}