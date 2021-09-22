package com.gentlekboy.postappusingfragments.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.gentlekboy.postappusingfragments.database.AppDao
import com.gentlekboy.postappusingfragments.model.comments.CommentList
import com.gentlekboy.postappusingfragments.model.comments.CommentListItem
import com.gentlekboy.postappusingfragments.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//Inject instances of DAO and retrofit interface
class CommentRepository @Inject constructor(private val apiInterface: ApiInterface, private val appDao: AppDao)  {
    //Get all comments from database
    fun getAllComments(): LiveData<List<CommentListItem>> {
        return appDao.getAllComments()
    }

    //Add comment to database
    fun insertComment(commentListItem: CommentListItem){
        appDao.insertComment(commentListItem)
    }

    //Delete all comments fro database
    fun deleteAllComments(){
        appDao.deleteComments()
    }

    //Make get request for comments
    fun makeGetRequest(postId: String){
        val networkCall: Call<CommentList> = apiInterface.getCommentsFromApi(postId)

        networkCall.enqueue(object : Callback<CommentList?> {
            override fun onResponse(call: Call<CommentList?>, response: Response<CommentList?>) {
                if (response.isSuccessful){
//                    appDao.deleteComments()

                    response.body()?.forEach {
                        insertComment(it)
                    }
                }
            }

            override fun onFailure(call: Call<CommentList?>, t: Throwable) {
                Log.d("GKB", "onFailure: Something Went Wrong Fetching Comments -> ${t.message}")
            }
        })
    }
}