package com.example.lorenzodwishlist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDAO
{
    @Query("SELECT * FROM item_table")
    fun getAll(): Flow <List<ItemEntity>>

    @Insert
    fun insertAll(vararg items: ItemEntity)

    @Query("DELETE FROM item_table")
    fun deleteAll()
}