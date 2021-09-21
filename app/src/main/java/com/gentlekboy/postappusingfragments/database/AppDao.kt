package com.gentlekboy.postappusingfragments.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gentlekboy.postappusingfragments.model.comments.CommentListItem
import com.gentlekboy.postappusingfragments.model.posts.PostListItem

@Dao
interface AppDao {
    @Query("SELECT * FROM posts")
    fun getAllPosts(): LiveData<List<PostListItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(postListItem: PostListItem)

    @Query("DELETE FROM posts")
    fun deletePosts()

    @Query("SELECT * FROM comments")
    fun getAllComments(): LiveData<List<CommentListItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComment(commentListItem: CommentListItem)

    @Query("DELETE FROM comments")
    fun deleteComments()
}