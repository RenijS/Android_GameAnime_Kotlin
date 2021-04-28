package com.example.animeapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

         val userName = intent.getStringExtra(Constants.USER_NAME)

         val correctChosen = intent.getIntExtra(Constants.CORRECT_CHOSEN,0)
         val totalQuestion = intent.getIntExtra(Constants.TOTAL_QUESTION,0)

        tvCongratulate.text = "Congratulation $userName"
        tvScore.text = "Score: $correctChosen/$totalQuestion"

        val correct: Double = correctChosen.toDouble()
        val total: Double = totalQuestion.toDouble()
        val score = correct/total
        when{
            score <= 0.3 -> ivTrophy.setImageResource(R.drawable.paper_trophy)
            score > 0.3 && score <= 0.7 -> ivTrophy.setImageResource(R.drawable.silver_trophy)
            score > 0.7 -> ivTrophy.setImageResource(R.drawable.gold_trophy)
        }

        buttonFinish.setOnClickListener {
            val intent = Intent(this, SelectionActivity::class.java)
            intent.putExtra(Constants.USER_NAME, userName)
            startActivity(intent)
            finish()
        }

    }
}