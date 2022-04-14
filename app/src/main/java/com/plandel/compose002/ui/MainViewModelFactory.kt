package com.plandel.compose002.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.plandel.compose002.repository.MainRepository

class MainViewModelFactory constructor(private val repository: MainRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(repository) as T
        }else {
            throw IllegalArgumentException("ViewModel Not Found!!")
        }
    }
}