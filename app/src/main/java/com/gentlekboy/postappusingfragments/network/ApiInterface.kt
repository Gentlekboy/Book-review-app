package com.gentlekboy.postappusingfragments.network

import com.gentlekboy.postappusingfragments.model.PostList
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    //This function specifies the request type and endpoint
    @GET("posts")
    fun getPostsFromApi(): Call<PostList>
}