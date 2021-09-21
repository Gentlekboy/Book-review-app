package com.gentlekboy.postappusingfragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gentlekboy.postappusingfragments.databinding.CommentsViewHolderBinding
import com.gentlekboy.postappusingfragments.model.comments.CommentListItem

class CommentAdapter: RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    inner class CommentViewHolder(var binding: CommentsViewHolderBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object: DiffUtil.ItemCallback<CommentListItem>(){
        override fun areItemsTheSame(oldItem: CommentListItem, newItem: CommentListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CommentListItem, newItem: CommentListItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = CommentsViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        with(holder){
            with(differ.currentList[position]){
                binding.commentName.text = name
                binding.commentEmail.text = email
                binding.commentBody.text = body
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}