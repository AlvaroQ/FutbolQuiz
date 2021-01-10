package com.quiz.futbol.ui.game

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.quiz.domain.Stadium
import com.quiz.futbol.R
import com.quiz.futbol.common.traslationAnimation
import com.quiz.futbol.common.traslationAnimationFadeIn
import com.quiz.futbol.databinding.FragmentGameBinding
import com.quiz.futbol.utils.*
import com.quiz.futbol.utils.Constants.TOTAL_TEAMS_SPAIN_FIRST_DIVISION
import com.quiz.futbol.utils.Constants.TypeGame
import com.quiz.futbol.utils.Constants.ModeGame
import com.quiz.futbol.utils.Constants.COUNTER_DOWN_DEFAULT
import kotlinx.coroutines.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel
import java.util.*
import java.util.concurrent.TimeUnit


class GameFragment : Fragment() {
    private val gameViewModel: GameViewModel by lifecycleScope.viewModel(this)
    private lateinit var binding: FragmentGameBinding

    lateinit var btnOptionOne: TextView
    lateinit var btnOptionTwo: TextView
    lateinit var btnOptionThree: TextView
    lateinit var btnOptionFour: TextView

    private var life: Int = 2
    private var stage: Int = 1
    private var points: Int = 0

    lateinit var typeGame: String
    lateinit var typeChampionship: String
    lateinit var modeGame: String

    var cTimer: CountDownTimer? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentGameBinding.inflate(inflater)
        val root = binding.root

        typeGame = GameFragmentArgs.fromBundle(requireArguments()).typeGame
        typeChampionship = GameFragmentArgs.fromBundle(requireArguments()).typeChampionship
        modeGame = GameFragmentArgs.fromBundle(requireArguments()).typeMode

        when(modeGame) {
            ModeGame.TRAINNIG.name -> drawTrainingMode()
            ModeGame.CARRER.name -> drawCarrerMode()
        }


        binding.appBarLayoutGame.btnBack.setSafeOnClickListener { activity?.onBackPressed() }

        btnOptionOne = root.findViewById(R.id.btnOptionOne)
        btnOptionTwo = root.findViewById(R.id.btnOptionTwo)
        btnOptionThree = root.findViewById(R.id.btnOptionThree)
        btnOptionFour = root.findViewById(R.id.btnOptionFour)

        btnOptionOne.setSafeOnClickListener {
            btnOptionOne.isSelected = !btnOptionOne.isSelected
            checkResponse()
        }

        btnOptionTwo.setSafeOnClickListener {
            btnOptionTwo.isSelected = !btnOptionTwo.isSelected
            checkResponse()
        }

        btnOptionThree.setSafeOnClickListener {
            btnOptionThree.isSelected = !btnOptionThree.isSelected
            checkResponse()
        }

        btnOptionFour.setSafeOnClickListener {
            btnOptionFour.isSelected = !btnOptionFour.isSelected
            checkResponse()
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameViewModel.navigation.observe(viewLifecycleOwner, Observer(::navigate))
        gameViewModel.progress.observe(viewLifecycleOwner, Observer(::updateProgress))
        gameViewModel.question.observe(viewLifecycleOwner, Observer(::drawQuestionQuiz))
        gameViewModel.responseOptions.observe(viewLifecycleOwner, Observer(::drawOptionsResponse))
    }

    private fun drawTrainingMode() {
        binding.appBarLayoutGame.toolbarTitle.text = getString(R.string.training)
        binding.appBarLayoutGame.layoutCounter.visibility = View.GONE
        binding.appBarLayoutGame.layoutLife.visibility = View.VISIBLE
    }

    private fun drawCarrerMode() {
        binding.appBarLayoutGame.toolbarTitle.visibility = View.GONE
        binding.appBarLayoutGame.layoutCounter.visibility = View.VISIBLE
        binding.appBarLayoutGame.layoutLife.visibility = View.GONE
        gameViewModel.setVerificationSent(System.currentTimeMillis())
        startCountDown()
    }

