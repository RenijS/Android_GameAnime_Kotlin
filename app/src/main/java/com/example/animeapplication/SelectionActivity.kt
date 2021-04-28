package com.example.animeapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_selection.*

class SelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)

        //useName is used to get name from mainactivity using intent
        val userName = intent.getStringExtra(Constants.USER_NAME)

        buttonAnime.setOnClickListener {
            val intent = Intent(this, AnimeQuiz::class.java)
            intent.putExtra(Constants.USER_NAME, userName)
            startActivity(intent)
            finish()
        }

        buttonEnd.setOnClickListener {
            finish()
        }
    }
}