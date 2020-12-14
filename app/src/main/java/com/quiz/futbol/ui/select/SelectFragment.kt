package com.quiz.futbol.ui.select

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.quiz.futbol.R
import com.quiz.futbol.common.startActivity
import com.quiz.futbol.databinding.SelectFragmentBinding
import com.quiz.futbol.ui.game.GameActivity
import com.quiz.futbol.utils.Constants
import com.quiz.futbol.utils.Constants.TYPE_CHAMPIONSHIP
import com.quiz.futbol.utils.Constants.TYPE_GAME
import com.quiz.futbol.utils.Constants.TypeGame.*
import com.quiz.futbol.utils.setSafeOnClickListener
import com.quiz.futbol.utils.underline
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class SelectFragment : Fragment() {
    private lateinit var binding: SelectFragmentBinding
    private val selectViewModel: SelectViewModel by lifecycleScope.viewModel(this)

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    companion object {
        fun newInstance() = SelectFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SelectFragmentBinding.inflate(inflater)
        val root = binding.root

        binding.helloText.underline()
        binding.helloText.setSafeOnClickListener {
            // login or profile
        }
        binding.btnStartCareerMode.setSafeOnClickListener {
            selectViewModel.loadCareerMode()
        }
        binding.btnStartTrainingMode.setSafeOnClickListener {
            selectViewModel.loadTrainingMode()
        }

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet.constraintBottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectViewModel.navigation.observe(viewLifecycleOwner, Observer(::navigate))
        selectViewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun updateUi(model: SelectViewModel.UiModel) {
        when (model) {
            is SelectViewModel.UiModel.ContentCareerMode -> {
                binding.bottomSheet.textTitle.text = if(model.mode == Constants.ModeGame.TRAINIG) {
                    getString(R.string.trainig_mode)
                } else {
                    getString(R.string.carrer_mode)
                }
                binding.bottomSheet.recyclerCategory.adapter = SelectItemsAdapter(requireContext(), model.items.toMutableList())

                val glm = GridLayoutManager(context, 2)
                glm.spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position % 5 == 0) 2 else 1
                    }
                }
                binding.bottomSheet.recyclerCategory.layoutManager = glm
                bottomSheetBehavior.isDraggable = true
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }
        }
    }

    private fun navigate(navigation: SelectViewModel.Navigation?) {
        when (navigation) {
            is SelectViewModel.Navigation.GameByImage -> {
                activity?.startActivity<GameActivity> {
                    putExtra(TYPE_GAME, BY_IMAGE)
                    putExtra(TYPE_CHAMPIONSHIP, navigation.championship)
                }
            }
            is SelectViewModel.Navigation.GameByName -> {
                activity?.startActivity<GameActivity> {
                    putExtra(TYPE_GAME, BY_NAME)
                    putExtra(TYPE_CHAMPIONSHIP, navigation.championship)
                }
            }
            is SelectViewModel.Navigation.GameByCapacity -> {
                activity?.startActivity<GameActivity> {
                    putExtra(TYPE_GAME, BY_CAPACITY)
                    putExtra(TYPE_CHAMPIONSHIP, navigation.championship)
                }
            }
            is SelectViewModel.Navigation.GameByBuilt -> {
                activity?.startActivity<GameActivity> {
                    putExtra(TYPE_GAME, BY_BUILT)
                    putExtra(TYPE_CHAMPIONSHIP, navigation.championship)
                }
            }
        }
    }
}
