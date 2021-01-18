package com.quiz.futbol.ui.follows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quiz.futbol.R
import com.quiz.futbol.databinding.FragmentFollowsBinding
import com.quiz.futbol.utils.Constants.FollowTypes
import com.quiz.futbol.utils.setSafeOnClickListener
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class FollowsFragment : Fragment() {

    private lateinit var binding: FragmentFollowsBinding
    private val followsViewModel: FollowsViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFollowsBinding.inflate(inflater)
        val root = binding.root
        binding.appBarLayoutFollow.btnBack.setSafeOnClickListener { activity?.onBackPressed() }
        refreshList()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followsViewModel.followsData.observe(viewLifecycleOwner, Observer(::updateUi))
        followsViewModel.unfollowFinished.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun updateUi(model: FollowsViewModel.UiModel) {
        when (model) {
            is FollowsViewModel.UiModel.FilledUserList -> {
                if(model.userList.size > 0) {
                    binding.recyclerFollows.adapter = FollowsItemsAdapter(requireContext(), model.userList, followsViewModel::unfollow)
                    binding.recyclerFollows.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
                    binding.recyclerFollows.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                } else {
                    when(FollowsFragmentArgs.fromBundle(requireArguments()).typeFollow) {
                        FollowTypes.FOLLOWING.name -> binding.textNoItems.text = getString(R.string.no_following)
                        FollowTypes.FOLLOWER.name -> binding.textNoItems.text = getString(R.string.no_followers)
                    }
                }
            }
            is FollowsViewModel.UiModel.FollowerResult -> {
                if(model.isSuccess) Toast.makeText(requireContext(), getString(R.string.follower_to_user), Toast.LENGTH_SHORT).show()
                else Toast.makeText(requireContext(), getString(R.string.follower_to_user_error), Toast.LENGTH_SHORT).show()
                refreshList()
            }
            is FollowsViewModel.UiModel.FollowingResult -> {
                if(model.isSuccess) Toast.makeText(requireContext(), getString(R.string.following_to_user), Toast.LENGTH_SHORT).show()
                else Toast.makeText(requireContext(), getString(R.string.following_to_user_error), Toast.LENGTH_SHORT).show()
                refreshList()
            }
        }
    }

    private fun refreshList() {
        when(FollowsFragmentArgs.fromBundle(requireArguments()).typeFollow) {
            FollowTypes.FOLLOWING.name -> {
                binding.appBarLayoutFollow.toolbarTitle.text = getString(R.string.following)
                followsViewModel.loadFollowingList()
            }
            FollowTypes.FOLLOWER.name -> {
                binding.appBarLayoutFollow.toolbarTitle.text = getString(R.string.followers)
                followsViewModel.loadFollowersList()
            }
        }
    }

    companion object {
        private val TAG = FollowsFragment::class.java.simpleName
    }
}