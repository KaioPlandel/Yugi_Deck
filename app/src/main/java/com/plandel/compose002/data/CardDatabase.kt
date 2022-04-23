package com.plandel.compose002.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.plandel.compose002.model.CardImage

@Database(entities = [CardImage::class], version = 1, exportSchema = false)
abstract class CardDatabase : RoomDatabase() {
    abstract val cardDao: CardDao
}