package com.plandel.compose002.ui.aboutCard

import androidx.lifecycle.ViewModel
import com.plandel.compose002.model.CardImage
import com.plandel.compose002.repository.MainRepository

class AboutCardViewModel(private val repository: MainRepository) : ViewModel() {
    val dataCard = repository.getAllCardSData()

    fun addNewCard(card: CardImage): Boolean {
        repository.addNewCard(card)
        return true
    }

    fun deleteCard(card: CardImage): Boolean {
        repository.deleteCard(card)
        return true
    }
}