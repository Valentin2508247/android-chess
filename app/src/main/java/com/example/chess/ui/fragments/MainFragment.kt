package com.example.chess.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chess.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var listener: IMainListener



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        initListeners()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as IMainListener
    }

    private fun initListeners() {
        binding.btCreateGame.setOnClickListener {
            listener.onGoToCreateGame()
        }

        binding.btJoinGame.setOnClickListener {
            listener.onGoToJoinGame()
        }
    }

    interface IMainListener {
        fun onGoToCreateGame()
        fun onGoToJoinGame()
    }
}