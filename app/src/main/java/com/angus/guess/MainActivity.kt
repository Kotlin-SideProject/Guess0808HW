package com.angus.guess

//import 表示路徑提供不是載入library
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

//2020/08/08 HW1

//1.猜拳判斷勝負
//2.四個骰子 兩兩相同一對 加總另外兩個 判斷輸贏

//沒寫代表public ,但是要能繼承需要open class
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_guess -> {
                startActivity(Intent(this, GuessActivity::class.java))
            }
            R.id.action_mora -> {
                startActivity(Intent(this, MoraActivity::class.java))
            }
            R.id.action_sausage -> {
                startActivity(Intent(this, SausageActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }
}

