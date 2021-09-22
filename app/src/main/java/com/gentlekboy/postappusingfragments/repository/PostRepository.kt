package com.gentlekboy.postappusingfragments.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.gentlekboy.postappusingfragments.database.AppDao
import com.gentlekboy.postappusingfragments.model.posts.PostList
import com.gentlekboy.postappusingfragments.model.posts.PostListItem
import com.gentlekboy.postappusingfragments.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val appDao: AppDao
) {
    fun getAllPosts(): LiveData<List<PostListItem>>{
        return appDao.getAllPosts()
    }

    fun insertPost(postListItem: PostListItem){
        appDao.insertPost(postListItem)
    }

    fun makeGetRequest(){
        val networkCall: Call<PostList> = apiInterface.getPostsFromApi()

        networkCall.enqueue(object : Callback<PostList?> {
            override fun onResponse(call: Call<PostList?>, response: Response<PostList?>) {
                if (response.isSuccessful){
//                    appDao.deletePosts()

                    response.body()?.forEach {
                        insertPost(it)
                    }
                }
            }

            override fun onFailure(call: Call<PostList?>, t: Throwable) {
                Log.d("GKB", "onFailure: Something Went Wrong Fetching Posts -> ${t.message}")
            }
        })
    }

    fun makePostRequest(postListItem: PostListItem){
        val networkCall: Call<PostListItem> = apiInterface.makeAPost(postListItem)

        networkCall.enqueue(object : Callback<PostListItem?> {
            override fun onResponse(call: Call<PostListItem?>, response: Response<PostListItem?>) {
                if (response.isSuccessful){
                    insertPost(postListItem)
                }else{
                    Log.d("GKB", "not successful: Something Went Wrong -> ${response.code()}")
                }
            }

            override fun onFailure(call: Call<PostListItem?>, t: Throwable) {
                Log.d("GKB", "onFailure: Something Went Wrong Posting the post -> ${t.message}")
            }
        })
    }
}