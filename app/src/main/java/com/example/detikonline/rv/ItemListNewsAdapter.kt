package com.example.detikonline.rv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.detikonline.activity.DetailActivity
import com.example.detikonline.databinding.ItemListNewsBinding
import com.example.detikonline.remote.pojo.ArticlesItem

class ItemListNewsAdapter : RecyclerView.Adapter<ItemListNewsViewHolder>() {
    private val listNews = ArrayList<ArticlesItem>()

    fun addData(data: List<ArticlesItem>) {
        listNews.clear()
        listNews.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListNewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListNewsBinding.inflate(layoutInflater, parent, false)
        return ItemListNewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    override fun onBindViewHolder(holder: ItemListNewsViewHolder, position: Int) {
        val data = listNews[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
//            Toast.makeText(holder.itemView.context, data.title, Toast.LENGTH_SHORT).show()

            // tambahkan Intent
            val intent = Intent(it.context, DetailActivity::class.java).apply {
                putExtra("BERITA_ITEM", data)
            }
            it.context.startActivity(intent)
        }
    }
}
