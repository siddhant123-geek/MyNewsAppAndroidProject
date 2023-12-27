package com.example.mynewsapplicationproject.ui.countries

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsapplicationproject.data.model.Country
import com.example.mynewsapplicationproject.databinding.CountryListItemBinding
import com.example.mynewsapplicationproject.ui.newsbycountry.NewsByCountryActivity
import com.example.mynewsapplicationproject.ui.topheadline.TopHeadlineActivity
import com.example.mynewsapplicationproject.utils.AppConstant.ISO_CODE_KEY
import com.example.mynewsapplicationproject.utils.IsoCodes
import kotlinx.coroutines.NonDisposableHandle.parent


class CountriesPageAdapter(private val countriesList: ArrayList<Country>):
    RecyclerView.Adapter<CountriesPageAdapter.DataViewHolder>() {

        class DataViewHolder(private val binding: CountryListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(country: Country) {
                binding.countryText.text = country.name
                itemView.setOnClickListener {
                    val clickedItemText = country.name
                    val isoCode = IsoCodes.countryToISOCodeMap[clickedItemText]
                    val intent = Intent(it.context, NewsByCountryActivity::class.java)
                    intent.putExtra(ISO_CODE_KEY, isoCode)
                    startActivity(it.context, intent, Bundle.EMPTY)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
            return DataViewHolder(
                CountryListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false)
            )
    }

    override fun getItemCount(): Int {
        return countriesList.size;
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(countriesList[position])
    }

    fun addData(list: List<Country>) {
        countriesList.addAll(list)
    }
}