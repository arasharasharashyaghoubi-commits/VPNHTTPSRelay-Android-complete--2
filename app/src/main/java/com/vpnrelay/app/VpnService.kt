package com.vpnrelay.app

import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import kotlinx.coroutines.*
import java.io.FileInputStream
import java.io.FileOutputStream

class VpnService : VpnService() {

    private var vpnInterface: ParcelFileDescriptor? = null
    private val client = RelayClient()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val id = intent?.getStringExtra("deploymentId") ?: ""
        val key = intent?.getStringExtra("authKey") ?: ""
        startRelay(id, key)
        return START_STICKY
    }

    private fun startRelay(id: String, key: String) {
        vpnInterface = Builder()
            .setSession("VPNRelay")
            .addAddress("10.0.0.2", 32)
            .addDnsServer("8.8.8.8")
            .addRoute("0.0.0.0", 0)
            .establish()

        CoroutineScope(Dispatchers.IO).launch {
            val input = FileInputStream(vpnInterface!!.fileDescriptor)
            val output = FileOutputStream(vpnInterface!!.fileDescriptor)
            ProxyServer(client, id, key, input, output).run()
        }
    }

    override fun onDestroy() {
        vpnInterface?.close()
        super.onDestroy()
    }
}