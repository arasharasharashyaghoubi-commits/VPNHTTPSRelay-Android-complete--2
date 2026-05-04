package com.vpnrelay.app

import java.io.*
import kotlinx.coroutines.*

class ProxyServer(
    private val relay: RelayClient,
    private val scriptId: String,
    private val authKey: String,
    private val input: InputStream,
    private val output: OutputStream
) {

    suspend fun run() = withContext(Dispatchers.IO) {
        val buffer = ByteArray(4096)
        while (true) {
            val len = input.read(buffer)
            if (len <= 0) break
        }
    }
}