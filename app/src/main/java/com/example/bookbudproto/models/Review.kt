package com.example.bookbudproto.models

data class Review(
    val bookImageResId: Int,
    val bookTitle: String,
    val content: String,
    val rating: Int
)
