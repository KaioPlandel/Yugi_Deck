package com.plandel.compose002.ui

import android.provider.ContactsContract
import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plandel.compose002.model.Data
import com.plandel.compose002.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel constructor(private val repository: MainRepository) : ViewModel() {

    var allCards = MutableLiveData<List<String>>()
    var yugiList = mutableListOf<String>()
    var status = MutableLiveData<Boolean>()
    var listSearch = mutableListOf<Data>()

    fun listCards() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = repository.getAllCards()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    status.postValue(true)
                    response.body()?.data?.forEach {
                        listSearch.add(it)
                        it.card_images.forEach { cardImage ->
                            yugiList.add(cardImage.image_url)
                        }
                    }
                    allCards.postValue(yugiList)
                } else {
                    status.postValue(false)
                }
            }
        }
    }

    fun searchCard(query: Editable?) {
        yugiList.clear()

        val str = "kotlin tutorial examples"

        val words = query.toString().split(" ").toMutableList()

        var output = ""

        for(word in words){
            output += word.capitalize() +" "
        }

        output = output.trim()

        listSearch.forEach {
            if(it.name.contains(output)){
                it.card_images.forEach { cardImage ->
                    yugiList.add(cardImage.image_url)
                }
            }
        }
    }
}