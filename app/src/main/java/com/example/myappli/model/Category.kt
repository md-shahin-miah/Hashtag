package com.example.myappli.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey var id: Int,
    val name: String,
    val parent: Int,
    var icon: String,
    val count: Int
)

