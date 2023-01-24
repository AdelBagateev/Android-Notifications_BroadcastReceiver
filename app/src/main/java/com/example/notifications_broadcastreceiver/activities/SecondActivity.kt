package com.example.notifications_broadcastreceiver.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notifications_broadcastreceiver.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private var binding : ActivitySecondBinding ?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
    }
}