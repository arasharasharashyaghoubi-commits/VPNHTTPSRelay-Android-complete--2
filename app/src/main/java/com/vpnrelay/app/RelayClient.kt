package com.vpnrelay.app

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import com.google.gson.Gson
import android.util.Base64

class RelayClient {

    private val gson = Gson()
    private val client = OkHttpClient()

    data class RequestPkt(
        val k: String,
        val m: String,
        val u: String,
        val h: Map<String, String>?,
        val b: String?,
        val ct: String?,
        val r: Boolean?
    )

    data class ResponsePkt(
        val s: Int,
        val h: Map<String, String>?,
        val b: String?
    )

    fun send(scriptId: String, authKey: String, method: String, url: String, headers: Map<String, String>, body: ByteArray?): ResponsePkt? {
        val reqBody = gson.toJson(
            RequestPkt(
                authKey, method, url, headers,
                body?.let { Base64.encodeToString(it, Base64.NO_WRAP) },
                "application/octet-stream", true
            )
        )

        val req = Request.Builder()
            .url("https://script.google.com/macros/s/$scriptId/exec")
            .post(reqBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        val resp = client.newCall(req).execute()
        if (!resp.isSuccessful) return null

        return gson.fromJson(resp.body?.string(), ResponsePkt::class.java)
    }
}