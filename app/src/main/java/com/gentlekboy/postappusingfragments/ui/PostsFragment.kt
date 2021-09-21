package com.gentlekboy.postappusingfragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.gentlekboy.postappusingfragments.R
import com.gentlekboy.postappusingfragments.adapter.PostAdapter
import com.gentlekboy.postappusingfragments.databinding.FragmentPostsBinding
import com.gentlekboy.postappusingfragments.model.PostListItem
import com.gentlekboy.postappusingfragments.utils.ClickPostInterface
import com.gentlekboy.postappusingfragments.viewModel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment(), ClickPostInterface {
    private lateinit var postAdapter: PostAdapter
    private lateinit var listOfPosts: MutableList<PostListItem>
    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViewAdapter()
        observeViewModel()
    }

    private fun observeViewModel() {
        postViewModel.makeGetRequest()
        postViewModel.getAllPosts().observe(viewLifecycleOwner, {
            listOfPosts.addAll(it)
            postAdapter.differ.submitList(it)
        })
    }

    private fun initRecyclerViewAdapter() {
        listOfPosts = mutableListOf()
        postAdapter = PostAdapter(listOfPosts, this)
        binding.postRecyclerview.adapter = postAdapter
        binding.postRecyclerview.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun navigateToCommentsActivity(position: Int, id: Int) {

    }
}