package com.example.mvvmshoppinglist.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvmshoppinglist.data.db.entity.ShoppingItem
import com.example.mvvmshoppinglist.data.repository.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository) : ViewModel() {

    fun plusItem(currentItem: ShoppingItem) {
        val newItem = currentItem.apply {
            this.amount++
        }
        upsert(newItem)
    }

    fun minusItem(currentItem: ShoppingItem) {
        if (currentItem.amount > 0) {
            val newItem = currentItem.apply {
                this.amount--
            }
            upsert(newItem)
        }
    }

    fun upsert(shoppingItem: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(shoppingItem)
    }

    fun delete(shoppingItem: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(shoppingItem)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()
}