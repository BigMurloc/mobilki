package com.example.tipper.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tipper.R
import java.util.Objects

class QuizFragment : Fragment() {
    private var score = 0
    private var questionTextView: TextView? = null
    private var answerButton1: Button? = null
    private var answerButton2: Button? = null
    private var answerButton3: Button? = null
    private val questions = arrayOf(
        QuizQuestion(
            "Ile dziennie kcal potrzebują kobiety?",
            arrayOf("Około 1000", "Około 4000", "Około 2000"),
            ANSWER_C
        ),
        QuizQuestion(
            "Która z podanych diet nie spożywa produktów pochodzenia zwierzęcego?",
            arrayOf("Wegetariańska", "Wegańska", "Karniwora"),
            ANSWER_B
        ),
        QuizQuestion(
            "Pij mleko?",
            arrayOf("Będziesz męski!", "Będziesz kaleką!", "Będziesz wielki!"),
            ANSWER_C
        ),
        QuizQuestion(
            "Zbalansowana dieta może pomóc z...",
            arrayOf("zdrowiem", "samochodem", "sąsiadem"),
            ANSWER_A
        ),
        QuizQuestion(
            "Co należy zrobić z owocem przed jego spożyciem?",
            arrayOf("Kupić", "Umyć", "Ugotować"),
            ANSWER_B
        ),
        QuizQuestion(
            "Dobre źródło węglowodanów to",
            arrayOf("Makaron", "Chipsy", "Mięso"),
            ANSWER_A
        )
    )
    private var currentQuestionIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        questionTextView = view.findViewById(R.id.question_text_view)
        answerButton1 = view.findViewById(R.id.answer_button_1)
        answerButton2 = view.findViewById(R.id.answer_button_2)
        answerButton3 = view.findViewById(R.id.answer_button_3)
        nextQuestion()
        setButtonListeners()
        return view
    }

    private fun setButtonListeners() {
        answerButton1!!.setOnClickListener(AnswerButtonOnClickListener(ANSWER_A))
        answerButton2!!.setOnClickListener(AnswerButtonOnClickListener(ANSWER_B))
        answerButton3!!.setOnClickListener(AnswerButtonOnClickListener(ANSWER_C))
    }

    private inner class AnswerButtonOnClickListener(private val answerIndex: Int) :
        View.OnClickListener {
        override fun onClick(v: View) {
            handleAnswer(answerIndex)
        }
    }

    private fun handleAnswer(answerIndex: Int) {
        if (questions[currentQuestionIndex].correctAnswerIndex == answerIndex) {
            score++
        }
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            nextQuestion()
        } else {
            val scoreFragment = QuizScoreFragment.newInstance(score, questions.size)
            Objects.requireNonNull(activity)
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout, scoreFragment)
                ?.commit()
        }
    }

    private fun nextQuestion() {
        val currentQuestion = questions[currentQuestionIndex]
        questionTextView!!.text = currentQuestion.question
        answerButton1!!.text = currentQuestion.answers[ANSWER_A]
        answerButton2!!.text = currentQuestion.answers[ANSWER_B]
        answerButton3!!.text = currentQuestion.answers[ANSWER_C]
    }

    companion object {
        private const val ANSWER_A = 0
        private const val ANSWER_B = 1
        private const val ANSWER_C = 2
        fun newInstance(param1: String?, param2: String?): QuizFragment {
            return QuizFragment()
        }
    }
}