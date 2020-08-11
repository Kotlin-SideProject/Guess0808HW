package com.angus.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.angus.guess.R.drawable
import kotlinx.android.synthetic.main.activity_guess.*
import kotlinx.android.synthetic.main.activity_sausage.*
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.HashSet

class SausageActivity : AppCompatActivity() {
    private var mySum: Int = 0
    private var comSum: Int = 0
    var TAG = "SausageActivity.kt"
    var comDices = mutableListOf<Int>()
    val diceIds = intArrayOf(
        R.drawable.dice_one,
        R.drawable.dice_two,
        R.drawable.dice_three,
        R.drawable.dice_four,
        R.drawable.dice_five,
        R.drawable.dice_six)
    companion object{
        val STATE_DICEAGAIN = 1
        val STATE_RESULTLOSE= 2
        val STATE_RESULT = 3
        val STATE_BIGGERRESULT = 4
        val STATE_RESULTWIN = 5
        var currentSTATE = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sausage)


        // my Dice
        dice.setOnClickListener {
            var myDices = mutableListOf<Int>()
            for (i in 0..3){
                var secret = Random().nextInt(6) + 1
                myDices.add(i, secret)
            }
            Log.d(TAG, "myDices: ${myDices[0]} ${myDices[1]} ${myDices[2]} ${myDices[3]}")

            //setImage
            your_dice1.run {
                visibility = View.VISIBLE
                setImageResource(diceIds[myDices[0] -1 ])
            }
            your_dice2.run {
                visibility = View.VISIBLE
                setImageResource(diceIds[myDices[1] -1 ])
            }
            your_dice3.run {
                visibility = View.VISIBLE
                setImageResource(diceIds[myDices[2] -1 ])
            }
            your_dice4.run {
                visibility = View.VISIBLE
                setImageResource(diceIds[myDices[3] -1 ])
            }
//            Log.d(TAG, "Dices: ${myDices[0]} ${myDices[1]} ${myDices[2]} ${myDices[3]}")
             mySum =  diceSum(myDices)
            var msg : StringBuilder = StringBuilder()
            when (mySum){
                0 -> {
                    currentSTATE = STATE_DICEAGAIN
                    sausage_result.text = "沒有結果，在骰一次"
                    com_dice1.visibility = View.INVISIBLE
                    com_dice2.visibility = View.INVISIBLE
                    com_dice3.visibility = View.INVISIBLE
                    com_dice4.visibility = View.INVISIBLE
                }
                3 -> {
                    currentSTATE = STATE_RESULTLOSE
                    sausage_result.text = "逼基 you lose!!"
//                    dice.isEnabled = false
                }
                in 4..12 -> {
                    currentSTATE = STATE_RESULT
//                    dice.isEnabled = false
                    msg.clear()
                    msg.append("等待對手結果...")
                    sausage_result.text = msg

                    // computer dice
                    while (comSum == 0) {
                        comDices.clear()
                        for (i in 0..3) {
                            var secret = Random().nextInt(6) + 1
                            comDices.add(i, secret)
                        }
                        comSum = diceSum(comDices)
                    }
                    Log.d(TAG, "${comDices}");
                    Log.d(TAG, "ComDices: ${comDices[0]} ${comDices[1]} ${comDices[2]} ${comDices[3]}")

                    //setImage
                        com_dice1.run {
                            visibility = View.VISIBLE
                            setImageResource(diceIds[comDices[0] -1 ])
                        }
                        com_dice2.run {
                            visibility = View.VISIBLE
                            setImageResource(diceIds[comDices[1] -1 ])
                        }
                        com_dice3.run {
                            visibility = View.VISIBLE
                            setImageResource(diceIds[comDices[2] -1 ])
                        }
                        com_dice4.run {
                            visibility = View.VISIBLE
                            setImageResource(diceIds[comDices[3] -1 ])
                        }


                        if (comSum == mySum){
                            currentSTATE = STATE_DICEAGAIN
                            dice.isEnabled = true
                            msg.append(" Game Tie , Dice again \n" +
                                    "Your Dice ${myDices[0]} ${myDices[1]} ${myDices[2]} ${myDices[3]} sum = ${mySum} \n" +
                                    "Computer Dice ${comDices[0]} ${comDices[1]} ${comDices[2]} ${comDices[3]} sum = ${comSum}"
                            )
                            sausage_result.text = msg
                            comSum = 0 // reset

                        }else if (comSum < mySum){
                            msg.append(" you win!! \n" +
                                    "Your Dice ${myDices[0]} ${myDices[1]} ${myDices[2]} ${myDices[3]} sum = ${mySum} \n" +
                                    "Computer Dice ${comDices[0]} ${comDices[1]} ${comDices[2]} ${comDices[3]} sum = ${comSum}"
                            )
                            sausage_result.text = msg
                            comSum = 0
                        }else if(comSum > mySum){
                            msg.append(" you lose!! \n" +
                                    "Your Dice ${myDices[0]} ${myDices[1]} ${myDices[2]} ${myDices[3]} sum = ${mySum} \n" +
                                    "Computer Dice ${comDices[0]} ${comDices[1]} ${comDices[2]} ${comDices[3]} sum = ${comSum}"
                            )
                            sausage_result.text = msg
                            comSum = 0
                        }


                }
                13 -> {
                    currentSTATE = STATE_RESULTWIN
                    sausage_result.text = "十八 you win!!!"
//                    dice.isEnabled = false
                }
            }
        }


    }

    private fun diceSum(myDices: MutableList<Int>) :  Int {
        var repeatTime = 0
        var repeatNumber = hashSetOf<Int>()
        var sum = 0

        // repeatNumber setting
        for (i in 0..myDices.size -1){
           for(j in i..myDices.size-1){
               if ( i != j && myDices[i] == myDices[j]){
                   repeatNumber.add(myDices[i])
               }
           }
        }
        // repeatTime setting
        for (i in 0..repeatNumber.size -1){
            for (j in 0..myDices.size -1 ){
                if (repeatNumber.elementAt(0) == myDices[j]){
                    repeatTime++
                }
            }
        }
        // STATE
        if (repeatTime == 0){
            sum = 0
        }else if(repeatTime == 2){
            sum = myDices.sum() - 2 * repeatNumber.elementAt(0)
        }else if (repeatTime == 3){
            sum = 0
        }else if (repeatTime == 4 && repeatNumber.size == 1){
            sum = 13 // 13 win!!
        }else if (repeatTime == 4 && repeatNumber.size == 2){
            sum = myDices.sum() - 2 * repeatNumber.elementAt(0)
        }
//                Log.d(TAG, "repeatTime :${repeatTime} , repeatNumber : ${repeatNumber} sum ${sum}")


        return sum
    }
}