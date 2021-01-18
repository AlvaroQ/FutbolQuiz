package com.quiz.futbol.ui.game

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
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
import com.quiz.futbol.utils.Constants.COUNTER_DOWN_DEFAULT
import com.quiz.futbol.utils.Constants.ModeGame
import com.quiz.futbol.utils.Constants.TOTAL_TEAMS_ENGLAND_FIRST_DIVISION
import com.quiz.futbol.utils.Constants.TOTAL_TEAMS_ITALY_FIRST_DIVISION
import com.quiz.futbol.utils.Constants.TOTAL_TEAMS_SPAIN_FIRST_DIVISION
import com.quiz.futbol.utils.Constants.TypeGame
import com.quiz.futbol.utils.Constants.TypeChampionship
import kotlinx.coroutines.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel
import java.util.*
import java.util.concurrent.TimeUnit


class GameFragment : Fragment() {
    private val gameViewModel: GameViewModel by lifecycleScope.viewModel(this)
    private lateinit var binding: FragmentGameBinding

    private lateinit var layoutOptionOne: ConstraintLayout
    private lateinit var layoutOptionTwo: ConstraintLayout
    private lateinit var layoutOptionThree: ConstraintLayout
    private lateinit var layoutOptionFour: ConstraintLayout

    private var life: Int = 2
    private var stage: Int = 1
    private var points: Int = 0

    lateinit var typeGame: String
    lateinit var typeChampionship: String
    private lateinit var modeGame: String

    var cTimer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentGameBinding.inflate(inflater)
        val root = binding.root

        typeGame = GameFragmentArgs.fromBundle(requireArguments()).typeGame
        typeChampionship = GameFragmentArgs.fromBundle(requireArguments()).typeChampionship
        modeGame = GameFragmentArgs.fromBundle(requireArguments()).typeMode

        when(modeGame) {
            ModeGame.TRAINNIG.name -> drawTrainingMode()
            ModeGame.CARRER.name -> drawCarrerMode()
        }

        gameViewModel.generateNewStage(typeChampionship)

        binding.textTitle.text = when(typeGame) {
            TypeGame.BY_NAME.name -> getString(R.string.stadium_by_name)
            TypeGame.BY_IMAGE.name -> getString(R.string.stadium_by_image)
            TypeGame.BY_BUILT.name -> getString(R.string.stadium_by_built)
            else -> getString(R.string.stadium_by_capacity)
        }

        binding.appBarLayoutGame.btnBack.setSafeOnClickListener { activity?.onBackPressed() }

        layoutOptionOne = binding.layoutButtonOne.layoutOption
        layoutOptionTwo = binding.layoutButtonTwo.layoutOption
        layoutOptionThree = binding.layoutButtonThree.layoutOption
        layoutOptionFour = binding.layoutButtonFour.layoutOption

        layoutOptionOne.setSafeOnClickListener {
            layoutOptionOne.isSelected = !layoutOptionOne.isSelected
            checkResponse()
        }

        layoutOptionTwo.setSafeOnClickListener {
            layoutOptionTwo.isSelected = !layoutOptionTwo.isSelected
            checkResponse()
        }

        layoutOptionThree.setSafeOnClickListener {
            layoutOptionThree.isSelected = !layoutOptionThree.isSelected
            checkResponse()
        }

        layoutOptionFour.setSafeOnClickListener {
            layoutOptionFour.isSelected = !layoutOptionFour.isSelected
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
        binding.appBarLayoutGame.layoutCounter.visibility = View.INVISIBLE
        binding.appBarLayoutGame.layoutLife.visibility = View.VISIBLE
    }

