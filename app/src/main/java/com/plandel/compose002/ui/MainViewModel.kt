package com.plandel.compose002.ui


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plandel.compose002.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    private val _plantCards = MutableLiveData<List<String>>()
    val plantCards: LiveData<List<String>> get() = _plantCards

    private val _reptileCards = MutableLiveData<List<String>>()
    val reptileCards: LiveData<List<String>> get() = _reptileCards

    private val _warriorCard = MutableLiveData<List<String>>()
    val warriorCard: LiveData<List<String>> get() = _warriorCard

    private val _dinousaurCards = MutableLiveData<List<String>>()
    val dinossaurCards: LiveData<List<String>> get() = _dinousaurCards

    private var yugi2 = mutableListOf<String>()
    private var reptile = mutableListOf<String>()
    private var warrior = mutableListOf<String>()
    private var dinosaur = mutableListOf<String>()
    private var status = MutableLiveData<Boolean>()


    fun listPlantCards() {

        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                try {
                    val response = repository.getPlantCards()
                    if (response.isSuccessful) {
                        status.postValue(true)
                        response.body()?.data?.forEach {
                            it.card_images.forEach { cardImage ->
                                yugi2.add(cardImage.image_url)
                            }
                        }
                        _plantCards.postValue(yugi2)
                    } else {
                        status.postValue(false)
                    }
                } catch (e: Exception) {
                    Log.d("TAG", "listPlantCards: " + e.message)
                }

            }
        }
    }

    fun listReptileCards() {
        viewModelScope.launch(Dispatchers.IO) {

            withContext(Dispatchers.Main) {
                try {
                    val response = repository.getRepitleCards()
                    if (response.isSuccessful) {
                        status.postValue(true)
                        response.body()?.data?.forEach {
                            it.card_images.forEach { cardImage ->
                                reptile.add(cardImage.image_url)
                            }
                        }
                        _reptileCards.postValue(reptile)
                    } else {
                        status.postValue(false)
                    }
                } catch (e: Exception) {
                    Log.d("TAG", "listPlantCards: " + e.message)
                }

            }
        }
    }

    fun listWarriorCards() {
        viewModelScope.launch(Dispatchers.IO) {

            withContext(Dispatchers.Main) {
                try {
                    val response = repository.getWarriorCards()

                    if (response.isSuccessful) {
                        status.postValue(true)
                        response.body()?.data?.forEach {
                            it.card_images.forEach { cardImage ->
                                warrior.add(cardImage.image_url)
                            }
                        }
                        _warriorCard.postValue(warrior)
                    } else {
                        status.postValue(false)
                    }
                } catch (e: Exception) {
                    Log.d("TAG", "listPlantCards: " + e.message)
                }
            }
        }
    }

    fun listDinosaurCards() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                try {
                    val response = repository.getDinosaurCards()
                    if (response.isSuccessful) {
                        status.postValue(true)
                        response.body()?.data?.forEach {
                            it.card_images.forEach { cardImage ->
                                dinosaur.add(cardImage.image_url)
                            }
                        }
                        _dinousaurCards.postValue(dinosaur)
                    } else {
                        status.postValue(false)
                    }
                } catch (e: Exception) {
                    Log.d("TAG", "listPlantCards: " + e.message)
                }

            }
        }
    }
}