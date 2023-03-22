package com.example.lab1_p2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab1_p2.main.MainActivity
import java.util.Timer
import kotlin.concurrent.timerTask

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val timer = Timer()
        timer.schedule(
            timerTask {
                val intent = Intent(this@Splash, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            2000
        )
    }
}