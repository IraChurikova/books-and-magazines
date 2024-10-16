package com.example.bookstory.adapter

import java.io.Serializable

data class ItemBooks(val id: Int? = null, val category: String, val namebook: String, val author: String, var textbook: String, var star: Double, val avatarUrl: Int, val favorite: Int) :
    Serializable