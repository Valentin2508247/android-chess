package com.example.chess.ui.customViews.logic

import com.example.chess.ui.customViews.piece.Piece

class Tile(val x: Int, val y: Int) {
    var piece: Piece? = null

    override fun toString(): String {
        return "${'a' + x}${8 - y}"
    }
}