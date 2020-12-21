package com.quiz.futbol.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.quiz.domain.User
import com.quiz.futbol.R
import com.quiz.futbol.base.BaseActivity
import com.quiz.futbol.common.startActivity
import com.quiz.futbol.databinding.LoginFragmentBinding
import com.quiz.futbol.ui.select.SelectActivity
import com.quiz.futbol.utils.getByteArrayFromImageURL
import com.quiz.futbol.utils.log
import com.quiz.futbol.utils.setSafeOnClickListener
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.URL
import java.net.URLConnection

class LoginFragment : Fragment() {
    private lateinit var binding: LoginFragmentBinding
    private val loginViewModel: LoginViewModel by lifecycleScope.viewModel(this)

    lateinit var signInClient: GoogleSignInClient
    lateinit var signInOptions: GoogleSignInOptions

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater)
        val root = binding.root
        setupGoogleLogin()
        binding.btnSignInButton.setSafeOnClickListener { login() }
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel.navigation.observe(viewLifecycleOwner, Observer(::navigation))
        loginViewModel.signedInAccount.observe(viewLifecycleOwner, Observer(::signedInAccount))
    }

    private fun navigation(navigation: LoginViewModel.Navigation) {
        when(navigation) {
            LoginViewModel.Navigation.Select -> activity?.startActivity<SelectActivity> {}
        }
    }

    private fun signedInAccount(uiModels: LoginViewModel.UiModel?) {
        when(uiModels) {
            is LoginViewModel.UiModel.GoogleFirebaseAuth -> googleFirebaseAuth(uiModels.account)
        }
    }

    private fun setupGoogleLogin() {
        signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        signInClient = GoogleSignIn.getClient(requireActivity(), signInOptions)
    }

    private fun login() {
        val loginIntent: Intent = signInClient.signInIntent
        startActivityForResult(loginIntent, RC_SIGN_IN)
    }

    private fun googleFirebaseAuth(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        (activity as BaseActivity).auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                log(TAG, "googleFirebaseAuth - isSuccessfull")
                val user = User(
                    it.result.user?.uid,
                    it.result.user?.displayName,
                    "",
                    it.result.user?.email,
                    it.result.user?.phoneNumber,
                    getByteArrayFromImageURL(it.result.user?.photoUrl.toString()),
                    System.currentTimeMillis(),
                    0
                )
                loginViewModel.saveUserSignIn(user)
                loginViewModel.navigationToSelectScreen()
            } else {
                Toast.makeText(requireContext(), "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    loginViewModel.googleFirebaseAuth(account)
                }
            } catch (e: ApiException) {
                Toast.makeText(requireContext(), "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(loginViewModel.getUid() == "") {
            val firebaseUser = FirebaseAuth.getInstance().currentUser
            if (firebaseUser != null) {
                log(TAG, "onStart user=${firebaseUser.uid}")

                loginViewModel.loginSucess(firebaseUser.uid)
            } else {
                binding.layoutDialogLogin.visibility = View.VISIBLE
            }
        } else {
            loginViewModel.navigationToSelectScreen()
        }
    }



    companion object {
        private val TAG = LoginFragment::class.java.simpleName
        const val RC_SIGN_IN: Int = 1
        fun newInstance() = LoginFragment()
    }
}