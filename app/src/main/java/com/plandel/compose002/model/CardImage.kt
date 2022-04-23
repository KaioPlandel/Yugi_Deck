package com.plandel.compose002.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cards")
data class CardImage(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image_url: String,
    val image_url_small: String
): Serializable