package com.plandel.compose002.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.plandel.compose002.R
import com.plandel.compose002.adapter.Adapter
import com.plandel.compose002.adapter.AdapterPlants
import com.plandel.compose002.adapter.AdapterReptile
import com.plandel.compose002.adapter.AdapterWarrior
import com.plandel.compose002.api.RetrofitService
import com.plandel.compose002.databinding.ActivityMainBinding
import com.plandel.compose002.repository.MainRepository

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val retrofit = RetrofitService.getRetrofitIntance()
    lateinit var viewModel: MainViewModel
    var adapter = Adapter()
    var adapter2 = AdapterPlants()
    var adapterReptile = AdapterReptile()
    var adapterWarrior = AdapterWarrior()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofit))).get(
            MainViewModel::class.java
        )

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
        viewModel.dinousaurCards.observe(this, Observer {
            adapter.setData(it)
        })

        viewModel.plantCards.observe(this, Observer {
            adapter2.setData(it)
        })

        viewModel.reptileCards.observe(this, Observer {
            adapterReptile.setData(it)
        })

        viewModel.warriorCard.observe(this, Observer {
            adapterWarrior.setData(it)
        })
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
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