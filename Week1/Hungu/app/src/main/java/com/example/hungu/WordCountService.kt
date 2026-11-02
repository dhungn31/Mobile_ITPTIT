package com.example.hungu

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class WordCountService : Service() {

    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {

        fun getService(): WordCountService {
            return this@WordCountService
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    fun countWords(note: String): Int {

        if (note.isBlank()) {
            return 0
        }

        return note.trim()
            .split(Regex("\\s+"))
            .count { it.isNotEmpty() }
    }
}