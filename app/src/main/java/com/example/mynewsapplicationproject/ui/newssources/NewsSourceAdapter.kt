package com.example.mynewsapplicationproject.ui.newssources

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsapplicationproject.data.model.Source
import com.example.mynewsapplicationproject.databinding.ListItemBinding

class NewsSourceAdapter(
    private val sourcesList: ArrayList<Source>
) : RecyclerView.Adapter<NewsSourceAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(source: Source) {
            binding.contentText.text = source.name
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(source.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return sourcesList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(sourcesList[position])

    fun addData(list: List<Source>) {
        sourcesList.addAll(list)
    }

}