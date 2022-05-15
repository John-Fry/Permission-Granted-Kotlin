package br.edu.ifpb.pdm.permissiongranted

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var telaReceiver: TelaReceiver? = null
    private var tela2Receiver: Tela2Receiver? = null
    private var onCharger: Charger? = null
    private lateinit var itf: IntentFilter
    private lateinit var textViewReceiver: TextView
    private lateinit var itf2: IntentFilter
    private lateinit var itCabo: IntentFilter
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.textViewReceiver = findViewById(R.id.textView)
        this.image = findViewById(R.id.imageView)
    }

    override fun onStart() {
        super.onStart()
        Log.i("App_PermissionGranted","On Start")
    }

    override fun onResume() {
        super.onResume()
        Log.i("App_PermissionGranted","On Resume")
        if (this.telaReceiver == null) {
            this.telaReceiver = TelaReceiver()
            val itf = IntentFilter().apply {
                addAction(Intent.ACTION_USER_PRESENT)
            }
        }
        registerReceiver(this.telaReceiver, itf)

        if (this.tela2Receiver == null) {
            this.tela2Receiver = Tela2Receiver()
            this.itf2 = IntentFilter().apply {
                addAction(Intent.ACTION_USER_PRESENT)
            }
        }
        registerReceiver(this.telaReceiver, itf2)

        if (this.onCharger == null) {
            this.onCharger = Charger()
            this.itCabo = IntentFilter().apply {
                addAction(Intent.ACTION_POWER_CONNECTED)
                addAction(Intent.ACTION_POWER_DISCONNECTED)
            }
        }
        registerReceiver(this.onCharger, this.itCabo)
    }

    override fun onPause() {
        super.onPause()
        Log.i("App_PermissionGranted","On Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("App_PermissionGranted","On Stop")
        unregisterReceiver(this.onCharger)
    }

    inner class Tela2Receiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            this@MainActivity.textViewReceiver.text = "Screen Unlocked"
        }
    }

    inner class Charger: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action.equals(Intent.ACTION_POWER_CONNECTED)) {
                this@MainActivity.textViewReceiver.text = "Conectado"
                this@MainActivity.image.setImageResource(resources.getIdentifier(
                    "bolt", "drawable",packageName))

            }
            else {
                this@MainActivity.textViewReceiver.text = "Desconectado"
                this@MainActivity.image.setImageResource(resources.getIdentifier(
                    "power_off", "drawable",packageName))
            }
        }
    }
}