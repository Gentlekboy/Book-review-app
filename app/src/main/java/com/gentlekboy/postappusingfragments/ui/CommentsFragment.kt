package com.gentlekboy.postappusingfragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gentlekboy.postappusingfragments.R
import com.gentlekboy.postappusingfragments.adapter.CommentAdapter
import com.gentlekboy.postappusingfragments.adapter.PostAdapter
import com.gentlekboy.postappusingfragments.databinding.FragmentCommentsBinding
import com.gentlekboy.postappusingfragments.databinding.FragmentPostsBinding
import com.gentlekboy.postappusingfragments.viewModel.CommentViewModel
import com.gentlekboy.postappusingfragments.viewModel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : Fragment() {
    private lateinit var commentAdapter: CommentAdapter
    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!
    private val commentViewModel: CommentViewModel by viewModels()
    val args: CommentsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postId = args.postId

        initRecyclerViewAdapter()
        observeViewModel(postId)
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