package com.example.chess.ui.customViews.logic

import android.util.Log
import com.example.chess.ui.customViews.piece.*
import com.example.chess.ui.customViews.ChessBoard

class BoardAdapter(val board: ChessBoard) {
    private val TAG = "BoardAdapter"
    private val field: Array<Array<Tile>> = Array(8) { i -> Array(8, ) { j -> Tile(j - 1, i - 1)} }
    var whitePieces: MutableList<Piece> = createWhitePieces()
    var blackPieces: MutableList<Piece> = createBlackPieces()

    // input event com.example.chess.ui.customViews.logic
    var clickCount = 0
    var clickedColumn = 0
    var clickedRow = 0


    init {
        placePieces(whitePieces)
        placePieces(blackPieces)
//        for (piece in whitePieces)
//            field[piece.y][piece.x].piece = piece
//        for (piece in blackPieces)
//            field[piece.y][piece.x].piece = piece
    }


    private fun placePieces(list: List<Piece>) {
        for (piece in list)
            field[piece.y][piece.x].piece = piece
    }

    private fun createBlackPieces(): MutableList<Piece>{
        val result = ArrayList<Piece>()
        for (i in 0..7)
            result.add(Pawn(i, 1))

        result.add(Rook(0, 0))
        result.add(Rook(7, 0))
        result.add(Knight(1, 0))
        result.add(Knight(6, 0))
        result.add(Bishop(2, 0))
        result.add(Bishop(5, 0))
        result.add(Queen(3, 0))
        result.add(King(4, 0))

        return result
    }

    private fun createWhitePieces(): MutableList<Piece>{
        val result = ArrayList<Piece>()
        for (i in 0..7)
            result.add(Pawn(i, 6))

        result.add(Rook(0, 7))
        result.add(Rook(7, 7))
        result.add(Knight(1, 7))
        result.add(Knight(6, 7))
        result.add(Bishop(2, 7))
        result.add(Bishop(5, 7))
        result.add(Queen(3, 7))
        result.add(King(4, 7))

        return result
    }

    fun click(column: Int, row: Int) {
                        clickCount++
                if (clickCount == 2) {
                    Log.d(TAG, "Move from {$clickedRow, $clickedColumn} to {$row, $column}")
                    makeMove(clickedColumn, clickedRow, column, row)
                    clickCount = 0
                }
                else {
                    clickedColumn = column
                    clickedRow = row
                }
    }

    private fun makeMove(x1: Int, y1: Int, x2: Int, y2: Int) {
        val from = field[y1][x1]
        val to = field[y2][x2]

        val fromPiece = from.piece
        fromPiece?.let { movedPiece ->
            movedPiece.x = x2
            movedPiece.y = y2
            to.piece?.let {
                whitePieces.remove(it)
                blackPieces.remove(it)
            }

            to.piece = from.piece

            from.piece = null
            // redraw
            board.invalidate()

        }
    }
}