package com.vinodpatildev.cryptoapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vinodpatildev.cryptoapp.Util.Resource
import com.vinodpatildev.cryptoapp.adapters.CryptoCurrencyAdapter
import com.vinodpatildev.cryptoapp.databinding.ActivityMainBinding
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

        setSupportActionBar(binding?.toolbar)
        binding?.toolbarTitle?.text = "Crypto App"

        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)


        // TODO : set observers on data in viewmodel and change the layout
        homeViewModel.cryptoCurrencyList.observe(this, Observer { response ->
            when(response){
                is Resource.Success -> {
                    binding?.shimmerViewContainer?.stopShimmer()
                    if(binding?.swipeRefreshLayout?.isRefreshing == true){
                        binding?.swipeRefreshLayout?.isRefreshing = false
                    }
                    binding?.rcvCryptoCurrencyList?.visibility = View.VISIBLE
                    // TODO : Load data into UI
                    cryptoCurrencyListAdapter =
                        response.data?.let {
                            CryptoCurrencyAdapter(this@MainActivity,it){ cryptoCurrency ->
                                Toast.makeText(this, cryptoCurrency.name + " selected.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    binding?.rcvCryptoCurrencyList?.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = cryptoCurrencyListAdapter
                    }
                }
                is Resource.Loading -> {
                    binding?.shimmerViewContainer?.startShimmer()
                }
                is Resource.Error -> {
                    binding?.shimmerViewContainer?.stopShimmer()
                    if(binding?.swipeRefreshLayout?.isRefreshing == true){
                        binding?.swipeRefreshLayout?.isRefreshing = false
                    }
                    Toast.makeText(this,response.message,Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding?.swipeRefreshLayout?.setOnRefreshListener {
            binding?.rcvCryptoCurrencyList?.visibility = View.INVISIBLE
            // TODO : request reload data from viewModel
            Log.d("logtesttest", "main activity refresh call" )
            homeViewModel.reloadCryptoCurrencyList()
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

        homeViewModel.reloadCryptoCurrencyList()
    }
}