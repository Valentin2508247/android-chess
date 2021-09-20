package com.example.chess.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chess.databinding.FragmentJoinGameBinding
import com.example.chess.models.OpenGame
import com.example.chess.ui.adapters.OpenGameListener
import com.example.chess.ui.adapters.OpenGamesAdapter
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class JoinGameFragment : Fragment(), OpenGameListener {
    private var _binding: FragmentJoinGameBinding? = null
    private val binding get() = _binding!!

    private val db = Firebase.firestore
    private var registration: ListenerRegistration? = null

    private val mAdapter = OpenGamesAdapter(this)
    private lateinit var mLayoutManager: LinearLayoutManager

    private lateinit var listener: MainFragment.IMainListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJoinGameBinding.inflate(inflater, container, false)

        setupRecyclerView()
        listenGames()

        return binding.root
    }

    private fun listenGames() {
        registration = db.collection("games")
            .addSnapshotListener { value, e ->
                Toast.makeText(context, "Firestore listener", Toast.LENGTH_LONG).show()
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    // TODO: offer user to reload query
                    return@addSnapshotListener
                }

                val games = arrayListOf<OpenGame>()
                for (doc in value!!) {
                    val game = OpenGame(
                        id = doc.id,
                        creator = doc.getString("creator")?: "Not found",
                        iconUrl = doc.getString("icon"))
                    games.add(game)
                    Log.d(TAG, game.toString())
                }
                mAdapter.submitList(games)

            }
    }

    private fun setupRecyclerView() {
        mLayoutManager = LinearLayoutManager(context)

        binding.rvOpenGames.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        registration?.remove()
    }

    private companion object {
        const val TAG = "JoinGameFragment"
    }
}