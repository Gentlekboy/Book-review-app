package com.gentlekboy.postappusingfragments.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gentlekboy.postappusingfragments.model.comments.CommentListItem
import com.gentlekboy.postappusingfragments.model.posts.PostListItem
import com.gentlekboy.postappusingfragments.repository.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(private val commentRepository: CommentRepository): ViewModel() {
    //Get all comments from database and observe in the fragment
    fun getAllComments(): LiveData<List<CommentListItem>>{
        return commentRepository.getAllComments()
    }

    //Trigger get request for comments
    fun makeGetRequest(postId: String){
        commentRepository.makeGetRequest(postId)
    }

    //Add new comment to database
    fun addNewComment(commentListItem: CommentListItem){
        commentRepository.insertComment(commentListItem)
    }

    //Delete comments from database
    fun deleteComments(){
        commentRepository.deleteAllComments()
    }
}