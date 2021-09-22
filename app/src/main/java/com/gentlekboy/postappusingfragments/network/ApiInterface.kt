package com.gentlekboy.postappusingfragments.network

import com.gentlekboy.postappusingfragments.model.comments.CommentList
import com.gentlekboy.postappusingfragments.model.posts.PostList
import com.gentlekboy.postappusingfragments.model.posts.PostListItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    //Make get request for posts
    @GET("posts")
    fun getPostsFromApi(): Call<PostList>

    //Make get request fro comments associated with a post
    @GET("posts/{postId}/comments")
    fun getCommentsFromApi(
        @Path("postId")
        postId: String
    ): Call<CommentList>

    //Make post request
    @POST("posts")
    fun makeAPost(
        @Body
        postListItem: PostListItem
    ): Call<PostListItem>
}