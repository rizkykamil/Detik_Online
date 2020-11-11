package com.example.detikonline.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.detikonline.R
import com.example.detikonline.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = layoutInflater
        binding = ActivityMainBinding.inflate(inflater)

        setContentView(binding.root)

        setSupportActionBar(binding.homeToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        Glide.with(this).load(R.drawable.logo).into(binding.imgToolbar)

        binding.btnSend.setOnClickListener {
            val data = binding.editText.text
            saveData(data.toString())
        }
    }

    private fun saveData(data: String) {
        binding.textView.text = data
    }

    // kode untuk menampikan m enu pada activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_refresh) {
            Toast.makeText(this, "Menu Refresh ditekan", Toast.LENGTH_SHORT).show()
            binding.editText.text.clear()
        }
        return super.onOptionsItemSelected(item)
    }
}
