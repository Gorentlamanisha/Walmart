package com.example.walmartcountriesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(private var countries: List<Country> = emptyList()) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameRegionCodeTextView: TextView = itemView.findViewById(R.id.nameRegionCodeTextView)
        private val capitalTextView: TextView = itemView.findViewById(R.id.capitalTextView)

        fun bind(country: Country) {
            "${country.name}, ${country.region}     ${country.code}".apply { this@CountryViewHolder.nameRegionCodeTextView.text =
                this
            }
            this.capitalTextView.text = country.capital
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(this.countries[position])
    }

    override fun getItemCount() = this.countries.size

    fun updateCountries(newCountries: List<Country>) {
        this.countries = newCountries
        this.notifyDataSetChanged()
    }
}