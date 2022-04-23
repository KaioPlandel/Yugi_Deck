package com.plandel.compose002.model

data class Data(
    val archetype: String,
    val atk: Int,
    val attribute: String,
    val card_images: List<CardImage>,
    val def: Int,
    val desc: String,
    val id: Int,
    val level: Int,
    val linkmarkers: List<String>,
    val linkval: Int,
    val name: String,
    val race: String,
    val scale: Int,
    val type: String
)