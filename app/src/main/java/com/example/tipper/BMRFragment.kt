package com.example.tipper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

enum Gender {
    FEMALE,
    MALE
}

public class BMRFragment extends Fragment {

    private RadioGroup genderRadioGroup;
    private EditText ageEditText;
    private EditText heightEditText;
    private EditText weightEditText;

    private int age;
    private double height;
    private double weight;
    private Gender gender;

    private TextView bmrTextView;

    public BMRFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b_m_r, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        genderRadioGroup = view.findViewById(R.id.genderRadioGroup);
        ageEditText = view.findViewById(R.id.ageEditText);
        heightEditText = view.findViewById(R.id.heightEditText);
        weightEditText = view.findViewById(R.id.weightEditText);
        bmrTextView = view.findViewById(R.id.bmrTextView);

        genderRadioGroup.setOnCheckedChangeListener(genderRadioGroupListener);
        ageEditText.addTextChangedListener(ageTextWatcher);
        heightEditText.addTextChangedListener(heightTextWatcher);
        weightEditText.addTextChangedListener(weightTextWatcher);

    }

    private final RadioGroup.OnCheckedChangeListener genderRadioGroupListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedButtonId) {
            switch (checkedButtonId) {
                case R.id.male:
                    gender = Gender.MALE;
                    break;
                case R.id.female:
                    gender = Gender.FEMALE;
                    break;
            }

            updateTextView(calculateBMR());
        }
    };

    private void updateTextView(double bmr) {
        bmrTextView.setText(String.valueOf(bmr));
    }

    private double calculateBMR() {
        double constantValue = gender == Gender.MALE ? 88.362 : 447.593;
        double ageScoreMultiplier = gender == Gender.MALE ? 5.677 : 4.330;
        double weightScoreMultiplier = gender == Gender.MALE ? 13.397 : 9.247;
        double heightScoreMultiplier = gender == Gender.MALE ? 4.799 : 3.098;

        double ageScore = age * ageScoreMultiplier;
        double weightScore = weight * weightScoreMultiplier;
        double heightScore = height * heightScoreMultiplier;

        return constantValue + weightScore + heightScore - ageScore;
    }

    private final TextWatcher ageTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            age = Integer.parseInt(charSequence.toString());
            updateTextView(calculateBMR());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private final TextWatcher heightTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            height = Double.parseDouble(charSequence.toString());
            updateTextView(calculateBMR());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private final TextWatcher weightTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            weight = Double.parseDouble(charSequence.toString());
            updateTextView(calculateBMR());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}