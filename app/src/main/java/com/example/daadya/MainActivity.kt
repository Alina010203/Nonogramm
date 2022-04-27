package com.example.daadya


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.daadya.models.Model
import com.example.daadya.views.GameView

class MainActivity : AppCompatActivity() {
    private var gameView: GameView? = null
    private var model: Model? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameView = findViewById(R.id.game_view)
        gameView!!.setOnTouchListener(this::onTouchListener)
        newGame()
    }

    fun newGame() {
        this.model = Model(5, 5)
        gameView?.setModel(this.model)
    }

    private fun onTouchListener(v: View?, event: MotionEvent?): Boolean {
        if (model != null && gameView != null) {
            val coords = gameView?.getCell(event?.x, event?.y)
            model?.clickCell(coords)
            gameView?.invalidate()
        }
        return v?.onTouchEvent(event) ?: true
    }
}