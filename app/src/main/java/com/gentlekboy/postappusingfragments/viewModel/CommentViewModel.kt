package com.gentlekboy.postappusingfragments.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gentlekboy.postappusingfragments.model.comments.CommentListItem
import com.gentlekboy.postappusingfragments.repository.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(private val commentRepository: CommentRepository): ViewModel() {
    fun getAllComments(): LiveData<List<CommentListItem>>{
        return commentRepository.getAllComments()
    }

    fun makeGetRequest(postId: String){
        commentRepository.makeGetRequest(postId)
    }
}