package com.example.bookstory.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "category")
    var category: String,
    @ColumnInfo(name = "namebook")
    var namebook: String,
    @ColumnInfo(name = "author")
    var author: String,
    @ColumnInfo(name = "textbook")
    var textbook: String,
    @ColumnInfo(name = "star")
    var star: Double,
    @ColumnInfo(name = "avatar_url")
    var avatarUrl: Int,
    @ColumnInfo(name = "favorite")
    var favorite: Int,

    )