package com.plandel.compose002.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.plandel.compose002.R
import com.plandel.compose002.adapter.Adapter
import com.plandel.compose002.adapter.AdapterPlants
import com.plandel.compose002.adapter.AdapterReptile
import com.plandel.compose002.adapter.AdapterWarrior
import com.plandel.compose002.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

//injenção de dependencias
//koin

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private var adapter = Adapter()
    private var adapter2 = AdapterPlants()
    private var adapterReptile = AdapterReptile()
    private var adapterWarrior = AdapterWarrior()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupObservers()
    }
    override fun onStart() {
        super.onStart()
        viewModel.listDinosaurCards()
        viewModel.listPlantCards()
        viewModel.listReptileCards()
        viewModel.listWarriorCards()
    }

    private fun setupObservers() {
        viewModel.dinossaurCards.observe(this) {
            adapter.setData(it)
        }

        viewModel.plantCards.observe(this) {
            adapter2.setData(it)
        }

        viewModel.reptileCards.observe(this) {
            adapterReptile.setData(it)
        }

        viewModel.warriorCard.observe(this) {
            adapterWarrior.setData(it)
        }
    }

    private fun setupRecyclerView() {
        //recycler allCards
        binding.recylerFilmes.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.recylerFilmes.adapter = adapter

        binding.recylerPlant.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.recylerPlant.adapter = adapter2

        binding.recylerReptile.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.recylerReptile.adapter = adapterReptile

        binding.recylerWarrior.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.recylerWarrior.adapter = adapterWarrior

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search) {
            startActivity(Intent(this@MainActivity, SearchCardActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}