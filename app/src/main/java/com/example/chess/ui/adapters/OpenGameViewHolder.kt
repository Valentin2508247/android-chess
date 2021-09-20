package com.example.chess.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chess.databinding.OpenGameItemBinding
import com.example.chess.models.OpenGame
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class OpenGameViewHolder(
    private val binding: OpenGameItemBinding,
    private val listener: OpenGameListener,
): RecyclerView.ViewHolder(binding.root) {

    fun bind(game: OpenGame) {
        binding.apply {
            tvCreator.text = game.creator
            tvId.text = game.id

//            game.iconUrl?.let {
//                Glide.with(binding.root.context)
//                    .load(game.iconUrl)
//                    .into(binding.ivIcon)
//            }

            Glide.with(binding.root.context)
                .load( Firebase.auth.currentUser?.photoUrl)
                .into(binding.ivIcon)

        }

    }
}