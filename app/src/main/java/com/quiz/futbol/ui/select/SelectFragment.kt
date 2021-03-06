package com.quiz.futbol.ui.select

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.quiz.futbol.R
import com.quiz.futbol.databinding.FragmentSelectBinding
import com.quiz.futbol.managers.DialogCustomManager
import com.quiz.futbol.utils.Constants.ModeGame
import com.quiz.futbol.utils.Constants.TypeGame.*
import com.quiz.futbol.utils.glideCircleLoadBase64
import com.quiz.futbol.utils.log
import com.quiz.futbol.utils.setSafeOnClickListener
import com.quiz.futbol.utils.underline
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf

class SelectFragment : Fragment() {
    private lateinit var binding: FragmentSelectBinding
    private val selectViewModel: SelectViewModel by lifecycleScope.viewModel(this)
    private val dialogCustomManager: DialogCustomManager by lifecycleScope.inject { parametersOf(requireActivity()) }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var modeGame: ModeGame

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSelectBinding.inflate(inflater)
        val root = binding.root

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet.constraintBottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        binding.btnStartCareerMode.setSafeOnClickListener { selectViewModel.loadCareerMode() }
        binding.btnStartTrainingMode.setSafeOnClickListener { selectViewModel.loadTrainingMode() }
        binding.btnMoreApps.setSafeOnClickListener { selectViewModel.goToMoreApps() }
        binding.layoutUser.setSafeOnClickListener { selectViewModel.goToProfile() }

        binding.btnMoreApps.underline()
        binding.helloText.underline()

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectViewModel.navigation.observe(viewLifecycleOwner, Observer(::navigate))
        selectViewModel.dialog.observe(viewLifecycleOwner, Observer(::showDialog))
        selectViewModel.loadBottomSheetData.observe(viewLifecycleOwner, Observer(::updateUi))
        selectViewModel.userData.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun updateUi(model: SelectViewModel.UiModel) {
        when (model) {
            is SelectViewModel.UiModel.ContentCareerMode -> {
                binding.bottomSheet.textTitle.text = if(model.mode == ModeGame.TRAINNIG) {
                    modeGame = ModeGame.TRAINNIG
                    getString(R.string.training_mode)
                } else {
                    modeGame = ModeGame.CARRER
                    getString(R.string.carrer_mode)
                }
                binding.bottomSheet.recyclerCategory.adapter = SelectItemsAdapter(requireContext(), model.items.toMutableList())

                val glm = GridLayoutManager(context, 2)
                glm.spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position % 6 == 0) 2 else 1
                    }
                }
                binding.bottomSheet.recyclerCategory.layoutManager = glm
                bottomSheetBehavior.isDraggable = true
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }
            is SelectViewModel.UiModel.UserData -> {
                binding.helloText.text = getString(R.string.hello, model.user.displayName)
                glideCircleLoadBase64(requireContext(), model.user.photoBase64, binding.imageUser)
            }
        }
    }

    private fun showDialog(dialog: SelectViewModel.Dialog?) {
        when (dialog) {
            is SelectViewModel.Dialog.DialogLevelLock -> dialogCustomManager.showDialogLevelLock()
        }
    }

    private fun navigate(navigation: SelectViewModel.Navigation?) {
        log(TAG, "navigate to $navigation")
        val action = when (navigation) {
            is SelectViewModel.Navigation.GameByImage -> SelectFragmentDirections.actionNavigationSelectToGame(BY_IMAGE.name, navigation.championship.name, modeGame.name)
            is SelectViewModel.Navigation.GameByName -> SelectFragmentDirections.actionNavigationSelectToGame(BY_NAME.name, navigation.championship.name, modeGame.name)
            is SelectViewModel.Navigation.GameByShield -> SelectFragmentDirections.actionNavigationSelectToGame(BY_SHIELD.name, navigation.championship.name, modeGame.name)
            is SelectViewModel.Navigation.GameByCapacity -> SelectFragmentDirections.actionNavigationSelectToGame(BY_CAPACITY.name, navigation.championship.name, modeGame.name)
            is SelectViewModel.Navigation.GameByBuilt -> SelectFragmentDirections.actionNavigationSelectToGame(BY_BUILT.name, navigation.championship.name, modeGame.name)
            SelectViewModel.Navigation.MoreApps -> SelectFragmentDirections.actionNavigationSelectToMoreApps()
            else -> SelectFragmentDirections.actionNavigationSelectToProfile()
        }
        findNavController().navigate(action)
    }

    companion object {
        private val TAG = SelectFragment::class.java.simpleName
    }
}
