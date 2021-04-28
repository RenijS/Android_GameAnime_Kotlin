package com.example.animeapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_anime_quiz.*

class AnimeQuiz : AppCompatActivity(), View.OnClickListener {

    var currentQuestionNum : Int = 1
    var selectedOption : Int = 0
    val questionList = Constants.getQuestionList()
    //how many correct answer were selected?
    var correctChosen : Int = 0
    //userName to get name from intent
    var userName: String? = null

    companion object{
        const val TAG = "AnimeQuiz"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_quiz)

        userName = intent.getStringExtra(Constants.USER_NAME)

        setQuestion()

        //this tag is used to identify if the button is clicked or not and is used to change the button's action
        buttonAnsSubmit.tag = 1

        tvOption1.setOnClickListener(this)
        tvOption2.setOnClickListener(this)
        tvOption3.setOnClickListener(this)
        tvOption4.setOnClickListener(this)
        buttonAnsSubmit.setOnClickListener(this)
    }

    /*setQuestion is used to set questions on screen according to Qnum
    here we also set options background cuz color needs to be returned to normal everytime we go to next Q
    */
    fun setQuestion(){
        tvQuestion.text = questionList[currentQuestionNum-1].question
        ivImage.setImageResource(questionList[currentQuestionNum-1].image)
        tvOption1.text = questionList[currentQuestionNum-1].option1
        tvOption1.background = ContextCompat.getDrawable(this, R.drawable.rounded_edittext)
        tvOption2.text = questionList[currentQuestionNum-1].option2
        tvOption2.background = ContextCompat.getDrawable(this, R.drawable.rounded_edittext)
        tvOption3.text = questionList[currentQuestionNum-1].option3
        tvOption3.background = ContextCompat.getDrawable(this, R.drawable.rounded_edittext)
        tvOption4.text = questionList[currentQuestionNum-1].option4
        tvOption4.background = ContextCompat.getDrawable(this, R.drawable.rounded_edittext)
    }

    private fun checkCorrectness(selectedOption: Int, qList : ArrayList<AnimeQuestions>): Boolean{
        if (selectedOption != qList[currentQuestionNum-1].correctAnswer){
            return false
        }
        correctChosen++
        return true
    }

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onClick(p0: View?) {

        when(p0?.id){
            R.id.tvOption1 -> {
                selectedOption = 1
                //changes the text color of selected text
                tvOption1.setTextColor(Color.rgb(0,0,0))
                //makes other options text to normal color
                tvOption2.setTextColor(R.color.notSelectedText)
                tvOption3.setTextColor(R.color.notSelectedText)
                tvOption4.setTextColor(R.color.notSelectedText)
            }
            R.id.tvOption2 -> {
                selectedOption = 2
                tvOption2.setTextColor(Color.rgb(0,0,0))
                tvOption1.setTextColor(R.color.notSelectedText)
                tvOption3.setTextColor(R.color.notSelectedText)
                tvOption4.setTextColor(R.color.notSelectedText)
            }
            R.id.tvOption3 -> {
                selectedOption = 3
                tvOption3.setTextColor(Color.rgb(0,0,0))
                tvOption1.setTextColor(R.color.notSelectedText)
                tvOption2.setTextColor(R.color.notSelectedText)
                tvOption4.setTextColor(R.color.notSelectedText)

            }
            R.id.tvOption4 -> {
                selectedOption = 4
                tvOption4.setTextColor(Color.rgb(0,0,0))
                tvOption1.setTextColor(R.color.notSelectedText)
                tvOption2.setTextColor(R.color.notSelectedText)
                tvOption3.setTextColor(R.color.notSelectedText)
            }
            R.id.buttonAnsSubmit -> {
                Log.i(TAG,"selected option $selectedOption")
                // when button is clicked in tag 0 we went to next question
                if (buttonAnsSubmit.tag == 0){
                    //when Qnum and listSize equals it means we finished quiz
                    if (currentQuestionNum == questionList.size){
                        val intent = Intent(this, ScoreActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, userName)
                        intent.putExtra(Constants.CORRECT_CHOSEN, correctChosen)
                        intent.putExtra(Constants.TOTAL_QUESTION, questionList.size)
                        startActivity(intent)
                        finish()
                    } else {
                        buttonAnsSubmit.tag = 1
                        currentQuestionNum++
                        buttonAnsSubmit.text = "Submit"
                        setQuestion()
                    }

                } else {
                    if (checkCorrectness(selectedOption, questionList)) {
                        when (selectedOption) {
                            1 -> tvOption1.background =
                                ContextCompat.getDrawable(this, R.drawable.correct_option_selected)
                            2 -> tvOption2.background =
                                ContextCompat.getDrawable(this, R.drawable.correct_option_selected)
                            3 -> tvOption3.background =
                                ContextCompat.getDrawable(this, R.drawable.correct_option_selected)
                            4 -> tvOption4.background =
                                ContextCompat.getDrawable(this, R.drawable.correct_option_selected)
                        }
                    } else {
                        when (selectedOption) {
                            1 -> tvOption1.background = ContextCompat.getDrawable(this, R.drawable.incorrect_option_selected)
                            2 -> tvOption2.background = ContextCompat.getDrawable(
                                this,
                                R.drawable.incorrect_option_selected
                            )
                            3 -> tvOption3.background = ContextCompat.getDrawable(
                                this,
                                R.drawable.incorrect_option_selected
                            )
                            4 -> tvOption4.background = ContextCompat.getDrawable(
                                this,
                                R.drawable.incorrect_option_selected
                            )
                        }
                    }
                    selectedOption = 0
                    progressBar.incrementProgressBy(1)
                    tvProgress.text = "$currentQuestionNum/${questionList.size}"
                    if (currentQuestionNum == questionList.size) {
                        buttonAnsSubmit.text = "Final Result"
                    } else {
                        buttonAnsSubmit.text = "Go To another Question"
                    }
                    buttonAnsSubmit.tag = 0
                }
            }
        }
    }
}