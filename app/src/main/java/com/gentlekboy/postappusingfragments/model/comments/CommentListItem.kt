package com.gentlekboy.postappusingfragments.model.comments

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "comments")
@Parcelize
data class CommentListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val body: String,
    val email: String,
    val name: String,
    val postId: Int
) : Parcelable