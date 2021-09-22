package com.gentlekboy.postappusingfragments.model.posts

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "posts")
@Parcelize
data class PostListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int
) : Parcelable