package com.vpnrelay.app

import android.content.Intent
import android.net.VpnService as AndroidVpnService
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val deployId = findViewById<EditText>(R.id.deploymentIdInput)
        val authKey = findViewById<EditText>(R.id.authKeyInput)
        val connectBtn = findViewById<Button>(R.id.connectButton)

        connectBtn.setOnClickListener {
            val intent = AndroidVpnService.prepare(this)
            if (intent != null) startActivityForResult(intent, 100)
            else startVpn(deployId.text.toString(), authKey.text.toString())
        }
    }

    private fun startVpn(id: String, key: String) {
        if (id.isBlank() || key.isBlank()) {
            Toast.makeText(this, "لطفاً مقادیر را کامل وارد کنید", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, VpnService::class.java)
        intent.putExtra("deploymentId", id)
        intent.putExtra("authKey", key)
        startService(intent)
    }
}