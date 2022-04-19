package com.plandel.compose002.api

import com.plandel.compose002.model.Card
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("api/v7/cardinfo.php")
    suspend fun getAllCards(): Response<Card>

    @GET("api/v7/cardinfo.php?race=dinosaur")
    suspend fun getDinosaurCards(): Response<Card>

    @GET("api/v7/cardinfo.php?race=plant")
    suspend fun getPlantCards(): Response<Card>

    @GET("api/v7/cardinfo.php?race=reptile")
    suspend fun getReptileCards(): Response<Card>

    @GET("api/v7/cardinfo.php?race=warrior")
    suspend fun getWarriorCards(): Response<Card>

}