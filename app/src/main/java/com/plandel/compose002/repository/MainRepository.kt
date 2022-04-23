package com.plandel.compose002.repository

import com.plandel.compose002.api.RetrofitService
import com.plandel.compose002.data.CardDao
import com.plandel.compose002.model.CardImage


class MainRepository(private val retrofitService: RetrofitService, private val cardDao: CardDao) {

    suspend fun getAllCards() = retrofitService.getAllCards()

    suspend fun getPlantCards() = retrofitService.getPlantCards()

    suspend fun getReptileCards() = retrofitService.getReptileCards()

    suspend fun getWarriorCards() = retrofitService.getWarriorCards()

    suspend fun getDinosaurCards() = retrofitService.getDinosaurCards()

    fun getAllCardSData() = cardDao.readAllCards()

    fun addNewCard(card: CardImage) = cardDao.addCard(card)

    fun deleteCard(card: CardImage) = cardDao.deleteCard(card)
}