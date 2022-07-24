package com.example.mvvmshoppinglist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmshoppinglist.data.repository.ShoppingRepository

@Suppress("UNCHECKED_CAST")
class ShoppingViewModelFactory(private val repository: ShoppingRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T
    }
}