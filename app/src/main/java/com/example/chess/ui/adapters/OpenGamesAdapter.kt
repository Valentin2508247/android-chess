package com.example.chess.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.chess.databinding.OpenGameItemBinding
import com.example.chess.models.OpenGame


class OpenGamesAdapter(private val listener: OpenGameListener):
    ListAdapter<OpenGame, OpenGameViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenGameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = OpenGameItemBinding.inflate(layoutInflater, parent, false)
        return OpenGameViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: OpenGameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object {
        private val itemComparator = object : DiffUtil.ItemCallback<OpenGame>() {
            override fun areItemsTheSame(oldItem: OpenGame, newItem: OpenGame): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: OpenGame, newItem: OpenGame): Boolean {
                return oldItem.creator == newItem.creator
                        && oldItem.iconUrl == newItem.iconUrl
            }
        }
    }
}