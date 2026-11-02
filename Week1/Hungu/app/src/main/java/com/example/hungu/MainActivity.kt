package com.example.hungu

import android.content.BroadcastReceiver
import android.content.ClipData
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.hungu.ui.theme.HunguTheme
import java.io.File

class MainActivity : ComponentActivity() {

    private var wordCountService: WordCountService? = null
    private var isBound = false
    private var receiverRegistered = false

    private val serviceReady = mutableStateOf(false)

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(
            name: ComponentName?,
            binder: IBinder?
        ) {
            val localBinder = binder as WordCountService.LocalBinder
            wordCountService = localBinder.getService()
            serviceReady.value = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            wordCountService = null
            serviceReady.value = false
        }
    }

    private val powerReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Intent.ACTION_POWER_CONNECTED) {

                Toast.makeText(
                    this@MainActivity,
                    "Đang được cắm sạc!",
                    Toast.LENGTH_SHORT
                ).show()

                Log.d("PowerReceiver", "Đã cắm sạc")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HunguTheme {

                var note by rememberSaveable {
                    mutableStateOf("")
                }

                var result by rememberSaveable {
                    mutableStateOf("Kết quả đây này")
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "Ghi chú học tập",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    OutlinedTextField(
                        value = note,
                        onValueChange = {
                            note = it
                        },
                        label = {
                            Text("Nhập nội dung ghi chú")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 5
                    )

                    Button(
                        onClick = {
                            val service = wordCountService

                            if (service != null) {
                                val count = service.countWords(note)
                                result = "Số từ trong ghi chú: $count"
                            } else {
                                result = "Service chưa kết nối"
                            }
                        },
                        enabled = serviceReady.value,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Đếm số từ")
                    }

                    Text(
                        text = result,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Button(
                        onClick = {
                            if (note.isNotBlank()) {
                                shareNote(note)
                            } else {
                                Toast.makeText(
                                    this@MainActivity,
                                    "nhập ghi chú đi baby",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Chia sẻ ghi chú")
                    }

                    Text(
                        text = if (serviceReady.value) {
                            "Service: Đã kết nối"
                        } else {
                            "Service: Đang kết nối... chờ tí :>"
                        }
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        val serviceIntent = Intent(this, WordCountService::class.java)

        isBound = bindService(
            serviceIntent,
            connection,
            Context.BIND_AUTO_CREATE
        )

        ContextCompat.registerReceiver(
            this,
            powerReceiver,
            IntentFilter(Intent.ACTION_POWER_CONNECTED),
            ContextCompat.RECEIVER_NOT_EXPORTED
        )

        receiverRegistered = true
    }

    override fun onStop() {
        super.onStop()

        if (receiverRegistered) {
            unregisterReceiver(powerReceiver)
            receiverRegistered = false
        }

        if (isBound) {
            unbindService(connection)
            isBound = false
            wordCountService = null
            serviceReady.value = false
        }
    }

    private fun shareNote(note: String) {

        val directory = File(cacheDir, "shared")

        if (!directory.exists()) {
            directory.mkdirs()
        }

        val file = File(directory, "note.txt")
        file.writeText(note)

        val uri = FileProvider.getUriForFile(
            this,
            "$packageName.fileprovider",
            file
        )

        val shareIntent = Intent(Intent.ACTION_SEND).apply {

            type = "text/plain"

            putExtra(Intent.EXTRA_STREAM, uri)

            clipData = ClipData.newUri(
                contentResolver,
                "Ghi chú học tập",
                uri
            )

            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        startActivity(
            Intent.createChooser(
                shareIntent,
                "Chia sẻ ghi chú"
            )
        )
    }
}