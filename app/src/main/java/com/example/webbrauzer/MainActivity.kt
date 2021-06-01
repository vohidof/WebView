 package com.example.webbrauzer

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import android.media.MediaPlayer.create as create1

class MainActivity : AppCompatActivity() {

    var url = "https://kun.uz/"
    lateinit var progressDialog:ProgressDialog

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        web_view.loadUrl(url)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")

        val mediaPlayer = MediaPlayer.create(this, R.raw.audio)

        web_view.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                /*progress_bar.visibility = View.VISIBLE*/

                progressDialog.show()

                mediaPlayer.start()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                progressDialog.hide()

                mediaPlayer.pause()

                /*val animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.my_anim)
                progress_bar.startAnimation(animation)
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        progress_bar.visibility = View.GONE
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })*/

            }
        }

    }
}