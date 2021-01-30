package com.quiz.futbol.ui.result

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.quiz.data.models.ArchievementsBack
import com.quiz.futbol.R
import com.quiz.futbol.databinding.FragmentResultBinding
import com.quiz.futbol.ui.MainActivity
import com.quiz.futbol.utils.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel
import com.quiz.futbol.utils.Constants.MIN_HITS_TO_UNLOCKED
import com.quiz.futbol.utils.Constants.ModeGame
import com.quiz.futbol.utils.Constants.STAGE_COMPLETED
import com.quiz.futbol.utils.Constants.TypeGame


class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private val resultViewModel: ResultViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentResultBinding.inflate(inflater)
        val root = binding.root
        val gamePoints = ResultFragmentArgs.fromBundle(requireArguments()).points.toInt()
        val modeGame = ResultFragmentArgs.fromBundle(requireArguments()).typeMode
        val typeGame = ResultFragmentArgs.fromBundle(requireArguments()).typeGame
        val typeChampionship = ResultFragmentArgs.fromBundle(requireArguments()).typeChampionship

        if(PreferenceManager.getDefaultSharedPreferences(context).getBoolean("sound", true)) {
            MediaPlayer.create(context, R.raw.game_over).start()
        }

        // Stage passed, unlocked next level saving on firebase && show info to user
        if(modeGame == ModeGame.CARRER.name && gamePoints > MIN_HITS_TO_UNLOCKED) {
            binding.textResultStageUnlocked.text = getString(R.string.level_unlocked)

            resultViewModel.upgradeUserLevel()

            val archievementsBack = ArchievementsBack(
                typeChampionship = typeChampionship,
                typeGame = typeGame,
                points = gamePoints.toLong())
            resultViewModel.uploadArchievement(archievementsBack)

            if(typeGame == TypeGame.BY_BUILT.name) {
                val archievementsCompletedBack = ArchievementsBack(
                    typeChampionship = typeChampionship,
                    typeGame = STAGE_COMPLETED,
                    points = gamePoints.toLong())
                resultViewModel.uploadArchievement(archievementsCompletedBack)

                // Show STAGE_COMPLETED

            }
            (activity as MainActivity).loadInstersticialAd()
        }

        binding.appBarLayoutResult.toolbarTitle.text = getString(R.string.result)
        binding.textResult.text = resources.getString(R.string.successful_stadiums, gamePoints)

        binding.btnContinue.setSafeOnClickListener { resultViewModel.navigateToGame() }
        binding.btnShare.setSafeOnClickListener { resultViewModel.navigateToShare(gamePoints) }
        binding.btnRate.setSafeOnClickListener { resultViewModel.navigateToRate() }
        binding.appBarLayoutResult.btnBack.setSafeOnClickListener { activity?.onBackPressed() }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultViewModel.navigation.observe(viewLifecycleOwner, Observer(::navigate))
    }

    private fun navigate(navigation: ResultViewModel.Navigation?) {
        log(TAG, "navigate to $navigation")
        when (navigation) {
            ResultViewModel.Navigation.Rate -> rateApp(requireContext())
            ResultViewModel.Navigation.Game -> {
                findNavController().navigate(R.id.action_navigation_result_to_select)
                (activity as MainActivity).showInstersticialAd()
            }
            is ResultViewModel.Navigation.Share -> shareApp(navigation.points, requireContext())
            is ResultViewModel.Navigation.Open -> openAppOnPlayStore(requireContext(), navigation.url)
        }
    }

    companion object {
        private val TAG = ResultFragment::class.java.simpleName
    }
}