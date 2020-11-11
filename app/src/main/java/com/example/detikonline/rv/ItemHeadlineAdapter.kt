package com.example.detikonline.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.detikonline.data.DataNews
import com.example.detikonline.databinding.ItemHeadlineBinding


class ItemHeadlineAdapter : RecyclerView.Adapter<ItemHeadlineVH>() {
    private val dataBerita = ArrayList<DataNews>()

    fun addData(data: List<DataNews>) {
        dataBerita.clear()
        dataBerita.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHeadlineVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHeadlineBinding.inflate(inflater, parent, false)
        return ItemHeadlineVH(binding)
    }

    override fun getItemCount(): Int {
        return dataBerita.size
    }

    override fun onBindViewHolder(holder: ItemHeadlineVH, position: Int) {
        val data = dataBerita[position]
        holder.bind(data)
    }
}