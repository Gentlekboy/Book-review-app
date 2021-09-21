package com.gentlekboy.postappusingfragments.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gentlekboy.postappusingfragments.model.posts.PostListItem
import com.gentlekboy.postappusingfragments.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postRepository: PostRepository): ViewModel() {
    fun getAllPosts(): LiveData<List<PostListItem>>{
        return postRepository.getAllPosts()
    }

    fun makeGetRequest(){
        postRepository.makeGetRequest()
    }
}