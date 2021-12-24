package com.horseman.memes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
     lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.memeImageView)
        fetchData()
    }

    private fun fetchData() {
        val queue = Volley.newRequestQueue(this);
        val url = "https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val url = response.getString("url")
                //val image = findViewById<ImageView>(R.id.memeImageView)
                Glide.with(this).load(url).into(imageView)
            },
            {
                Toast.makeText(this, "failed", Toast.LENGTH_LONG).show()
            })
        queue.add(jsonObjectRequest)
    }

    fun shareMeme(view: View) {}
    fun nextMeme(view: View) {}
}