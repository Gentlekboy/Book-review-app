package com.gentlekboy.postappusingfragments.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.gentlekboy.postappusingfragments.database.PostDao
import com.gentlekboy.postappusingfragments.model.PostList
import com.gentlekboy.postappusingfragments.model.PostListItem
import com.gentlekboy.postappusingfragments.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val postDao: PostDao
) {
    fun getAllPosts(): LiveData<List<PostListItem>>{
        return postDao.getAllPosts()
    }

    fun insertPost(postListItem: PostListItem){
        postDao.insertPost(postListItem)
    }

    fun makeGetRequest(){
        val networkCall: Call<PostList> = apiInterface.getPostsFromApi()

        networkCall.enqueue(object : Callback<PostList?> {
            override fun onResponse(call: Call<PostList?>, response: Response<PostList?>) {
                if (response.isSuccessful){
                    postDao.deletePosts()

                    response.body()?.forEach {
                        insertPost(it)
                    }
                }
            }

            override fun onFailure(call: Call<PostList?>, t: Throwable) {
                Log.d("GKB", "onFailure: Something Went Wrong -> ${t.message}")
            }
        })
    }
}