package za.ac.iie.quizeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)


            val tvFinalScore = findViewById<TextView>(R.id.tvFinalScore)

            val tvFinalFeedback = findViewById<TextView>(R.id.tvFinalFeedback)

            val btnReview = findViewById<Button>(R.id.btnReview)

            val btnExit = findViewById<Button>(R.id.btnExit)


            val score = intent.getIntExtra("SCORE", 0)

            val questions = intent.getStringArrayExtra("QUESTIONS")

            val answers = intent.getBooleanArrayExtra("ANSWERS")



            tvFinalScore.text = "You scored $score out of 5"


            // Feedback based on score

            tvFinalFeedback.text = if (score >= 3) "Great job!" else "Keep practising!"


            // Button to review all correct answers

            btnReview.setOnClickListener {

                val intent = Intent(this, ReviewActivity::class.java)

                intent.putExtra("QUESTIONS", questions)

                intent.putExtra("ANSWERS", answers)

                startActivity(intent)

            }


            // Exit the app

            btnExit.setOnClickListener {

                finishAffinity()






                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.review)) { v, insets ->
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

