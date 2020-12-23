package com.quiz.futbol.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.quiz.futbol.R
import com.quiz.futbol.databinding.ProfileFragmentBinding
import com.quiz.futbol.utils.glideLoadBase64
import com.quiz.futbol.utils.setSafeOnClickListener
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

        binding.layoutGlobal.setSafeOnClickListener { profileViewModel.loadGlobalArchievementsItems() }
        binding.layoutPersonal.setSafeOnClickListener { profileViewModel.loadPersonalArchievementsItems() }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel.userData.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun updateUi(model: ProfileViewModel.UiModel) {
        when (model) {
            is ProfileViewModel.UiModel.UserPersonalData -> {
                binding.textNameUser.text = model.user.displayName
                if(model.user.displaydescription == "") binding.textDescriptionUser.visibility = View.GONE
                else binding.textDescriptionUser.text = model.user.displaydescription
                binding.textDescriptionUser.text = model.user.displaydescription
                glideLoadBase64(requireContext(), model.user.photoBase64, binding.imageUser)
            }
            is ProfileViewModel.UiModel.Level -> {
                binding.textLevel.text = model.numberLevel.toString()
            }
            is ProfileViewModel.UiModel.Followers -> {
                binding.textNumberFollowers.text = model.numberFollowers.toString()
            }
            is ProfileViewModel.UiModel.Following -> {
                binding.textNumberFollowing.text = model.numberFollowing.toString()
            }
            is ProfileViewModel.UiModel.MainUserStageCompleted -> {
                if (model.items.size == 0) binding.recyclerArchivements.visibility = View.GONE
                else {
                    binding.recyclerArchivements.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                    binding.recyclerArchivements.adapter = MainArchievementItemsAdapter(requireContext(), model.items.toMutableList())
                }
            }
            is ProfileViewModel.UiModel.MainArchievements -> {
                binding.bottomSheet.textTitle.text = getString(R.string.last_global_archievements)
                binding.bottomSheet.recyclerEvents.adapter = ProfileBottomSheetItemsAdapter(requireContext(), model.items.toMutableList())
                binding.bottomSheet.recyclerEvents.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                bottomSheetBehavior.isDraggable = true
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }
            is ProfileViewModel.UiModel.PersonalArchievements -> {
                binding.bottomSheet.textTitle.text = getString(R.string.last_personal_archievements)
                binding.bottomSheet.recyclerEvents.adapter = ProfileBottomSheetItemsAdapter(requireContext(), model.items.toMutableList())
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