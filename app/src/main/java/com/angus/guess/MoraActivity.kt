package com.angus.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mora.*
import java.util.*

class MoraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mora)

        paper.setOnClickListener {
            // paper = 1, scissors = 2, rock = 3
            val secret = Random().nextInt(3) + 1
            when(secret){
                1 -> {
                    mora_result.text = "Game Tie!"
                    chooseImage.setImageResource(R.drawable.paper)
                }
                2 -> {
                    mora_result.text = "You Lose!!"
                    chooseImage.setImageResource(R.drawable.scissors)
                }
                3 -> {
                    mora_result.text = "You Win!!!"
                    chooseImage.setImageResource(R.drawable.rocks)
                }
            }
        }

        scissors.setOnClickListener {
            // paper = 1, scissors = 2, rock = 3
            val secret = Random().nextInt(3) + 1
            when(secret){
                1 -> {
                    mora_result.text = "You Win!!!"
                    chooseImage.setImageResource(R.drawable.paper)
                }
                2 -> {
                    mora_result.text = "Game Tie!"
                    chooseImage.setImageResource(R.drawable.scissors)
                }
                3 -> {
                    mora_result.text = "You Lose!!"
                    chooseImage.setImageResource(R.drawable.rocks)
                }

            }
        }

        rock.setOnClickListener {
            // paper = 1, scissors = 2, rock = 3
            val secret = Random().nextInt(3) + 1
            when(secret){
                1 -> {
                    mora_result.text = "You Lose!!"
                    chooseImage.setImageResource(R.drawable.paper)
                }
                2 -> {
                    mora_result.text = "You Win!!!"
                    chooseImage.setImageResource(R.drawable.scissors)
                }
                3 -> {
                    mora_result.text = "Game Tie!"
                    chooseImage.setImageResource(R.drawable.rocks)
                }
            }
        }
    }
}