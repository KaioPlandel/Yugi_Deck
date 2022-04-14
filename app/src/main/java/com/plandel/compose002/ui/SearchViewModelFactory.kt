package com.plandel.compose002.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.plandel.compose002.repository.MainRepository

class SearchViewModelFactory constructor(private val repository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchViewModel::class.java)){
            return SearchViewModel(repository) as T
        }else {
            throw IllegalArgumentException("ViewModel Not Found!")
        }
    }
}