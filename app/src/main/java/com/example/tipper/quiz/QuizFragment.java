package com.example.tipper.quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tipper.R;

import java.util.Objects;

public class QuizFragment extends Fragment {

    private static final int ANSWER_A = 0;
    private static final int ANSWER_B = 1;
    private static final int ANSWER_C = 2;
    private int score = 0;

    private TextView questionTextView;
    private Button answerButton1, answerButton2, answerButton3;

    private final QuizQuestion[] questions = new QuizQuestion[]{
            new QuizQuestion("Ile dziennie kcal potrzebują kobiety?", new String[]{"Około 1000", "Około 4000", "Około 2000"}, ANSWER_C),
            new QuizQuestion("Która z podanych diet nie spożywa produktów pochodzenia zwierzęcego?", new String[]{"Wegetariańska", "Wegańska", "Karniwora"}, ANSWER_B),
            new QuizQuestion("Pij mleko?", new String[]{"Będziesz męski!", "Będziesz kaleką!", "Będziesz wielki!"}, ANSWER_C),
            new QuizQuestion("Zbalansowana dieta może pomóc z...", new String[]{"zdrowiem", "samochodem", "sąsiadem"}, ANSWER_A),
            new QuizQuestion("Co należy zrobić z owocem przed jego spożyciem?", new String[]{"Kupić", "Umyć", "Ugotować"}, ANSWER_B),
            new QuizQuestion("Dobre źródło węglowodanów to", new String[]{"Makaron", "Chipsy", "Mięso"}, ANSWER_A),
    };

    private int currentQuestionIndex = 0;

    public QuizFragment() {
        // Required empty public constructor
    }

    public static QuizFragment newInstance(String param1, String param2) {
        return new QuizFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        questionTextView = view.findViewById(R.id.question_text_view);
        answerButton1 = view.findViewById(R.id.answer_button_1);
        answerButton2 = view.findViewById(R.id.answer_button_2);
        answerButton3 = view.findViewById(R.id.answer_button_3);

        nextQuestion();
        setButtonListeners();

        return view;
    }

    private void setButtonListeners() {
        answerButton1.setOnClickListener(new AnswerButtonOnClickListener(ANSWER_A));
        answerButton2.setOnClickListener(new AnswerButtonOnClickListener(ANSWER_B));
        answerButton3.setOnClickListener(new AnswerButtonOnClickListener(ANSWER_C));
    }

    private class AnswerButtonOnClickListener implements View.OnClickListener {
        private final int answerIndex;

        public AnswerButtonOnClickListener(int answerIndex) {
            this.answerIndex = answerIndex;
        }

        @Override
        public void onClick(View v) {
            handleAnswer(answerIndex);
        }
    }

    private void handleAnswer(int answerIndex) {
        if (questions[currentQuestionIndex].getCorrectAnswerIndex() == answerIndex) {
            score++;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            nextQuestion();
        } else {
            QuizScoreFragment scoreFragment = QuizScoreFragment.newInstance(score, questions.length);

            Objects.requireNonNull(getActivity())
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, scoreFragment)
                    .commit();
        }
    }

    private void nextQuestion() {
        QuizQuestion currentQuestion = questions[currentQuestionIndex];
        questionTextView.setText(currentQuestion.getQuestion());
        answerButton1.setText(currentQuestion.getAnswers()[ANSWER_A]);
        answerButton2.setText(currentQuestion.getAnswers()[ANSWER_B]);
        answerButton3.setText(currentQuestion.getAnswers()[ANSWER_C]);
    }
}