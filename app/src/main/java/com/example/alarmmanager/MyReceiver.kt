package com.example.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra("key")
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Log.d("MyLog", "Alarm message: $message")
    }
}