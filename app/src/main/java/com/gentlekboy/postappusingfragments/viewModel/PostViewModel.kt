package com.gentlekboy.postappusingfragments.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gentlekboy.postappusingfragments.model.posts.PostListItem
import com.gentlekboy.postappusingfragments.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postRepository: PostRepository): ViewModel() {
    //Get all posts from database and observe in the fragment
    fun getAllPosts(): LiveData<List<PostListItem>>{
        return postRepository.getAllPosts()
    }

    //Trigger get request for posts
    fun makeGetRequest(){
        postRepository.makeGetRequest()
    }

    //Trigger post request
    fun makePostRequest(postListItem: PostListItem){
        postRepository.makePostRequest(postListItem)
    }

    //Add new post to database
    fun addNewPost(postListItem: PostListItem){
        postRepository.insertPost(postListItem)
    }

    //Delete posts from database
    fun deleteAllPosts(){
        postRepository.deleteAllPosts()
    }

    //Searches the database for post title and is observed in the post fragment
    fun searchDatabase(searchQuery: String): LiveData<List<PostListItem>> {
        return postRepository.searchDatabase(searchQuery)
    }
}