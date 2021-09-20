package com.gentlekboy.postappusingfragments.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostListItem(
    @PrimaryKey(autoGenerate = true)
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)