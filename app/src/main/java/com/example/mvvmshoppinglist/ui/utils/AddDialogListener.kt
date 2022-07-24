package com.example.mvvmshoppinglist.ui.utils

import com.example.mvvmshoppinglist.data.db.entity.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(shoppingItem: ShoppingItem)
}