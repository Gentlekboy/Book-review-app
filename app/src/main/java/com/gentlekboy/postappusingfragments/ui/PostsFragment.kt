package com.gentlekboy.postappusingfragments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gentlekboy.postappusingfragments.adapter.PostAdapter
import com.gentlekboy.postappusingfragments.databinding.FragmentPostsBinding
import com.gentlekboy.postappusingfragments.utils.ClickPostInterface
import com.gentlekboy.postappusingfragments.viewModel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment(), ClickPostInterface {
    private lateinit var postAdapter: PostAdapter
    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!
    private val postViewModel: PostViewModel by viewModels()
    private val args: PostsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            val action = PostsFragmentDirections.actionPostsFragmentToAddPostFragment(postAdapter.differ.currentList.size)
            findNavController().navigate(action)
        }

        initRecyclerViewAdapter()
        addNewBook()
        observeViewModel()
    }

    private fun addNewBook() {
        val newBook = args.postListItem

        if (newBook != null){
            postViewModel.addNewPost(newBook)
        }
    }

    private fun observeViewModel() {
        postViewModel.makeGetRequest()
        postViewModel.getAllPosts().observe(viewLifecycleOwner, {
            postAdapter.differ.submitList(it)
        })
    }

    private fun initRecyclerViewAdapter() {
        postAdapter = PostAdapter(this)
        binding.postRecyclerview.adapter = postAdapter
        binding.postRecyclerview.setHasFixedSize(true)
    }

    override fun navigateToCommentsActivity(position: Int, postId: Int) {
        val action = PostsFragmentDirections.actionPostsFragmentToCommentsFragment(postId.toString())
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}