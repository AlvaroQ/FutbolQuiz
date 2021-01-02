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
import com.quiz.futbol.databinding.FollowsFragmentBinding
import com.quiz.futbol.utils.Constants.FOLLOW_SCREEN
import com.quiz.futbol.utils.setSafeOnClickListener
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class FollowsFragment : Fragment() {

    private lateinit var binding: FollowsFragmentBinding
    private val followsViewModel: FollowsViewModel by lifecycleScope.viewModel(this)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FollowsFragmentBinding.inflate(inflater)
        val root = binding.root
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
                binding.recyclerFollows.adapter = FollowsItemsAdapter(requireContext(), model.userList, followsViewModel::unfollow)
                binding.recyclerFollows.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
                binding.recyclerFollows.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }
            is FollowsViewModel.UiModel.FollowerResult -> {
                if(model.isSuccess) Toast.makeText(requireContext(), "Ya no sigues al usuario", Toast.LENGTH_SHORT).show()
                else Toast.makeText(requireContext(), "No se ha podido completar dejar de seguir al usuario", Toast.LENGTH_SHORT).show()
                refreshList()
            }
            is FollowsViewModel.UiModel.FollowingResult -> {
                if(model.isSuccess) Toast.makeText(requireContext(), "El usuario seleccionado ya no es seguido por ti", Toast.LENGTH_SHORT).show()
                else Toast.makeText(requireContext(), "No se ha podido completar dejar de seguir al usuario", Toast.LENGTH_SHORT).show()
                refreshList()
            }
        }
    }

    private fun refreshList() {
        when(activity?.intent?.getStringExtra(FOLLOW_SCREEN)) {
            getString(R.string.following) -> followsViewModel.loadFollowingList()
            else -> followsViewModel.loadFollowersList()
        }
    }

    companion object {
        private val TAG = FollowsFragment::class.java.simpleName
        fun newInstance() = FollowsFragment()
    }
}