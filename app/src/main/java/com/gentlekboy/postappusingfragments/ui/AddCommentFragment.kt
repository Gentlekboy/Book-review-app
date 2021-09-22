package com.gentlekboy.postappusingfragments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gentlekboy.postappusingfragments.R
import com.gentlekboy.postappusingfragments.databinding.FragmentAddCommentBinding
import com.gentlekboy.postappusingfragments.model.comments.CommentListItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCommentFragment : Fragment() {
    //Initialize variables
    private var _binding: FragmentAddCommentBinding? = null
    private val binding get() = _binding!!
    private val args: AddCommentFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Set up view binding
        _binding = FragmentAddCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Navigate to comments fragment on click of the back button
        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_addCommentFragment_to_commentsFragment)
        }

        //Pass commentListItem to comment fragment
        binding.addCommentButton.setOnClickListener {
            val commentBody = binding.commentEditText.text.toString().trim()
            val postId = args.postId
            val id = args.id + 1

            if (commentBody.isNotEmpty()){
                val commentListItem = CommentListItem(id, commentBody, "kufreabasi.udoh@decagon.dev", "Kufre Udoh", postId)
                val direction = AddCommentFragmentDirections
                    .actionAddCommentFragmentToCommentsFragment(args.postId.toString(), args.postBody, args.title, commentListItem)
                findNavController().navigate(direction)
            }
        }
    }

    //Make binding null to avoid memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}