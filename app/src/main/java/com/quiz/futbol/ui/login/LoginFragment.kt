package com.quiz.futbol.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.quiz.futbol.databinding.LoginFragmentBinding
import com.quiz.futbol.utils.setSafeOnClickListener
import com.quiz.futbol.utils.underline
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.quiz.futbol.common.startActivity
import com.quiz.futbol.ui.game.GameActivity
import com.quiz.futbol.utils.Constants
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class LoginFragment : Fragment() {
    private lateinit var binding: LoginFragmentBinding
    private val loginViewModel: LoginViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = LoginFragmentBinding.inflate(inflater)
        val root = binding.root
        binding.btnSignInButton.setSafeOnClickListener { (activity as LoginActivity).login() }
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel.loginWithGoogle.observe(viewLifecycleOwner, Observer(::navigate))
    }

    private fun navigate(login: LoginViewModel.UiModel) {
        if(login is LoginViewModel.UiModel.LoginSuccess) {
            activity?.startActivity<GameActivity> {}
        }
    }

    companion object {
        private val TAG = LoginFragment::class.java.simpleName
        fun newInstance() = LoginFragment()
    }
}