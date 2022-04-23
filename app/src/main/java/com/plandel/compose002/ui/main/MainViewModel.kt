package com.plandel.compose002.ui.main


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plandel.compose002.helper.LoadingState
import com.plandel.compose002.model.Card
import com.plandel.compose002.model.CardImage
import com.plandel.compose002.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    private val _plantCards = MutableLiveData<List<CardImage>>()
    val plantCards: LiveData<List<CardImage>> get() = _plantCards

    private val _reptileCards = MutableLiveData<List<CardImage>>()
    val reptileCards: LiveData<List<CardImage>> get() = _reptileCards

    private val _warriorCard = MutableLiveData<List<CardImage>>()
    val warriorCard: LiveData<List<CardImage>> get() = _warriorCard

    private val _dinousaurCards = MutableLiveData<List<CardImage>>()
    val dinossaurCards: LiveData<List<CardImage>> get() = _dinousaurCards

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> get() = _loadingState

    private var yugi2 = mutableListOf<CardImage>()
    private var reptile = mutableListOf<CardImage>()
    private var warrior = mutableListOf<CardImage>()
    private var dinosaur = mutableListOf<CardImage>()

    init {
        listDinosaurCards()
        listPlantCards()
        listReptileCards()
        listWarriorCards()
    }

    private fun listPlantCards() {
        viewModelScope.launch(Dispatchers.IO) {
            _loadingState.postValue(LoadingState.LOADING)
            withContext(Dispatchers.Main) {
                try {
                    val response = repository.getPlantCards()
                    if (response.isSuccessful) {
                        _loadingState.postValue(LoadingState.LOADED)
                        response.body()?.data?.forEach {
                            it.card_images.forEach { cardImage ->
                                yugi2.add(cardImage)
                            }
                        }
                        _plantCards.postValue(yugi2)
                    } else {
                        _loadingState.postValue(LoadingState.error(response.message()))
                    }
                } catch (e: Exception) {
                    _loadingState.postValue(LoadingState.error(e.message))
                }
            }
        }
    }

    private fun listReptileCards() {
        viewModelScope.launch(Dispatchers.IO) {

            withContext(Dispatchers.Main) {
                try {
                    _loadingState.postValue(LoadingState.LOADING)
                    val response = repository.getReptileCards()
                    if (response.isSuccessful) {
                        response.body()?.data?.forEach {
                            it.card_images.forEach { cardImage ->
                                reptile.add(cardImage)
                            }
                        }
                        _reptileCards.postValue(reptile)
                    } else {
                        _loadingState.postValue(LoadingState.error(response.message()))
                    }
                } catch (e: Exception) {
                    _loadingState.postValue(LoadingState.error(e.message))
                }
            }
        }
    }

    private fun listWarriorCards() {
        viewModelScope.launch(Dispatchers.IO) {

            withContext(Dispatchers.Main) {
                _loadingState.postValue(LoadingState.LOADING)
                try {
                    val response = repository.getWarriorCards()
                    if (response.isSuccessful) {
                        response.body()?.data?.forEach {
                            it.card_images.forEach { cardImage ->
                                warrior.add(cardImage)
                            }
                        }
                        _warriorCard.postValue(warrior)
                    } else {
                        _loadingState.postValue(LoadingState.error(response.message()))
                    }
                } catch (e: Exception) {
                    _loadingState.postValue(LoadingState.error(e.message))
                }
            }
        }
    }

    private fun listDinosaurCards() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _loadingState.postValue(LoadingState.LOADING)
                try {
                    val response = repository.getDinosaurCards()
                    if (response.isSuccessful) {
                        response.body()?.data?.forEach {
                            it.card_images.forEach { cardImage ->
                                dinosaur.add(cardImage)
                            }
                        }
                        _dinousaurCards.postValue(dinosaur)
                    } else {
                        _loadingState.postValue(LoadingState.error(response.message()))
                    }
                } catch (e: Exception) {
                    _loadingState.postValue(LoadingState.error(e.message))
                }
            }
        }
    }
}