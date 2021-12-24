package com.horseman.videocall

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_call.*

class CallActivity : AppCompatActivity() {

    var username = ""
    var friendUsername = ""

    var isPeerConnected = false

    var firebaseRef = Firebase.database.getReference("users")

    var isAudio = true
    var isVideo = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        username = intent.getStringExtra("username")!!

        callBtn.setOnClickListener {
            TODO()
        }
        toggleAudioBtn.setOnClickListener {
            TODO()
        }
        toggleVideoBtn.setOnClickListener {
            TODO()
        }

        setupWebView()
    }

    @SuppressLint("JavascriptInterface")
    private fun setupWebView() {
        webView.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest?) {
                request?.grant(request.resources)
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.settings.mediaPlaybackRequiresUserGesture = false
        webView.addJavascriptInterface(javascriptinterface(this), "Android")
        loadVideoCall()
    }

    private fun loadVideoCall() {
        val filePath = "file:android_asset/call.html"
        webView.loadUrl(filePath)

        webView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                
            }
        }
    }

    fun onPeerConnected(){
        isPeerConnected = true
    }
}