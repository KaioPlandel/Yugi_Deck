package com.plandel.compose002.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plandel.compose002.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {
    var allCards = MutableLiveData<List<String>>()
    var plantCards = MutableLiveData<List<String>>()
    var reptileCards =MutableLiveData<List<String>>()
    var warriorCard = MutableLiveData<List<String>>()
    var dinousaurCards = MutableLiveData<List<String>>()

    var yugi = mutableListOf<String>()
    var yugi2 = mutableListOf<String>()
    var reptile = mutableListOf<String>()
    var warrior = mutableListOf<String>()
    var dinosaur = mutableListOf<String>()
    var status = MutableLiveData<Boolean>()

    fun listCards(){
        GlobalScope.launch(Dispatchers.IO) {
            val response = repository.getAllCards()

            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    status.postValue(true)
                    response.body()?.data?.forEach {
                        it.card_images.forEach { cardImage ->
                            yugi.add(cardImage.image_url)
                        }
                    }
                    allCards.postValue(yugi)
                }else {
                    status.postValue(false)
                }
            }
        }
    }

    fun listPlantCards() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = repository.getPlantCards()

            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    status.postValue(true)
                    response.body()?.data?.forEach {
                        it.card_images.forEach { cardImage ->
                            yugi2.add(cardImage.image_url)
                        }
                    }
                    plantCards.postValue(yugi2)
                }else {
                    status.postValue(false)
                }
            }
        }
    }

    fun listReptileCards() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = repository.getRepitleCards()

            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    status.postValue(true)
                    response.body()?.data?.forEach {
                        it.card_images.forEach { cardImage ->
                            reptile.add(cardImage.image_url)
                        }
                    }
                    reptileCards.postValue(reptile)
                }else {
                    status.postValue(false)
                }
            }
        }
    }

    fun listWarriorCards() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = repository.getWarriorCards()

            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    status.postValue(true)
                    response.body()?.data?.forEach {
                        it.card_images.forEach { cardImage ->
                            warrior.add(cardImage.image_url)
                        }
                    }
                    warriorCard.postValue(warrior)
                }else {
                    status.postValue(false)
                }
            }
        }
    }

    fun listDinosaurCards() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = repository.getDinosaurCards()

            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    status.postValue(true)
                    response.body()?.data?.forEach {
                        it.card_images.forEach { cardImage ->
                            dinosaur.add(cardImage.image_url)
                        }
                    }
                    dinousaurCards.postValue(dinosaur)
                }else {
                    status.postValue(false)
                }
            }
        }
    }
}