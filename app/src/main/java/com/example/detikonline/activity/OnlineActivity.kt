package com.example.detikonline.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.detikonline.R
import com.example.detikonline.databinding.ActivityOnlineBinding
import com.example.detikonline.remote.RetrofitInterface
import com.example.detikonline.remote.RetrofitService
import com.example.detikonline.remote.pojo.ArticlesItem
import com.example.detikonline.rv.ItemListNewsAdapter
import kotlinx.coroutines.launch

class OnlineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnlineBinding

    // deklarasikan adapter yang akan digunakan pada RecyclerView
    private lateinit var adapterRV: ItemListNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = layoutInflater
        binding = ActivityOnlineBinding.inflate(inflater)

        setContentView(binding.root)

        setSupportActionBar(binding.homeToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        Glide.with(this).load(R.drawable.logo).into(binding.imgToolbar)

        // isi variabel adapterRV dengan class ItemListNewsAdapter
        adapterRV = ItemListNewsAdapter()

        // atur recyclerview rvNews yang akan digunakan
//        binding.rvNews.setHasFixedSize(true)
//        binding.rvNews.layoutManager = LinearLayoutManager(this)
//        binding.rvNews.adapter = adapterRV
        // versi yang telah disederhanakan
        binding.rvNews.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@OnlineActivity)
            adapter = adapterRV
        }

        val retrofitService = RetrofitService.buildService(RetrofitInterface::class.java)
        lifecycleScope.launch {
            val request = retrofitService.topHeadlines("id")
            if (request.isSuccessful) {
                Toast.makeText(
                    this@OnlineActivity,
                    request.body()?.totalResults.toString(),
                    Toast.LENGTH_SHORT
                ).show()

                request.body()?.articles?.let { data ->
                    // Mengisi kekosongan Headline
                    val beritaPertama = data[0]
                    binding.itemHeadline.run {
                        textTitle.text = beritaPertama?.title
                        textDate.text = beritaPertama?.publishedAt
                        beritaPertama?.let {
                            Glide.with(this@OnlineActivity)
                                .load(it.urlToImage)
                                .into(imgHeadline)
                        }
                        // Tambahkan Onlick Listener pada itemHeadline
                        root.setOnClickListener {
                            val intent = Intent(it.context, DetailActivity::class.java).apply {
                                putExtra("BERITA_ITEM", beritaPertama)
                            }
                            it.context.startActivity(intent)
                        }
                    }

                    // buat sebuah array kosong yang hanya bisa diisi oleh ArticlesItem
                    val dataResult = arrayListOf<ArticlesItem>()

                    // lakukan perulangan pada setiap item dari data
                    data.forEach { item ->
                        // jika item tidak bernilai null maka
                        item?.let {
                            // tambahkan item ke dalam dataResult
                            dataResult.add(it)
                        }
                    }
                    // masukkan dataResult ke dalam adapterRV
                    adapterRV.addData(dataResult)
                }
            } else {
                Log.e("OnlineActivity", request.message())
            }
        }
    }
}
