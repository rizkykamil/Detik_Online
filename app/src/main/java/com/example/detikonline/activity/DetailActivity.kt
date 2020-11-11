package com.example.detikonline.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.detikonline.databinding.ActivityDetailBinding
import com.example.detikonline.remote.pojo.ArticlesItem

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var dataIntent: ArticlesItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = layoutInflater
        binding = ActivityDetailBinding.inflate(inflater)

        setContentView(binding.root)
        // Set Action Bar
        setSupportActionBar(binding.homeToolbar)
        // Tampilkan tombol back pada ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Dapatkan Data dari intent
        dataIntent = intent.getParcelableExtra("BERITA_ITEM") as ArticlesItem
        // Set Judul pada ActionBar
        supportActionBar?.title = dataIntent.title

        binding.webView.run {
            // Isi webview client
            webChromeClient = MyWebChromeClient()
            webViewClient = MyWebViewClient()
            // aktifkan javascript pada webview client
            @SuppressLint("SetJavaScriptEnabled")
            settings.javaScriptEnabled = true
            // load url
            loadUrl(dataIntent.url)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    // buat WebChromeClient
    internal class MyWebChromeClient : WebChromeClient()

    // buat WebViewClient
    internal class MyWebViewClient : WebViewClient()
}