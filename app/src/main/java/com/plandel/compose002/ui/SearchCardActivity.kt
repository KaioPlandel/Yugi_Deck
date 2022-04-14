package com.plandel.compose002.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.plandel.compose002.R
import com.plandel.compose002.adapter.AdapterGrid
import com.plandel.compose002.api.RetrofitService
import com.plandel.compose002.databinding.ActivitySearchCardBinding
import com.plandel.compose002.repository.MainRepository

class SearchCardActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchCardBinding
    lateinit var viewModel: SearchViewModel
    val adapterGrid = AdapterGrid()
    val retrofitService = RetrofitService.getRetrofitIntance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this,SearchViewModelFactory(MainRepository(retrofitService))).get(SearchViewModel::class.java)


        viewModel.listCards()

        binding.editSearch.addTextChangedListener {
            Log.d("TAG", "onCreate: " + it.toString())
            viewModel.searchCard(it)
            adapterGrid.setData(viewModel.yugiList)
        }

        viewModel.allCards.observe(this, Observer {
            adapterGrid.setData(it)
        })

        binding.recyclerSearch.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerSearch.adapter = adapterGrid
        binding.recyclerSearch.setHasFixedSize(true)
    }
}