package com.quiz.futbol.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.quiz.domain.User
import com.quiz.futbol.R
import com.quiz.futbol.common.startActivity
import com.quiz.futbol.databinding.ProfileFragmentBinding
import com.quiz.futbol.ui.follows.FollowsActivity
import com.quiz.futbol.ui.profileEdit.ProfileEditActivity
import com.quiz.futbol.utils.Constants.USER_UUID
import com.quiz.futbol.utils.Constants.FOLLOW_SCREEN
import com.quiz.futbol.utils.glideLoadBase64
import com.quiz.futbol.utils.setSafeOnClickListener
import com.quiz.futbol.utils.expandImage
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: ProfileFragmentBinding
    private val profileViewModel: ProfileViewModel by lifecycleScope.viewModel(this)

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ProfileFragmentBinding.inflate(inflater)
        val root = binding.root

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet.constraintBottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        binding.imageUser.setSafeOnClickListener { profileViewModel.onUserImageClicked(binding.imageUser) }
        binding.imageEditProfile.setSafeOnClickListener { profileViewModel.goToEditProfile() }
        binding.layoutGlobal.setSafeOnClickListener { profileViewModel.loadGlobalArchievementsItems() }
        binding.layoutPersonal.setSafeOnClickListener { profileViewModel.loadPersonalArchievementsItems(activity?.intent?.getStringExtra(USER_UUID)) }
        binding.layoutFollowing.setSafeOnClickListener { profileViewModel.goToFollows(getString(R.string.following)) }
        binding.layoutFollowers.setSafeOnClickListener { profileViewModel.goToFollows(getString(R.string.followers)) }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.apply {
            userData.observe(viewLifecycleOwner, Observer(::updateUi))
            navigation.observe(viewLifecycleOwner, Observer(::navigate))
        }
    }

    override fun onStart() {
        super.onStart()
        profileViewModel.loadUserPersonalData(activity?.intent?.getStringExtra(USER_UUID))
    }

    private fun navigate(navigation: ProfileViewModel.Navigation) {
        when (navigation) {
            ProfileViewModel.Navigation.EditProfile -> activity?.startActivity<ProfileEditActivity> {}
            is ProfileViewModel.Navigation.Follows -> activity?.startActivity<FollowsActivity> { putExtra(FOLLOW_SCREEN, navigation.screenFollow) }
            is ProfileViewModel.Navigation.Expand -> activity?.expandImage(navigation.imageView, navigation.icon)
            is ProfileViewModel.Navigation.FriendProfile -> activity?.startActivity<ProfileActivity> { putExtra(USER_UUID, navigation.uuid) }
        }
    }

    private fun updateFriendUI() {
        binding.imageEditProfile.visibility = View.GONE
        binding.layoutGlobal.visibility = View.GONE
        setFollowButton(binding.buttonFollow.isSelected)
        binding.buttonFollow.setSafeOnClickListener {
            setFollowButton(!binding.buttonFollow.isSelected)
        }
    }

    private fun setFollowButton(isSelected: Boolean) {
        binding.buttonFollow.isSelected = isSelected
        if(binding.buttonFollow.isSelected) {
            binding.buttonFollow.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_follow_selected)
            binding.buttonFollow.setTextColor(requireActivity().getColor(R.color.white))
            binding.buttonFollow.text = getString(R.string.unfollow)
        }
        else {
            binding.buttonFollow.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_follow)
            binding.buttonFollow.setTextColor(requireActivity().getColor(R.color.colorPrimary))
            binding.buttonFollow.text = getString(R.string.follow)
        }
    }

    private fun updateUserUI(user: User) {
        binding.textNameUser.text = user.displayName
        glideLoadBase64(requireContext(), user.photoBase64, binding.imageUser)

        if(user.displayDescription == "") binding.textDescriptionUser.visibility = View.GONE
        else binding.textDescriptionUser.text = user.displayDescription

        if(user.displayLocation == "") binding.layoutLocation.visibility = View.GONE
        else binding.textLocationUser.text = user.displayLocation
    }

    private fun updateUi(model: ProfileViewModel.UiModel) {
        when (model) {
            is ProfileViewModel.UiModel.MyPersonalData -> updateUserUI(model.user)
            is ProfileViewModel.UiModel.FriendPersonalData -> {
                updateUserUI(model.user)
                updateFriendUI()
                profileViewModel.isFollowed(activity?.intent?.getStringExtra(USER_UUID)!!)
            }
            is ProfileViewModel.UiModel.IsFollowed -> {
                binding.buttonFollow.visibility = View.VISIBLE
                setFollowButton(model.isFollowed)
            }
            is ProfileViewModel.UiModel.Level -> binding.textLevel.text = model.numberLevel.toString()
            is ProfileViewModel.UiModel.Followers -> binding.textNumberFollowers.text = model.numberFollowers.toString()
            is ProfileViewModel.UiModel.Following -> binding.textNumberFollowing.text = model.numberFollowing.toString()
            is ProfileViewModel.UiModel.MainUserStageCompleted -> {
                if (model.items.size == 0) binding.recyclerArchivements.visibility = View.GONE
                else {
                    binding.recyclerArchivements.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                    binding.recyclerArchivements.adapter = MainArchievementItemsAdapter(requireContext(), model.items.toMutableList())
                }
            }
            is ProfileViewModel.UiModel.MainArchievements -> {
                binding.bottomSheet.textTitle.text = getString(R.string.last_global_archievements)
                binding.bottomSheet.recyclerEvents.adapter = ProfileBottomSheetItemsAdapter(model.items.toMutableList())
                binding.bottomSheet.recyclerEvents.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                bottomSheetBehavior.isDraggable = true
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }
            is ProfileViewModel.UiModel.PersonalArchievements -> {
                binding.bottomSheet.textTitle.text = getString(R.string.last_personal_archievements)
                binding.bottomSheet.recyclerEvents.adapter = ProfileBottomSheetItemsAdapter(model.items.toMutableList())
                binding.bottomSheet.recyclerEvents.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                bottomSheetBehavior.isDraggable = true
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }
        }
    }

    companion object {
        private val TAG = ProfileFragment::class.java.simpleName
        fun newInstance() = ProfileFragment()
    }
}