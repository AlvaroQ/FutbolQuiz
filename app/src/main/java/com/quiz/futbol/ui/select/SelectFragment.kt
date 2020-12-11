package com.quiz.futbol.ui.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.quiz.futbol.R
import com.quiz.futbol.common.startActivity
import com.quiz.futbol.databinding.SelectFragmentBinding
import androidx.lifecycle.Observer
import com.quiz.futbol.ui.game.GameActivity
import com.quiz.futbol.utils.Constants
import com.quiz.futbol.utils.setSafeWithoutAnimationOnClickListener
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class SelectFragment : Fragment() {
    private lateinit var binding: SelectFragmentBinding
    private val selectViewModel: SelectViewModel by lifecycleScope.viewModel(this)

    companion object {
        fun newInstance() = SelectFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SelectFragmentBinding.inflate(inflater)
        val root = binding.root
/*
        val btnStartByFlag: CardView = root.findViewById(R.id.cardStartByFlag)
        btnStartByFlag.setSafeWithoutAnimationOnClickListener {
            selectViewModel.navigateToGameByFlag()
        }

        val btnStartByCountry: CardView = root.findViewById(R.id.cardStartByCountry)
        btnStartByCountry.setSafeWithoutAnimationOnClickListener {
            selectViewModel.navigateToGameByCountry()
        }*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectViewModel.navigation.observe(viewLifecycleOwner, Observer(::navigate))
    }

    private fun navigate(navigation: SelectViewModel.Navigation?) {
        when (navigation) {
            SelectViewModel.Navigation.GameByImage -> {
                activity?.startActivity<GameActivity> { putExtra(Constants.TYPE_GAME, Constants.TypeGame.BY_IMAGE) }
            }
            SelectViewModel.Navigation.GameByName -> {
                activity?.startActivity<GameActivity> { putExtra(Constants.TYPE_GAME, Constants.TypeGame.BY_NAME) }
            }
        }
    }
}
