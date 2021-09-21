package com.gentlekboy.postappusingfragments.model.posts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int
)