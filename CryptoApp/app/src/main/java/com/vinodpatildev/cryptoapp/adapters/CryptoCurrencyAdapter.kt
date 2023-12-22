package com.vinodpatildev.cryptoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vinodpatildev.cryptoapp.databinding.RcvCryptoCurrencyListItemBinding
import com.vinodpatildev.cryptoapp.models.CryptoCurrency

class CryptoCurrencyAdapter(
    private val cryptoCurrencyList: List<CryptoCurrency>,
    private val listener: (cryptoCurrency: CryptoCurrency) -> Unit
) : RecyclerView.Adapter<CryptoCurrencyAdapter.CryptoCurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoCurrencyViewHolder {
        val binding = RcvCryptoCurrencyListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CryptoCurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoCurrencyViewHolder, position: Int) {
        val cryptoCurrency = cryptoCurrencyList[position]
        holder.bind(cryptoCurrency, listener)
    }

    override fun getItemCount(): Int {
        return cryptoCurrencyList.size
    }

    inner class CryptoCurrencyViewHolder(val binding: RcvCryptoCurrencyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            cryptoCurrency: CryptoCurrency,
            listener: (cryptoCurrency: CryptoCurrency) -> Unit
        ) {
            binding.apply {
                tvCryptoCurrencyName.text = cryptoCurrency.name
                tvCryptoCurrencySymbol.text = cryptoCurrency.symbol
                tvCryptoCurrencyExchange.text = cryptoCurrency.name_full
            }

            binding.root.setOnClickListener {
                listener(cryptoCurrency)
            }
        }
    }
}