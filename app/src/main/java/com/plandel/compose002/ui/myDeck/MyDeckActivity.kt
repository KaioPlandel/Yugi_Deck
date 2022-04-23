package com.plandel.compose002.ui.myDeck

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.plandel.compose002.adapter.AdapterMyCard
import com.plandel.compose002.databinding.ActivityMyDeckBinding
import com.plandel.compose002.ui.aboutCard.AboutCardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyDeckActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyDeckBinding
    private val viewModel: AboutCardViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyDeckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        setupRecycler()
    }

    private fun setupRecycler() {
        val adapter = AdapterMyCard()
        binding.recyclerMyDeck.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerMyDeck.adapter = adapter
        binding.recyclerMyDeck.setHasFixedSize(true)

        viewModel.dataCard.observe(this) {
            adapter.setData(it)
        }
    }

    private fun setupActionBar() {
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "My Deck"
    }
}