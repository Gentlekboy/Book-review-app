package com.gentlekboy.postappusingfragments.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gentlekboy.postappusingfragments.adapter.PostAdapter
import com.gentlekboy.postappusingfragments.databinding.FragmentPostsBinding
import com.gentlekboy.postappusingfragments.model.posts.PostListItem
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
        //Set up view binding
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Navigate to add posts fragment
        binding.floatingActionButton.setOnClickListener {
            val action = PostsFragmentDirections.actionPostsFragmentToAddPostFragment(postAdapter.differ.currentList.size)
            findNavController().navigate(action)
        }

        initRecyclerViewAdapter()
        addNewBook()
        observeViewModel()
        filterPosts()
    }

    //This function filters posts
    private fun filterPosts() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                val searchText = query?.lowercase()?.trim()
                val newPostList = mutableListOf<PostListItem>()
//
                if (searchText != null) {
                    if (searchText.isNotEmpty()){
                        postViewModel.getAllPosts().observe(viewLifecycleOwner, { allPosts ->
                            newPostList.addAll(allPosts)
                            postViewModel.deleteAllPosts()

                            newPostList.forEach {
                                if (it.title.lowercase().contains(searchText)){
                                    val newList = mutableListOf(it)
                                    postAdapter.differ.submitList(newList)
                                }
                            }
                        })
                    }else{
                        postAdapter.differ.submitList(newPostList)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    //This function adds a new post
    private fun addNewBook() {
        val newBook = args.postListItem

        if (newBook != null){
            postViewModel.apply {
                makePostRequest(newBook)
                addNewPost(newBook)
            }
        }
    }

    //This function observes changes in the database and updates UI accordingly
    private fun observeViewModel() {
        postViewModel.makeGetRequest()
        postViewModel.getAllPosts().observe(viewLifecycleOwner, {
            postAdapter.differ.submitList(it)
        })
    }

    //This function initializes recycler view adapter
    private fun initRecyclerViewAdapter() {
        postAdapter = PostAdapter(this)
        binding.postRecyclerview.adapter = postAdapter
        binding.postRecyclerview.setHasFixedSize(true)
    }

    //This function navigates to a particular clicked post's comment
    override fun navigateToCommentsActivity(position: Int, postId: Int, postBody: String, title: String) {
        val action = PostsFragmentDirections.actionPostsFragmentToCommentsFragment(postId.toString(), postBody, title)
        findNavController().navigate(action)
    }

    //Make binding null to avoid memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}