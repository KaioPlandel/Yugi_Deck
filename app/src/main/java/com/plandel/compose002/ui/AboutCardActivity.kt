package com.plandel.compose002.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.plandel.compose002.R
import com.plandel.compose002.databinding.ActivityAboutCardBinding

class AboutCardActivity : AppCompatActivity() {
    lateinit var binding: ActivityAboutCardBinding

    companion object {
        var count = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showCard()
        setubListeners()

    }

    private fun setubListeners() {
        binding.buttonSave.setOnClickListener {
            Log.d("TAG", "onCreate: " + count)
            if (saveCard(count)) {
                count++
                binding.buttonSave.setImageResource(R.drawable.ic_baseline_done_24)
            } else {
                count++
                binding.buttonSave.setImageResource(R.drawable.ic_baseline_add_24)
            }
        }
        count = 2
    }

    private fun showCard() {
        val intent = intent.getStringExtra("card")
        Glide.with(this)
            .load(intent)
            .into(binding.saveCard)
    }

    private fun saveCard(number: Int): Boolean {
        return number % 2 == 0
    }

}