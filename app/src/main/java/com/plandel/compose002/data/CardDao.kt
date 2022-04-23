package com.plandel.compose002.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.plandel.compose002.model.CardImage

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCard(card: CardImage)

    @Delete
    fun deleteCard(card: CardImage)

    @Query("SELECT * FROM cards")
    fun readAllCards(): LiveData<List<CardImage>>
}