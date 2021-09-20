package com.example.chess.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import com.example.chess.R
import com.example.chess.databinding.ActivityMainBinding
import com.example.chess.ui.fragments.CreateGameFragment
import com.example.chess.ui.fragments.GameFragment
import com.example.chess.ui.fragments.LoginFragment
import com.example.chess.ui.fragments.MainFragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(), LoginFragment.ILoginListener, MainFragment.IMainListener,
    CreateGameFragment.ICreateGameListener, GameFragment.IGameListener {

    private lateinit var binding: ActivityMainBinding

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Firebase.auth.currentUser == null)
            doSignIn()




//        onLogin("login")
//        onGoToCreateGame()
//        onCreateGame("gameID")


    }

    private fun doSignIn() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build())

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            Log.d(TAG, "Sign in successful")
            // ...
        } else {
            Log.d(TAG, "Sign in error ${response?.error?.errorCode}")
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    private fun navigate(actionId: Int) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(actionId)
    }

    override fun onLogin(name: String) {
        Log.d(TAG, "Name: $name")
        navigate(R.id.action_loginFragment_to_mainFragment)
    }

    override fun onGoToCreateGame() {
        navigate(R.id.action_mainFragment_to_createGameFragment)
    }

    override fun onGoToJoinGame() {
        navigate(R.id.action_mainFragment_to_joinGameFragment)
    }

    override fun onCreateGame(gameId: String) {
        navigate(R.id.action_createGameFragment_to_gameFragment)
    }

    private companion object {
        const val TAG = "MainActivity2"
    }
}