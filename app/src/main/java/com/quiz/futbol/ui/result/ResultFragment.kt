package com.quiz.futbol.ui.result

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import com.quiz.futbol.R
import com.quiz.futbol.databinding.ResultFragmentBinding
import com.quiz.futbol.utils.Constants.POINTS
import com.quiz.futbol.utils.openAppOnPlayStore
import com.quiz.futbol.utils.rateApp
import com.quiz.futbol.utils.setSafeOnClickListener
import com.quiz.futbol.utils.shareApp
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel


class ResultFragment : Fragment() {
    private lateinit var binding: ResultFragmentBinding
    private val resultViewModel: ResultViewModel by lifecycleScope.viewModel(this)
    private var gamePoints = 0
    private lateinit var imageViewPickup: ImageView

    companion object {
        fun newInstance() = ResultFragment()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = ResultFragmentBinding.inflate(inflater)
        val root = binding.root

        if(PreferenceManager.getDefaultSharedPreferences(context).getBoolean("sound", true)) {
            MediaPlayer.create(context, R.raw.game_over).start()
        }
        gamePoints = activity?.intent?.extras?.getInt(POINTS)!!

        val textResult: TextView = root.findViewById(R.id.textResult)
        textResult.text = resources.getString(R.string.successful_stadiums, gamePoints)

        val btnContinue: Button = root.findViewById(R.id.btnContinue)
        btnContinue.setSafeOnClickListener { resultViewModel.navigateToGame() }

        val btnShare: Button = root.findViewById(R.id.btnShare)
        btnShare.setSafeOnClickListener { resultViewModel.navigateToShare(gamePoints) }

        val btnRate: Button = root.findViewById(R.id.btnRate)
        btnRate.setSafeOnClickListener { resultViewModel.navigateToRate() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultViewModel.navigation.observe(viewLifecycleOwner, Observer(::navigate))
        resultViewModel.showingAds.observe(viewLifecycleOwner, Observer(::loadAd))
    }

    private fun loadAd(model: ResultViewModel.UiModel) {
        /*  if (model is ResultViewModel.UiModel.ShowAd)
              (activity as ResultActivity).showAd(model.show)*/
    }

    private fun navigate(navigation: ResultViewModel.Navigation?) {
        when (navigation) {
            ResultViewModel.Navigation.Rate -> rateApp(requireContext())
            ResultViewModel.Navigation.Game -> activity?.finishAfterTransition()
            is ResultViewModel.Navigation.Share -> shareApp(navigation.points, requireContext())
            is ResultViewModel.Navigation.Open -> openAppOnPlayStore(requireContext(), navigation.url)
        }
    }
}