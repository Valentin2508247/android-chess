package com.example.chess.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chess.databinding.FragmentCreateGameBinding

class CreateGameFragment : Fragment() {

    private var _binding: FragmentCreateGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var listener: ICreateGameListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateGameBinding.inflate(inflater, container, false)
        initListeners()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ICreateGameListener
    }

    private fun initListeners() {
        binding.btStartGame.setOnClickListener {
            listener.onCreateGame("gameId")
        }
    }

    interface ICreateGameListener {
        fun onCreateGame(gameId: String)
    }
}