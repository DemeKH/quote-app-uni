package com.example.quoteoftheday

data class Quote(
    val id: Int = 0,     // for SQLite auto-increment
    val text: String,
    val author: String
)
