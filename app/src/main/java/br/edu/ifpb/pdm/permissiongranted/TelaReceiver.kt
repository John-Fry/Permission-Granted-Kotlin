package br.edu.ifpb.pdm.permissiongranted

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class TelaReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Screen Unlocked", Toast.LENGTH_LONG).show()
    }


}