package com.example.chess.ui.customViews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.chess.R
import com.example.chess.ui.customViews.piece.*
import com.example.chess.ui.customViews.logic.*


class ChessBoard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): View(context, attrs) {
    private val TAG = "ChessBoard"


    var w = 120
    lateinit var bPawn: Bitmap
    lateinit var wPawn: Bitmap
    lateinit var bRook: Bitmap
    lateinit var wRook: Bitmap
    lateinit var wKnight: Bitmap
    lateinit var bKnight: Bitmap
    lateinit var wBishop: Bitmap
    lateinit var bBishop: Bitmap
    lateinit var wQueen: Bitmap
    lateinit var bQueen: Bitmap
    lateinit var wKing : Bitmap
    lateinit var bKing: Bitmap


    var adapter: BoardAdapter = BoardAdapter(this)
//    var clickCount = 0
//    var clickedX = 0
//    var clickedY = 0


    override fun onSizeChanged(xNew: Int, yNew: Int, xOld: Int, yOld: Int) {
        super.onSizeChanged(xNew, yNew, xOld, yOld)
        Log.d(TAG, "On size changed. xNew: $xNew yNew:$yNew xOld:$xOld yOld:$yOld")
        w = xNew / 8
        loadImages(w)
        //        viewWidth = xNew
        //        viewHeight = yNew
    }


    private fun loadImages(size: Int) {
        bPawn =  Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.b_pawn),
            w, w, true)
        wPawn =  Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.w_pawn),
            w, w, true)
        bRook = Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.b_rook),
            w, w, true)

        wRook =  Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.w_rook),
            w, w, true)
        wKnight =  Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.w_knight),
            w, w, true)
        bKnight =  Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.b_knight),
            w, w, true)
        wBishop =  Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.w_bishop),
            w, w, true)
        bBishop =  Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.b_bishop),
            w, w, true)
        wQueen =  Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.w_queen),
            w, w, true)
        bQueen =  Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.b_queen),
            w, w, true)
        wKing =  Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.w_king),
            w, w, true)
        bKing =  Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.b_king),
            w, w, true)
    }

    private val whPainter = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.FILL
        isAntiAlias = true
        strokeWidth = 30f
    }

    private val blPainter = Paint().apply {
        color = Color.DKGRAY
        style = Paint.Style.FILL
        isAntiAlias = true
        strokeWidth = 30f
    }

    override fun onDraw(canvas: Canvas) {
        canvas.apply {
            drawColor(Color.WHITE)
            val width = w.toFloat()

            // x - i, y - j
            for (j in 0..7){
                //var paint = if (j % 2 == 0) whPainter else blPainter
                for (i in 0..7){
                    val paint = if ((i + j) % 2 == 0) whPainter else blPainter
                    drawRect(width * j, i * width, width * j + width, i * width + width, paint)
                }
            }

            drawPieces(canvas)

            //drawBitmap(bRook, 0f, 0f, blPainter)

        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = 960
        var height = 960
        setMeasuredDimension(width or MeasureSpec.EXACTLY, height or MeasureSpec.EXACTLY)
    }

    private fun drawPieces(canvas: Canvas) {

        canvas.apply {
            // rooks
            for (piece in adapter.whitePieces) {
                val bmp = when(piece) {
                    is Pawn -> wPawn
                    is Knight -> wKnight
                    is Bishop -> wBishop
                    is Rook -> wRook
                    is King -> wKing
                    is Queen -> wQueen
                }
                drawBitmap(bmp, (piece.x * w).toFloat(), (piece.y * w).toFloat(), null)
            }

            for (piece in adapter.blackPieces) {
                val bmp = when(piece) {
                    is Pawn -> bPawn
                    is Knight -> bKnight
                    is Bishop -> bBishop
                    is Rook -> bRook
                    is King -> bKing
                    is Queen -> bQueen
                }
                drawBitmap(bmp, (piece.x * w).toFloat(), (piece.y * w).toFloat(), null)
            }
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)

        val eventX = event?.x
        val eventY = event?.y

        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                return true
            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG, "Up x: $eventX, y: $eventY")
                val column = eventX!!.toInt() / w
                val row = eventY!!.toInt() / w
                adapter.click(column, row)
                performClick()
                return true
            }
            MotionEvent.ACTION_CANCEL -> {

            }
            MotionEvent.ACTION_OUTSIDE -> {

            }
            else -> {
            }
        }

        return false
    }
}