    private fun startCountDown() {
        val timeSinceVerificationSent = System.currentTimeMillis() - gameViewModel.getVerificationSent()
        if(cTimer == null && timeSinceVerificationSent < COUNTER_DOWN_DEFAULT) {
            cTimer = object : CountDownTimer(COUNTER_DOWN_DEFAULT - timeSinceVerificationSent, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.appBarLayoutGame.tvTimer.text = (millisUntilFinished / 1000).toString()
                }

                override fun onFinish() {
                    life = 0
                    nextScreen()
                }
            }
            (cTimer as CountDownTimer).start()
        } else {
            cancelTimer()
        }
    }

    private fun cancelTimer() {
        binding.appBarLayoutGame.tvTimer.text = ""
        cTimer = with(cTimer) {
            this?.cancel()
            null
        }
    }
    private fun navigate(navigation: GameViewModel.Navigation?) {
        log(TAG, "navigate to $navigation")
        when (navigation) {
            is GameViewModel.Navigation.Result -> {
                val action = GameFragmentDirections.actionNavigationGameToResult(points.toString(), modeGame, typeGame, typeChampionship)
                findNavController().navigate(action)
            }
        }
    }

    private fun updateProgress(model: GameViewModel.UiModel?) {
        if (model is GameViewModel.UiModel.Loading && model.show) {
            glideLoadingGif(requireContext(), binding.imageLoading)
            binding.imageLoading.visibility = View.VISIBLE
            binding.textQuiz.visibility = View.GONE
            binding.imageQuiz.visibility = View.GONE

            btnOptionOne.isSelected = false
            btnOptionTwo.isSelected = false
            btnOptionThree.isSelected = false
            btnOptionFour.isSelected = false

            enableBtn(false)
        } else {
            binding.imageLoading.visibility = View.GONE
            binding.textQuiz.visibility = View.VISIBLE
            binding.imageQuiz.visibility = View.VISIBLE

            enableBtn(true)
        }
    }

    private fun drawQuestionQuiz(stadium: Stadium) {
        log(tag, "drawQuestionQuiz")
        log(tag, "typeGame = $typeGame")
        log(tag, "imageQuiz = " + stadium.stadium_image)
        gameViewModel.setVerificationSent(System.currentTimeMillis())
        when(typeGame) {
            TypeGame.BY_NAME.name -> binding.textQuiz.text = Locale(getString(R.string.locale), stadium.name!!).displayCountry
            TypeGame.BY_IMAGE.name -> glideLoadURL(requireContext(), stadium.stadium_image, binding.imageQuiz)
        }
    }

    private fun drawOptionsResponse(optionsListByPos: MutableList<String>) {
        var delay = 150L
        if(stage == 1) {
            delay = 0L
            binding.containerButtons.traslationAnimationFadeIn()
        }
        else binding.containerButtons.traslationAnimation()

        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.MILLISECONDS.toMillis(delay))
            withContext(Dispatchers.Main) {
                btnOptionOne.text = optionsListByPos[0]
                btnOptionTwo.text = optionsListByPos[1]
                btnOptionThree.text = optionsListByPos[2]
                btnOptionFour.text = optionsListByPos[3]
            }
        }
    }

    private fun checkResponse() {
        enableBtn(false)
        stage += 1

        drawCorrectResponse(gameViewModel.getStadium().name!!)
        nextScreen()
    }

    private fun deleteLife() {
        if(modeGame == ModeGame.TRAINNIG.name) {
            life--
            writeDeleteLife(life)
        }
    }

    private fun drawCorrectResponse(capitalNameCorrect: String) {
        when {
            btnOptionOne.text == capitalNameCorrect -> {
                btnOptionOne.background =  ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_correct)
                when {
                    btnOptionOne.isSelected -> {
                        MediaPlayer.create(context, R.raw.success).start()
                        points += 1
                    }
                    btnOptionTwo.isSelected -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                        btnOptionTwo.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_wrong)
                    }
                    btnOptionThree.isSelected -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                        btnOptionThree.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_wrong)
                    }
                    btnOptionFour.isSelected -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                        btnOptionFour.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_wrong)
                    }
                    else -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                    }
                }
            }
            btnOptionTwo.text == capitalNameCorrect -> {
                btnOptionTwo.background =  ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_correct)
                when {
                    btnOptionOne.isSelected -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                        btnOptionOne.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_wrong)
                    }
                    btnOptionTwo.isSelected -> {
                        MediaPlayer.create(context, R.raw.success).start()
                        points += 1
                    }
                    btnOptionThree.isSelected -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                        btnOptionThree.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_wrong)
                    }
                    btnOptionFour.isSelected -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                        btnOptionFour.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_wrong)
                    }
                    else -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                    }
                }
            }
            btnOptionThree.text == capitalNameCorrect -> {
                btnOptionThree.background =  ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_correct)
                when {
                    btnOptionOne.isSelected -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                        btnOptionOne.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_wrong)
                    }
                    btnOptionTwo.isSelected -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                        btnOptionTwo.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_wrong)
                    }
                    btnOptionThree.isSelected -> {
                        MediaPlayer.create(context, R.raw.success).start()
                        points += 1
                    }
                    btnOptionFour.isSelected -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                        btnOptionFour.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_wrong)
                    }
                    else -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                    }
                }
            }
            btnOptionFour.text == capitalNameCorrect -> {
                btnOptionFour.background =  ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_correct)
                when {
                    btnOptionOne.isSelected -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                        btnOptionOne.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_wrong)
                    }
                    btnOptionTwo.isSelected -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                        btnOptionTwo.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_wrong)
                    }
                    btnOptionThree.isSelected -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                        btnOptionThree.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_wrong)
                    }
                    btnOptionFour.isSelected -> {
                        MediaPlayer.create(context, R.raw.success).start()
                        points += 1
                    }
                    else -> {
                        MediaPlayer.create(context, R.raw.fail).start()
                        deleteLife()
                    }
                }
            }
        }
    }

    private fun enableBtn(isEnable: Boolean) {
        btnOptionOne.isClickable = isEnable
        btnOptionTwo.isClickable = isEnable
        btnOptionThree.isClickable = isEnable
        btnOptionFour.isClickable = isEnable

        if(isEnable) {
            btnOptionOne.background = ContextCompat.getDrawable(requireContext(), R.drawable.selector_with_radius_button)
            btnOptionTwo.background = ContextCompat.getDrawable(requireContext(), R.drawable.selector_with_radius_button)
            btnOptionThree.background = ContextCompat.getDrawable(requireContext(), R.drawable.selector_with_radius_button)
            btnOptionFour.background = ContextCompat.getDrawable(requireContext(), R.drawable.selector_with_radius_button)
        }
    }

    private fun nextScreen() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.MILLISECONDS.toMillis(1000))
            withContext(Dispatchers.Main) {
                if(stage > TOTAL_TEAMS_SPAIN_FIRST_DIVISION || life < 1) gameViewModel.navigateToResult(points.toString())
                else gameViewModel.generateNewStage()
            }
        }
    }

    private fun writeDeleteLife(life: Int) {
        when(life) {
            2 -> {
                binding.appBarLayoutGame.lifeSecond.setImageDrawable(context?.getDrawable(R.drawable.ic_life_on))
                binding.appBarLayoutGame.lifeFirst.setImageDrawable(context?.getDrawable(R.drawable.ic_life_on))
            }
            1 -> {
                binding.appBarLayoutGame.lifeSecond.startAnimation(AnimationUtils.loadAnimation(context, R.anim.scale_xy_collapse))

                binding.appBarLayoutGame.lifeSecond.setImageDrawable(context?.getDrawable(R.drawable.ic_life_off))
                binding.appBarLayoutGame.lifeFirst.setImageDrawable(context?.getDrawable(R.drawable.ic_life_on))
            }
            0 -> {
                binding.appBarLayoutGame.lifeFirst.startAnimation(AnimationUtils.loadAnimation(context, R.anim.scale_xy_collapse))

                // GAME OVER
                binding.appBarLayoutGame.lifeSecond.setImageDrawable(context?.getDrawable(R.drawable.ic_life_off))
                binding.appBarLayoutGame.lifeFirst.setImageDrawable(context?.getDrawable(R.drawable.ic_life_off))
            }
        }
    }


    companion object {
        private val TAG = GameFragment::class.java.simpleName
    }
}
