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
    //Gets all posts from database
    @Query("SELECT * FROM posts")
    fun getAllPosts(): LiveData<List<PostListItem>>

    //Adds post to database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(postListItem: PostListItem)

    //Delete all posts from database
    @Query("DELETE FROM posts")
    fun deletePosts()

    //Search database using post title
    @Query("SELECT * FROM posts WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<PostListItem>>

    //Gets all comments from database
    @Query("SELECT * FROM comments")
    fun getAllComments(): LiveData<List<CommentListItem>>

    //Adds comment to database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComment(commentListItem: CommentListItem)

    //Delete all comments from database
    @Query("DELETE FROM comments")
    fun deleteComments()
}