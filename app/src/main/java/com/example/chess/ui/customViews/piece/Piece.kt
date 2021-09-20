package com.example.chess.ui.customViews.piece

sealed class Piece(val id: Int, var x: Int, var y: Int) {

    companion object {
        private var currentId: Int = 0

        fun getNextId(): Int {
            return currentId++
        }
    }
}