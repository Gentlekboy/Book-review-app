package com.gentlekboy.postappusingfragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gentlekboy.postappusingfragments.databinding.PostsViewHolderBinding
import com.gentlekboy.postappusingfragments.model.PostListItem
import com.gentlekboy.postappusingfragments.utils.ClickPostInterface

class PostAdapter(
    private var listOfPosts: MutableList<PostListItem>,
    private var clickPostInterface: ClickPostInterface
): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(var binding: PostsViewHolderBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object: DiffUtil.ItemCallback<PostListItem>(){
        override fun areItemsTheSame(oldItem: PostListItem, newItem: PostListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostListItem, newItem: PostListItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostsViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        with(holder){
            with(listOfPosts[position]){
                binding.postTitle.text = title
                binding.postBody.text = body
                binding.postId.text = id.toString()

                holder.itemView.setOnClickListener {
                    clickPostInterface.navigateToCommentsActivity(position, id)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfPosts.size
    }
}