package com.gentlekboy.postappusingfragments.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gentlekboy.postappusingfragments.R
import com.gentlekboy.postappusingfragments.adapter.CommentAdapter
import com.gentlekboy.postappusingfragments.databinding.FragmentCommentsBinding
import com.gentlekboy.postappusingfragments.viewModel.CommentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : Fragment() {
    private lateinit var commentAdapter: CommentAdapter
    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!
    private val commentViewModel: CommentViewModel by viewModels()
    private val args: CommentsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViewAdapter()

        val postId = args.postId

        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_commentsFragment_to_postsFragment)
            commentViewModel.deleteComments()
        }

        binding.commentFloatingActionButton.setOnClickListener {

            val directions = CommentsFragmentDirections.actionCommentsFragmentToAddCommentFragment(postId.toInt(), commentAdapter.differ.currentList.size, args.title, args.postBody)
            Log.d("GKBADD", "PASSED ID -> : ${commentAdapter.differ.currentList.size}")
            findNavController().navigate(directions)
        }

        showPostFromPostFragment()
        addNewComment()
        observeViewModel(postId)
    }

    private fun addNewComment() {
        val commentListItem = args.commentListItem

        if (commentListItem != null) {
            commentViewModel.addNewComment(commentListItem)

            Log.d("GKBADD", "ADDED COMMENT: $commentListItem")
        }
    }

    private fun showPostFromPostFragment() {
        binding.titleInComment.text = args.title
        binding.postBodyInComment.text = args.postBody
    }

    private fun observeViewModel(postId: String) {
        commentViewModel.makeGetRequest(postId)
        commentViewModel.getAllComments().observe(viewLifecycleOwner, {
            commentAdapter.differ.submitList(it)
        })
    }

    private fun initRecyclerViewAdapter() {
        commentAdapter = CommentAdapter()
        binding.recyclerviewInComment.adapter = commentAdapter
        binding.recyclerviewInComment.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}