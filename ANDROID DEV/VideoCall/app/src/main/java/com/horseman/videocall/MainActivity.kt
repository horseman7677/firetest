package com.horseman.videocall

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import kotlinx.android.synthetic.main.activity_main.*
import java.time.Instant
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    val permission =
        arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO)
    val requestcode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!isPermissionGranted()) {
            askPermission()
        }

        Firebase.initialize(this)

       loginBtn.setOnClickListener {
           val username = usernameEdit.text.toString()
           val intent = Intent(this,CallActivity::class.java)
           intent.putExtra("username",username)
           startActivity(intent)
       }
    }

    private fun askPermission() {
        ActivityCompat.requestPermissions(this,permission,requestcode)
    }

    private fun isPermissionGranted(): Boolean {

      permission.forEach {
          if (ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED)
              return false
      }

        return true

    }


}