package com.android.apps.seoulpublicbike

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        supportActionBar?.hide()
        connectRetrofit()
    }

    private fun connectRetrofit() {
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    super.run()
                    sleep(1500) //Delay of 2 seconds
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    val intent = Intent(
                        this@StartActivity,
                        MainActivity::class.java
                    )
                    startActivity(intent)
                    finish()
                }
            }
        }
        thread.start()
    }
}