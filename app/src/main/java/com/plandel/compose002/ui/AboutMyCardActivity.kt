package com.plandel.compose002.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.plandel.compose002.R
import com.plandel.compose002.databinding.ActivityAboutMyCardBinding
import com.plandel.compose002.databinding.ActivityMyDeckBinding
import com.plandel.compose002.model.CardImage
import com.plandel.compose002.ui.aboutCard.AboutCardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutMyCardActivity : AppCompatActivity() {
    lateinit var binding: ActivityAboutMyCardBinding
    val viewModel: AboutCardViewModel by viewModel<AboutCardViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutMyCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showCard()
        setupActionBar()

        binding.buttonDelete.setOnClickListener {
            val card = intent.getSerializableExtra("card") as CardImage
            if (viewModel.deleteCard(card)){
                finish()
            }
        }
    }

    private fun setupActionBar() {
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "About My Card"
    }

    private fun showCard() {
        val card = intent.getSerializableExtra("card") as CardImage
        Glide.with(this)
            .load(card.image_url)
            .into(binding.deleteCard)
    }
}