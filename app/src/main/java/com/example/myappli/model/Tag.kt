package com.example.myappli.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    @PrimaryKey val id: Int,
    val tag: String,
    val category: String,
    val categoryId: Int
)
