package com.example.mvvmshoppinglist.data.repository

import com.example.mvvmshoppinglist.data.db.ShoppingDatabase
import com.example.mvvmshoppinglist.data.db.entity.ShoppingItem

class ShoppingRepository(private val db: ShoppingDatabase) {

    suspend fun upsert(shoppingItem: ShoppingItem) = db.getShoppingDao().upsert(shoppingItem)

    suspend fun delete(shoppingItem: ShoppingItem) = db.getShoppingDao().delete(shoppingItem)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}