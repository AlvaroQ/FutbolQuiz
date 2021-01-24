package com.quiz.futbol.ui.moreApps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.quiz.domain.App
import com.quiz.futbol.databinding.FragmentMoreAppsBinding
import com.quiz.futbol.ui.MainActivity
import com.quiz.futbol.utils.glideLoadingGif
import com.quiz.futbol.utils.setSafeOnClickListener
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class MoreAppsFragment : Fragment() {
    private lateinit var binding: FragmentMoreAppsBinding
    private val moreAppsViewModel: MoreAppsViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentMoreAppsBinding.inflate(inflater)
        binding.appBarLayoutMoreApps.btnBack.setSafeOnClickListener { activity?.onBackPressed() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moreAppsViewModel.list.observe(viewLifecycleOwner, Observer(::fillList))
        moreAppsViewModel.progress.observe(viewLifecycleOwner, Observer(::updateProgress))
        moreAppsViewModel.showingAds.observe(viewLifecycleOwner, Observer(::loadAd))
    }

    private fun fillList(appList: MutableList<App>) {
        binding.recyclerviewMoreApps.adapter = MoreAppsListAdapter(requireContext(), appList)
    }

    private fun loadAd(model: MoreAppsViewModel.UiModel) {
        //if (model is MoreAppsViewModel.UiModel.ShowAd) (activity as MainActivity).showAd(model.show)
    }

    private fun updateProgress(model: MoreAppsViewModel.UiModel?) {
        if (model is MoreAppsViewModel.UiModel.Loading && model.show) {
            glideLoadingGif(activity as MainActivity, binding.imagenLoading)
            binding.imagenLoading.visibility = View.VISIBLE
        } else {
            binding.imagenLoading.visibility = View.GONE
        }
    }
}