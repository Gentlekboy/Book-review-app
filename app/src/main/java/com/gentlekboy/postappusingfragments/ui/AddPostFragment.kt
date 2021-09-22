package com.gentlekboy.postappusingfragments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gentlekboy.postappusingfragments.R
import com.gentlekboy.postappusingfragments.databinding.FragmentAddPostBinding
import com.gentlekboy.postappusingfragments.model.posts.PostListItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPostFragment : Fragment() {
    private var _binding: FragmentAddPostBinding? = null
    private val binding get() = _binding!!
    private val args: AddPostFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_addPostFragment_to_postsFragment)
        }

        binding.addBookButton.setOnClickListener {
            addNewBook()
        }
    }

    private fun addNewBook() {
        val title = binding.addBookTitle.text.toString().trim()
        val body = binding.addBookBody.text.toString().trim()
        val postId = args.postId + 1

        if (title.isNotEmpty() && body.isNotEmpty()){
            val postListItem = PostListItem(postId, body, title, 11)
            val action = AddPostFragmentDirections.actionAddPostFragmentToPostsFragment(postListItem)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}