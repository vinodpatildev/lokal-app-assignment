package com.vinodpatildev.cryptoapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vinodpatildev.cryptoapp.R
import com.vinodpatildev.cryptoapp.adapters.CryptoCurrencyAdapter
import com.vinodpatildev.cryptoapp.databinding.ActivityMainBinding
import com.vinodpatildev.cryptoapp.models.CryptoCurrency
import com.vinodpatildev.cryptoapp.viewmodels.HomeViewModel
import com.vinodpatildev.cryptoapp.viewmodels.HomeViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var cryptoCurrencyListAdapter: CryptoCurrencyAdapter? = null
    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)

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

        // TODO : set observers on data in viewmodel and change the layout

        binding?.swipeRefreshLayout?.setOnRefreshListener {
            binding?.rcvCryptoCurrencyList?.visibility = View.INVISIBLE
            // TODO : reload data from viewModel
        }
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.DOWN) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                binding?.swipeRefreshLayout?.isRefreshing = true
                binding?.swipeRefreshLayout?.setOnRefreshListener(null)
            }
        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding?.rcvCryptoCurrencyList)

    }
}