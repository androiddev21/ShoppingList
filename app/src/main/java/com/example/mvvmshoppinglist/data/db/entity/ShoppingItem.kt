package com.example.mvvmshoppinglist.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShoppingItems")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "itemName")
    val name: String,
    @ColumnInfo(name = "itemAmount")
    var amount: Int
)