package com.gentlekboy.postappusingfragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gentlekboy.postappusingfragments.databinding.PostsViewHolderBinding
import com.gentlekboy.postappusingfragments.model.posts.PostListItem
import com.gentlekboy.postappusingfragments.utils.ClickPostInterface

class PostAdapter(private var clickPostInterface: ClickPostInterface): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(var binding: PostsViewHolderBinding): RecyclerView.ViewHolder(binding.root)

    //Create diffCallback to add lists in my adapter in a background thread
    private val diffCallback = object: DiffUtil.ItemCallback<PostListItem>(){
        override fun areItemsTheSame(oldItem: PostListItem, newItem: PostListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostListItem, newItem: PostListItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)

    //Inflate views from view holder layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostsViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    //Bind data to views
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        with(holder){
            with(differ.currentList[position]){
                binding.postTitle.text = title
                binding.postBody.text = body
                binding.postId.text = id.toString()

                holder.itemView.setOnClickListener {
                    clickPostInterface.navigateToCommentsActivity(position, id, body, title)
                }
            }
        }
    }

    //Get the list of items in the adapter
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}