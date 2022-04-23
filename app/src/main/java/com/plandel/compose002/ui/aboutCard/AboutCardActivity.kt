package com.plandel.compose002.ui.aboutCard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.plandel.compose002.databinding.ActivityAboutCardBinding
import com.plandel.compose002.model.CardImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutCardBinding
    private val viewModel: AboutCardViewModel by viewModel<AboutCardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showCard()
        setupListeners()
        setupActionBar()
    }

    private fun setupActionBar() {
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "About Card"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupListeners() {
        val card = intent.getSerializableExtra("card") as CardImage
        binding.buttonSave.setOnClickListener {
            if (viewModel.addNewCard(card)){
                Toast.makeText(this,"Card Saved",Toast.LENGTH_SHORT).show()
                finish()
            }

        }
    }

    private fun showCard() {
        val card = intent.getSerializableExtra("card") as CardImage
        Glide.with(this)
            .load(card.image_url)
            .into(binding.saveCard)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}