    private fun drawCarrerMode() {
        binding.appBarLayoutGame.toolbarTitle.visibility = View.INVISIBLE
        binding.appBarLayoutGame.layoutCounter.visibility = View.VISIBLE
        binding.appBarLayoutGame.layoutLife.visibility = View.INVISIBLE
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
                val action = GameFragmentDirections.actionNavigationGameToResult(
                    points.toString(),
                    modeGame,
                    typeGame,
                    typeChampionship
                )
                findNavController().navigate(action)
            }
        }
    }

    private fun updateProgress(model: GameViewModel.UiModel?) {
        if (model is GameViewModel.UiModel.Loading && model.show) {
            glideLoadingGif(requireContext(), binding.imageLoading)
            binding.imageLoading.visibility = View.VISIBLE
            binding.textQuiz.visibility = View.INVISIBLE
            binding.imageQuiz.visibility = View.INVISIBLE

            layoutOptionOne.isSelected = false
            layoutOptionTwo.isSelected = false
            layoutOptionThree.isSelected = false
            layoutOptionFour.isSelected = false

            enableBtn(false)
        } else {
            binding.imageLoading.visibility = View.INVISIBLE
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
            TypeGame.BY_NAME.name -> binding.textQuiz.text = Locale(
                getString(R.string.locale),
                stadium.name!!
            ).displayCountry
            TypeGame.BY_IMAGE.name -> glideLoadURL(
                requireContext(),
                stadium.stadium_image,
                binding.imageQuiz
            )
            TypeGame.BY_BUILT.name -> glideLoadURL(
                requireContext(),
                stadium.stadium_image,
                binding.imageQuiz
            )
            TypeGame.BY_CAPACITY.name -> glideLoadURL(
                requireContext(),
                stadium.stadium_image,
                binding.imageQuiz
            )
        }
    }

    private fun drawOptionsResponse(optionsListByPos: MutableList<Stadium>) {
        var delay = 150L
        if(stage == 1) {
            delay = 0L
            binding.containerButtons.traslationAnimationFadeIn()
        }
        else binding.containerButtons.traslationAnimation()

        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.MILLISECONDS.toMillis(delay))
            withContext(Dispatchers.Main) {
                when(typeGame) {
                    TypeGame.BY_NAME.name -> drawImageResponse(optionsListByPos)
                    TypeGame.BY_IMAGE.name -> drawTextResponse(optionsListByPos)
                    TypeGame.BY_BUILT.name -> drawBuiltResponse(optionsListByPos)
                    TypeGame.BY_CAPACITY.name -> drawCapacityResponse(optionsListByPos)
                }
            }
        }
    }

    private fun drawTextResponse(optionsListByPos: MutableList<Stadium>) {
        layoutOptionOne.id = optionsListByPos[0].id
        binding.layoutButtonOne.textOption.text = optionsListByPos[0].name
        binding.layoutButtonOne.cardOption.visibility = View.GONE

        layoutOptionTwo.id = optionsListByPos[1].id
        binding.layoutButtonTwo.textOption.text = optionsListByPos[1].name
        binding.layoutButtonTwo.cardOption.visibility = View.GONE

        layoutOptionThree.id = optionsListByPos[2].id
        binding.layoutButtonThree.textOption.text = optionsListByPos[2].name
        binding.layoutButtonThree.cardOption.visibility = View.GONE

        layoutOptionFour.id = optionsListByPos[3].id
        binding.layoutButtonFour.textOption.text = optionsListByPos[3].name
        binding.layoutButtonFour.cardOption.visibility = View.GONE
    }

    private fun drawImageResponse(optionsListByPos: MutableList<Stadium>) {
        layoutOptionOne.id = optionsListByPos[0].id
        glideLoadURL(requireContext(), optionsListByPos[0].stadium_image, binding.layoutButtonOne.imageOption)

        layoutOptionTwo.id = optionsListByPos[1].id
        glideLoadURL(requireContext(), optionsListByPos[1].stadium_image, binding.layoutButtonTwo.imageOption)

        layoutOptionThree.id = optionsListByPos[2].id
        glideLoadURL(requireContext(), optionsListByPos[2].stadium_image, binding.layoutButtonThree.imageOption)

        layoutOptionFour.id = optionsListByPos[3].id
        glideLoadURL(requireContext(), optionsListByPos[3].stadium_image, binding.layoutButtonFour.imageOption)
    }

    private fun drawBuiltResponse(optionsListByPos: MutableList<Stadium>) {
        layoutOptionOne.id = optionsListByPos[0].id
        binding.layoutButtonOne.textOption.text = setStringNumberWithThousandSeparator(optionsListByPos[0].build!!)
        binding.layoutButtonOne.cardOption.visibility = View.GONE

        layoutOptionTwo.id = optionsListByPos[1].id
        binding.layoutButtonTwo.textOption.text = setStringNumberWithThousandSeparator(optionsListByPos[1].build!!)
        binding.layoutButtonTwo.cardOption.visibility = View.GONE

        layoutOptionThree.id = optionsListByPos[2].id
        binding.layoutButtonThree.textOption.text = setStringNumberWithThousandSeparator(optionsListByPos[2].build!!)
        binding.layoutButtonThree.cardOption.visibility = View.GONE

        layoutOptionFour.id = optionsListByPos[3].id
        binding.layoutButtonFour.textOption.text = setStringNumberWithThousandSeparator(optionsListByPos[3].build!!)
        binding.layoutButtonFour.cardOption.visibility = View.GONE
    }

    private fun drawCapacityResponse(optionsListByPos: MutableList<Stadium>) {
        layoutOptionOne.id = optionsListByPos[0].id
        binding.layoutButtonOne.textOption.text = setStringNumberWithThousandSeparator(optionsListByPos[0].capacity!!)
        binding.layoutButtonOne.cardOption.visibility = View.GONE

        layoutOptionTwo.id = optionsListByPos[1].id
        binding.layoutButtonTwo.textOption.text = setStringNumberWithThousandSeparator(optionsListByPos[1].capacity!!)
        binding.layoutButtonTwo.cardOption.visibility = View.GONE

        layoutOptionThree.id = optionsListByPos[2].id
        binding.layoutButtonThree.textOption.text = setStringNumberWithThousandSeparator(optionsListByPos[2].capacity!!)
        binding.layoutButtonThree.cardOption.visibility = View.GONE

        layoutOptionFour.id = optionsListByPos[3].id
        binding.layoutButtonFour.textOption.text = setStringNumberWithThousandSeparator(optionsListByPos[3].capacity!!)
        binding.layoutButtonFour.cardOption.visibility = View.GONE
    }

    private fun checkResponse() {
        enableBtn(false)
        stage += 1

        drawCorrectResponse(gameViewModel.getStadium())
        nextScreen()
    }

    private fun deleteLife() {
        if(modeGame == ModeGame.TRAINNIG.name) {
            life--
            writeDeleteLife(life)
        }
    }

    private fun drawCorrectItemResponse(itemLayout: ConstraintLayout) {
        MediaPlayer.create(context, R.raw.success).start()
        points += 1
        itemLayout.background =  ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_correct)
    }

    private fun drawFailItemResponse(itemlayout: ConstraintLayout) {
        MediaPlayer.create(context, R.raw.fail).start()
        deleteLife()
        itemlayout.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_radius_wrong)
    }

    private fun drawCorrectResponse(stadiumCorrect: Stadium?) {
        if(stadiumCorrect != null) {
            when {
                layoutOptionOne.id == stadiumCorrect.id -> {
                    when {
                        layoutOptionOne.isSelected -> drawCorrectItemResponse(layoutOptionOne)
                        layoutOptionTwo.isSelected -> drawFailItemResponse(layoutOptionTwo)
                        layoutOptionThree.isSelected -> drawFailItemResponse(layoutOptionThree)
                        layoutOptionFour.isSelected -> drawFailItemResponse(layoutOptionFour)
                    }
                }
                layoutOptionTwo.id == stadiumCorrect.id -> {
                    when {
                        layoutOptionOne.isSelected -> drawFailItemResponse(layoutOptionOne)
                        layoutOptionTwo.isSelected -> drawCorrectItemResponse(layoutOptionTwo)
                        layoutOptionThree.isSelected -> drawFailItemResponse(layoutOptionThree)
                        layoutOptionFour.isSelected -> drawFailItemResponse(layoutOptionFour)
                    }
                }
                layoutOptionThree.id == stadiumCorrect.id -> {
                    when {
                        layoutOptionOne.isSelected -> drawFailItemResponse(layoutOptionOne)
                        layoutOptionTwo.isSelected -> drawFailItemResponse(layoutOptionTwo)
                        layoutOptionThree.isSelected -> drawCorrectItemResponse(layoutOptionThree)
                        layoutOptionFour.isSelected -> drawFailItemResponse(layoutOptionFour)
                    }
                }
                layoutOptionFour.id == stadiumCorrect.id -> {
                    when {
                        layoutOptionOne.isSelected -> drawFailItemResponse(layoutOptionOne)
                        layoutOptionTwo.isSelected -> drawFailItemResponse(layoutOptionTwo)
                        layoutOptionThree.isSelected -> drawFailItemResponse(layoutOptionThree)
                        layoutOptionFour.isSelected -> drawCorrectItemResponse(layoutOptionFour)
                    }
                }
            }
        }
    }

    private fun enableBtn(isEnable: Boolean) {
        layoutOptionOne.isClickable = isEnable
        layoutOptionTwo.isClickable = isEnable
        layoutOptionThree.isClickable = isEnable
        layoutOptionFour.isClickable = isEnable

        if(isEnable) {
            layoutOptionOne.background = ContextCompat.getDrawable(requireContext(), R.drawable.selector_with_radius_button)
            layoutOptionTwo.background = ContextCompat.getDrawable(requireContext(), R.drawable.selector_with_radius_button)
            layoutOptionThree.background = ContextCompat.getDrawable(requireContext(), R.drawable.selector_with_radius_button)
            layoutOptionFour.background = ContextCompat.getDrawable(requireContext(), R.drawable.selector_with_radius_button)
        }
    }

    private fun nextScreen() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.MILLISECONDS.toMillis(1000))
            withContext(Dispatchers.Main) {
                when(typeChampionship) {
                    TypeChampionship.SPAIN_FIRST_DIVISION.name -> {
                        if(stage > TOTAL_TEAMS_SPAIN_FIRST_DIVISION || life < 1) gameViewModel.navigateToResult(points.toString())
                        else gameViewModel.generateNewStage(typeChampionship)
                    }
                    TypeChampionship.ENGLAND_FIRST_DIVISION.name -> {
                        if(stage > TOTAL_TEAMS_ENGLAND_FIRST_DIVISION || life < 1) gameViewModel.navigateToResult(points.toString())
                        else gameViewModel.generateNewStage(typeChampionship)
                    }
                    TypeChampionship.ITALY_FIRST_DIVISION.name -> {
                        if(stage > TOTAL_TEAMS_ITALY_FIRST_DIVISION || life < 1) gameViewModel.navigateToResult(points.toString())
                        else gameViewModel.generateNewStage(typeChampionship)
                    }
                }
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
                binding.appBarLayoutGame.lifeSecond.setImageDrawable(context?.getDrawable(R.drawable.ic_life_off))
                binding.appBarLayoutGame.lifeFirst.setImageDrawable(context?.getDrawable(R.drawable.ic_life_off))
            }
        }
    }

    companion object {
        private val TAG = GameFragment::class.java.simpleName
    }
}
