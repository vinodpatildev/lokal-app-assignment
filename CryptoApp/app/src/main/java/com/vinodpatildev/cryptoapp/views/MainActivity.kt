package com.vinodpatildev.cryptoapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinodpatildev.cryptoapp.R
import com.vinodpatildev.cryptoapp.adapters.CryptoCurrencyAdapter
import com.vinodpatildev.cryptoapp.databinding.ActivityMainBinding
import com.vinodpatildev.cryptoapp.models.CryptoCurrency

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var cryptoCurrencyListAdapter: CryptoCurrencyAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        cryptoCurrencyListAdapter =
            CryptoCurrencyAdapter(
                listOf(
                    CryptoCurrency("abc1", 123, "abc1", "abc", "abc"),
                    CryptoCurrency("abc2", 123, "abc2", "abc", "abc"),
                    CryptoCurrency("abc3", 123, "abc3", "abc", "abc"),
                    CryptoCurrency("abc4", 123, "abc4", "abc", "abc"),
                    CryptoCurrency("abc1", 123, "abc1", "abc", "abc"),
                    CryptoCurrency("abc2", 123, "abc2", "abc", "abc"),
                    CryptoCurrency("abc3", 123, "abc3", "abc", "abc"),
                    CryptoCurrency("abc4", 123, "abc4", "abc", "abc"),
                    CryptoCurrency("abc1", 123, "abc1", "abc", "abc"),
                    CryptoCurrency("abc2", 123, "abc2", "abc", "abc"),
                    CryptoCurrency("abc3", 123, "abc3", "abc", "abc"),
                    CryptoCurrency("abc4", 123, "abc4", "abc", "abc"),
                    CryptoCurrency("abc1", 123, "abc1", "abc", "abc"),
                    CryptoCurrency("abc2", 123, "abc2", "abc", "abc"),
                    CryptoCurrency("abc3", 123, "abc3", "abc", "abc"),
                    CryptoCurrency("abc4", 123, "abc4", "abc", "abc"),
                    CryptoCurrency("abc1", 123, "abc1", "abc", "abc"),
                    CryptoCurrency("abc2", 123, "abc2", "abc", "abc"),
                    CryptoCurrency("abc3", 123, "abc3", "abc", "abc"),
                    CryptoCurrency("abc4", 123, "abc4", "abc", "abc"),
                    CryptoCurrency("abc1", 123, "abc1", "abc", "abc"),
                    CryptoCurrency("abc2", 123, "abc2", "abc", "abc"),
                    CryptoCurrency("abc3", 123, "abc3", "abc", "abc"),
                    CryptoCurrency("abc4", 123, "abc4", "abc", "abc"),
                    CryptoCurrency("abc1", 123, "abc1", "abc", "abc"),
                    CryptoCurrency("abc2", 123, "abc2", "abc", "abc"),
                    CryptoCurrency("abc3", 123, "abc3", "abc", "abc"),
                    CryptoCurrency("abc4", 123, "abc4", "abc", "abc"),
                    CryptoCurrency("abc1", 123, "abc1", "abc", "abc"),
                    CryptoCurrency("abc2", 123, "abc2", "abc", "abc"),
                    CryptoCurrency("abc3", 123, "abc3", "abc", "abc"),
                    CryptoCurrency("abc4", 123, "abc4", "abc", "abc"),

                    ),){
                cryptoCurrency ->
                Toast.makeText(this, cryptoCurrency.name + " selected.", Toast.LENGTH_SHORT).show()
            }

        binding?.rcvCryptoCurrencyList?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = cryptoCurrencyListAdapter
        }

    }
}