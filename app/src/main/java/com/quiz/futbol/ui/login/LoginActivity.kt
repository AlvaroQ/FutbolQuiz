package com.quiz.futbol.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import com.quiz.futbol.databinding.LoginActivityBinding
import com.quiz.futbol.ui.select.SelectActivity
import com.quiz.futbol.utils.Constants.User
import com.quiz.futbol.utils.log

class LoginActivity : BaseActivity() {
    private lateinit var binding: LoginActivityBinding

    lateinit var signInClient: GoogleSignInClient
    lateinit var signInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupGoogleLogin()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerLogin, LoginFragment.newInstance())
                .commitNow()
        }
    }

    override fun onStart() {
        super.onStart()
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser != null) {
            log(TAG, "user=$firebaseUser")
        }

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        log(TAG, "currentUser=$currentUser")
        val user = User(
            auth.currentUser?.uid,
            auth.currentUser?.displayName,
            auth.currentUser?.email,
            auth.currentUser?.phoneNumber,
            auth.currentUser?.photoUrl.toString())

        startActivity<SelectActivity> { putExtra(User, user) }
    }

    private fun setupGoogleLogin() {
        signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        signInClient = GoogleSignIn.getClient(this, signInOptions)
    }

    fun login() {
        val loginIntent: Intent = signInClient.signInIntent
        startActivityForResult(loginIntent, RC_SIGN_IN)
    }

    private fun googleFirebaseAuth(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                log(TAG, "googleFirebaseAuth - isSuccessfull")
                val user = User(
                        it.result.user?.uid,
                        it.result.user?.displayName,
                        it.result.user?.email,
                        it.result.user?.phoneNumber,
                        it.result.user?.photoUrl.toString())

                startActivity<SelectActivity> { putExtra(User, user) }
            } else {
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    googleFirebaseAuth(account)
                }
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        const val RC_SIGN_IN: Int = 1
        private val TAG = LoginActivity::class.java.simpleName
    }
}