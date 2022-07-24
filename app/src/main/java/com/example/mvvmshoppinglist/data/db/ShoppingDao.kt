package com.example.mvvmshoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.mvvmshoppinglist.data.db.entity.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("select * FROM shoppingitems")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}