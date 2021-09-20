package com.example.chess.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chess.databinding.FragmentLoginBinding
import com.example.chess.extensions.clearError
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var listener: ILoginListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        initListeners()

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ILoginListener
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListeners() {
        binding.btLogin.setOnClickListener {
            val db = Firebase.firestore
            val game = hashMapOf(
                "id" to binding.textInputEditText.text.toString(),
                "creator" to "Valentin"
            )
            db.collection("games")
                .add(game)
                .addOnSuccessListener { reference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${reference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }


//            if (validateInput()) {
//                binding.textInputLayout.clearError()
//                listener.onLogin(binding.textInputEditText.text.toString())
//            }
//            else
//                binding.textInputLayout.callError("Need 4 or more characters")
        }

        binding.clLogin.setOnClickListener {

            binding.textInputLayout.clearError()
        }
    }

    private fun validateInput(): Boolean {
        return binding.textInputEditText.text!!.length > 3
    }

    interface ILoginListener{
        fun onLogin(name: String)
    }

    private companion object {
        const val TAG = "LoginFragment"
    }
}