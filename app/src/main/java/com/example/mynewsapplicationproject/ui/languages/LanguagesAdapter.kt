package com.example.mynewsapplicationproject.ui.languages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsapplicationproject.data.model.Language
import com.example.mynewsapplicationproject.databinding.LanguagesListItemBinding
import com.example.mynewsapplicationproject.ui.newsbylanguage.NewsByLanguageActivity
import com.example.mynewsapplicationproject.utils.AppConstant
import com.example.mynewsapplicationproject.utils.IsoCodes

class LanguagesAdapter(private val languagesList: ArrayList<Language>):
    RecyclerView.Adapter<LanguagesAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: LanguagesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(language: Language) {
            binding.languageText.text = language.name
            itemView.setOnClickListener {
                Log.d("###", "bind: Coming to onclick of language item")
                val clickedItemText = language.name
                Log.d("###", "bind: clickedItemText inside the click item $clickedItemText")
                val isoCode = IsoCodes.languageToIsoCodeMap[clickedItemText]
                Log.d("###", "bind: isocode sent through intent $isoCode")
                val intent = Intent(it.context, NewsByLanguageActivity::class.java)
                intent.putExtra(AppConstant.ISO_CODE_KEY, isoCode)
                ContextCompat.startActivity(it.context, intent, Bundle.EMPTY)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LanguagesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun getItemCount(): Int {
        return languagesList.size;
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(languagesList[position])
    }

    fun addData(list: List<Language>) {
        languagesList.addAll(list)
    }
}