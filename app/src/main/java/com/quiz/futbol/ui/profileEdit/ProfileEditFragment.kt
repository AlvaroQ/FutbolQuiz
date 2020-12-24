package com.quiz.futbol.ui.profileEdit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.dhaval2404.imagepicker.ImagePicker
import com.quiz.futbol.databinding.EditProfileFragmentBinding
import com.quiz.futbol.utils.getBase64FromBitmap
import com.quiz.futbol.utils.glideLoadBase64
import com.quiz.futbol.utils.setSafeOnClickListener
import com.quiz.futbol.utils.toBase64
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel


class ProfileEditFragment : Fragment() {

    private lateinit var binding: EditProfileFragmentBinding
    private val profileEditViewModel: ProfileEditViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditProfileFragmentBinding.inflate(inflater)
        val root = binding.root

        binding.imageUser.setSafeOnClickListener {
            profileEditViewModel.clickOnPicker()
        }

        binding.btnSubmit.setSafeOnClickListener {
            profileEditViewModel.updateUserData(
                binding.nameEdit.text.toString(),
                binding.descriptionEdit.text.toString(),
                binding.locationEdit.text.toString(),
                getBase64FromBitmap(binding.imageUser.drawable.toBitmap()))
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileEditViewModel.apply {
            userData.observe(viewLifecycleOwner, Observer(::updateUi))
            nav.observe(viewLifecycleOwner, ::navigate)
        }
    }

    private fun navigate(nav: ProfileEditViewModel.ProfileNavigation) {
        when (nav) {
            ProfileEditViewModel.ProfileNavigation.PickerNavigation -> {
                ImagePicker.with(this)
                    .crop()
                    .compress(maxSize = 1024)
                    .maxResultSize(width = 1080, height = 1080)
                    .start()
            }
            else -> activity?.finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                glideLoadBase64(
                    requireContext(),
                    ImagePicker.getFile(data)?.toBase64(),
                    binding.imageUser
                )
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun updateUi(model: ProfileEditViewModel.UiModel) {
        when (model) {
            is ProfileEditViewModel.UiModel.UserPersonalData -> {
                binding.nameEdit.setText(model.user.displayName)
                binding.descriptionEdit.setText(model.user.displayDescription)
                binding.locationEdit.setText(model.user.displayLocation)
                glideLoadBase64(requireContext(), model.user.photoBase64, binding.imageUser)
            }
        }
    }

    companion object {
        private val TAG = ProfileEditFragment::class.java.simpleName
        fun newInstance() = ProfileEditFragment()
    }
}