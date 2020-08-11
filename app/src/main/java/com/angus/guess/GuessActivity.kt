package com.angus.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_guess.*
import java.util.*

class GuessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess)

        val secret = Random().nextInt(10)  + 1 //bound =  10 產生0~9亂數
        guess.setOnClickListener {
            var guessNumber  = et_number.text.toString().toInt()
            if(guessNumber < secret){
                result.text = "too small , guess bigger"
            }else if(guessNumber == secret){
                result.text = "Bingo!!!!"
            }else if (guessNumber > secret){
                result.text = "too bigger, guess smaller"
            }
        }
    }
}