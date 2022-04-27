package com.example.daadya


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.daadya.models.Model
import com.example.daadya.views.GameView

class MainActivity : AppCompatActivity() {
    private var gameView: GameView? = null
    private var model: Model? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameView = findViewById(R.id.game_view)
        newGame()

//        findViewById<Button>(R.id.button1_1).setOnClickListener(this::onClick)
//        findViewById<Button>(R.id.button1_2).setOnClickListener(this::onClick)
//        findViewById<Button>(R.id.button1_3).setOnClickListener(this::onClick)
//        findViewById<Button>(R.id.button2_1).setOnClickListener(this::onClick)
//        findViewById<Button>(R.id.button2_2).setOnClickListener(this::onClick)
//        findViewById<Button>(R.id.button2_3).setOnClickListener(this::onClick)
//        findViewById<Button>(R.id.button3_1).setOnClickListener(this::onClick)
//        findViewById<Button>(R.id.button3_2).setOnClickListener(this::onClick)
//        findViewById<Button>(R.id.button3_3).setOnClickListener(this::onClick)

    }

    fun newGame() {
        this.model = Model(5, 5)
        gameView?.setModel(this.model)
    }

//    @SuppressLint("ResourceAsColor")
//    private fun onClick(v: View) {
//        if ((v as Button).background.equals(R.color.cell_colored)) {
//            (v as Button).setBackgroundColor(R.color.cell_uncolored)
//        } else {
//            (v as Button).setBackgroundColor(R.color.cell_colored)
//        }
//
//    }
}