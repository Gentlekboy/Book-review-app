package com.gentlekboy.postappusingfragments.model.comments

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val body: String,
    val email: String,
    val name: String,
    val postId: Int
)