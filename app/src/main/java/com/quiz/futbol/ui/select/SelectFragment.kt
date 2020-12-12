package com.quiz.futbol.ui.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.quiz.futbol.databinding.SelectFragmentBinding
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

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet.constraintBottomSheet)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectViewModel.navigation.observe(viewLifecycleOwner, Observer(::navigate))
        selectViewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun updateUi(model: SelectViewModel.UiModel) {
        when (model) {
            is SelectViewModel.UiModel.Content -> {
                binding.bottomSheet.recyclerCategory.adapter = SelectItemsAdapter(requireContext(), model.items.toMutableList())

                val glm = GridLayoutManager(context, 2)
                glm.spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position % 5 == 0) 2 else 1
                    }
                }

                binding.bottomSheet.recyclerCategory.layoutManager = glm
                bottomSheetBehavior.isDraggable = true
                bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO, true)
            }
        }
    }

    private fun navigate(navigation: SelectViewModel.Navigation?) {
        when (navigation) {
            is SelectViewModel.Navigation.GameByImage -> {
            }
            is SelectViewModel.Navigation.GameByName -> {
            }
            is SelectViewModel.Navigation.GameByCapacity -> {
            }
            is SelectViewModel.Navigation.GameByBuilt -> {
            }
        }
    }
}
