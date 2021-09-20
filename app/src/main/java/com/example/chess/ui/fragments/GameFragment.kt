package com.example.chess.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chess.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var listener: IGameListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGameBinding.inflate(inflater, container, false)


//        binding.chessBoard.setOnClickListener {
//            Toast.makeText(context, "Board on click", Toast.LENGTH_SHORT).show()
//        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as IGameListener
    }

    interface IGameListener{

    }
}