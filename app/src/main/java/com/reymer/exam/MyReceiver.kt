package com.reymer.exam

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.util.Log
import java.lang.StringBuilder

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals("android.intent.action.BATTERY_CHANGED")) {
            val batteryStatus: Intent? =
                IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
                    context.registerReceiver(null, ifilter)
                }

            val batteryPct: Int? = batteryStatus?.let { intent ->
                val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                level * 100 / scale
            }

            StringBuilder().apply {
                append("Battery: ${batteryPct}%\n")
                toString().also { log ->
                    Log.i("MyReceiverAction", log)
                }
            }
        }
    }
}