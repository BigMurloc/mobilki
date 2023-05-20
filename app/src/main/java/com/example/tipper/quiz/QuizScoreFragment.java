package com.example.tipper.quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tipper.R;

public class QuizScoreFragment extends Fragment {

    private static final String ARG_SCORE = "score";
    private static final String ARG_QUESTION_COUNT = "questionCount";

    public static QuizScoreFragment newInstance(int score, int questionCount) {
        QuizScoreFragment fragment = new QuizScoreFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SCORE, score);
        args.putInt(ARG_QUESTION_COUNT, questionCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_score, container, false);

        int score = getArguments().getInt(ARG_SCORE);
        int questionCount = getArguments().getInt(ARG_QUESTION_COUNT);

        TextView scoreTextView = view.findViewById(R.id.score_text_view);
        String scoreText = getResources().getString(R.string.quiz_score, score, questionCount);
        scoreTextView.setText(scoreText);

        return view;
    }
}