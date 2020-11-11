package com.example.detikonline.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.detikonline.R
import com.example.detikonline.data.DataNews
import com.example.detikonline.data.NewsModel
import com.example.detikonline.databinding.ActivityNewsBinding
import com.example.detikonline.rv.ItemHeadlineAdapter
import com.example.detikonline.rv.ItemListNewsAdapter
import kotlinx.android.synthetic.main.item_headline.*

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    private lateinit var adapter: ItemListNewsAdapter
    private lateinit var adapterHorizontal: ItemHeadlineAdapter
    private lateinit var data: List<DataNews>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // deklarasikan binding
        val inflater = layoutInflater
        binding = ActivityNewsBinding.inflate(inflater)

        setContentView(binding.root)

        // gunakan toolbar buatan sendiri
        setSupportActionBar(binding.homeToolbar)

        // hilangkan judul di toolbar dengan set false
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // masukkan gambar ke dalam imageview Toolbar
        Glide.with(this).load(R.drawable.logo).into(binding.imgToolbar)

        // Data
        data = NewsModel.newslist // mendapatkan data daftar berita
        val beritaHeadline = data[0]  // data pertama adalah index ke-0
        val beritaLainnya = data.drop(1) // menghilangkan satu data di awal

        // Recyclerview vertical
        adapter = ItemListNewsAdapter() // menggunakan adapter ItemListNews
        binding.rvNews.setHasFixedSize(true) // recyclerview memiliki ukuran yang ditentukan
        binding.rvNews.layoutManager =
            LinearLayoutManager(this) // recyclerview vertical (default value)
        binding.rvNews.adapter = adapter
        // menambahkan data pada adapter recycler vertical
//        adapter.addData(beritaLainnya)

        // Recyclerview Horizontal
        adapterHorizontal = ItemHeadlineAdapter() // menggunakan adapter ItemHeadlineAdapter
        binding.rvHorizontal.run {
            // menggunakan Layout Manager Horizontal untuk efek scroll ke samping
            layoutManager =
                LinearLayoutManager(this@NewsActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = adapterHorizontal
        }

        // menambahkan data pada adapter recycler
        adapterHorizontal.addData(beritaLainnya)

        // Mengisi data pada include id item_headline
        binding.itemHeadline.run {
            text_title.text = beritaHeadline.title
            text_date.text = beritaHeadline.desc
            Glide.with(this@NewsActivity).load(beritaHeadline.photo).into(img_headline)
        }
    }
}
