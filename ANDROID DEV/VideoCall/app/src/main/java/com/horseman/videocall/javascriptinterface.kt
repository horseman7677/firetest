package com.horseman.videocall

import android.webkit.JavascriptInterface

class javascriptinterface(val callActivity : CallActivity) {
    @JavascriptInterface
    public  fun onPeerConnected(){
        callActivity.onPeerConnected()
    }
}