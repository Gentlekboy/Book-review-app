package com.gentlekboy.postappusingfragments.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gentlekboy.postappusingfragments.model.PostListItem

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    fun getAllPosts(): LiveData<List<PostListItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(postListItem: PostListItem)

    @Query("DELETE FROM posts")
    fun deletePosts()
}