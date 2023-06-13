package com.example.tipper.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tipper.R

class QuizScoreFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz_score, container, false)
        val score = arguments!!.getInt(ARG_SCORE)
        val questionCount = arguments!!.getInt(ARG_QUESTION_COUNT)
        val scoreTextView = view.findViewById<TextView>(R.id.score_text_view)
        val scoreText = resources.getString(R.string.quiz_score, score, questionCount)
        scoreTextView.text = scoreText
        return view
    }

    companion object {
        private const val ARG_SCORE = "score"
        private const val ARG_QUESTION_COUNT = "questionCount"
        fun newInstance(score: Int, questionCount: Int): QuizScoreFragment {
            val fragment = QuizScoreFragment()
            val args = Bundle()
            args.putInt(ARG_SCORE, score)
            args.putInt(ARG_QUESTION_COUNT, questionCount)
            fragment.arguments = args
            return fragment
        }
    }
}