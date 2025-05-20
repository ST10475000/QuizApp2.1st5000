package za.ac.iie.quizeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

// Arrays for questions and their corresponding answers

        val questions = arrayOf(

            "Nelson Mandela was the president in 1994.",

            "The Great Wall of China is in India.",

            "The Berlin Wall fell in 1989.",

            "Julius Caesar was a Roman Emperor.",

            "The pyramids were built in France."

        )


        val answers = arrayOf(true, false, true, false, false)


        var currentIndex = 0
        var score = 0

            val tvQuestion = findViewById<TextView>(R.id.tvQuestion)
            val tvFeedback = findViewById<TextView>(R.id.tvFeedback)
            val btnTrue = findViewById<Button>(R.id.btnTrue)
            val btnFalse = findViewById<Button>(R.id.False)
            val btnNext = findViewById<Button>(R.id.btnNext)

            // Display first question
            tvQuestion.text = questions[currentIndex]

            fun checkAnswer(userAnswer: Boolean) {
                val correctAnswer = answers[currentIndex]
                if (userAnswer == correctAnswer) {
                    tvFeedback.text = "Correct!"
                    score++
                } else {
                    tvFeedback.text = "Incorrect"
                }
                btnTrue.isEnabled = false
                btnFalse.isEnabled = false
                btnNext.isEnabled = true
            }

            btnTrue.setOnClickListener {
                checkAnswer(true)
            }

            btnFalse.setOnClickListener {
                checkAnswer(false)

            }

            btnNext.setOnClickListener {
                currentIndex++
                if (currentIndex < questions.size) {
                    tvQuestion.text = questions[currentIndex]
                    tvFeedback.text = ""
                    btnTrue.isEnabled = true
                    btnFalse.isEnabled = true
                    btnNext.isEnabled = false
                } else {
                    val intent = Intent(this, ScoreActivity::class.java)
                    intent.putExtra("SCORE", score)
                    intent.putExtra("QUESTIONS", questions)
                    intent.putExtra("ANSWERS", answers)
                    startActivity(intent)
                    finish()


                    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tvFeedBack)) { v, insets ->
                        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                        v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                        )
                        insets
                    }
                }
            }
        }
    }
