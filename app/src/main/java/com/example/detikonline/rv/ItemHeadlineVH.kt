package com.example.detikonline.rv

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.detikonline.data.DataNews
import com.example.detikonline.databinding.ItemHeadlineBinding

class ItemHeadlineVH(binding: ItemHeadlineBinding) : RecyclerView.ViewHolder(binding.root) {
    private val image = binding.imgHeadline
    private val title = binding.textTitle
    private val date = binding.textDate
    private val root = binding.root

    fun bind(berita: DataNews) {
        title.text = berita.title
        date.text = berita.desc
        Glide.with(root.context).load(berita.photo).into(image)
    }
}
