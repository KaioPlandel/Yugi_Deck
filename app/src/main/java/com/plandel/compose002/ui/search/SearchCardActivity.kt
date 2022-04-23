package com.plandel.compose002.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import com.plandel.compose002.adapter.AdapterGrid
import com.plandel.compose002.databinding.ActivitySearchCardBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCardActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchCardBinding
    private val viewModel: SearchViewModel by viewModel()
    private val adapterGrid = AdapterGrid()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.listCards()

        binding.editSearch.addTextChangedListener {
            viewModel.searchCard(it)
            adapterGrid.setData(viewModel.yugiList)
        }

        viewModel.allCards.observe(this) {
            adapterGrid.setData(it)
        }

        binding.recyclerSearch.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerSearch.adapter = adapterGrid
        binding.recyclerSearch.setHasFixedSize(true)
    }
}