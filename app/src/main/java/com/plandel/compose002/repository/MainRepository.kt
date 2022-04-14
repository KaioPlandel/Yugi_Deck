package com.plandel.compose002.repository

import com.plandel.compose002.api.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllCards() = retrofitService.getAllCards()

    suspend fun getPlantCards() = retrofitService.getPlantCards()

    suspend fun getRepitleCards() = retrofitService.getReptileCards()

    suspend fun getWarriorCards() = retrofitService.getWarriorCards()

    suspend fun getDinosaurCards() = retrofitService.getDinosaurCards()
}