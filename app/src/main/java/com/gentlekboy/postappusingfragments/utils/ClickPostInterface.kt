package com.gentlekboy.postappusingfragments.utils

interface ClickPostInterface {
    //Navigate to comment with parameters
    fun navigateToCommentsActivity(position: Int, postId: Int, postBody: String, title: String)
}