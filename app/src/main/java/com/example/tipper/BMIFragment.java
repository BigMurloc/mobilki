package com.example.tipper;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BMIFragment extends Fragment {


    private EditText heightEditText;
    private EditText weightEditText;
    private TextView bmiTextView;
    private double weight;
    private double height;
    private static final NumberFormat bmiFormat = new DecimalFormat("#.##");


    public BMIFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b_m_i, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        heightEditText = view.findViewById(R.id.heightEditText);
        weightEditText = view.findViewById(R.id.weightEditText);
        bmiTextView = view.findViewById(R.id.bmiTextView);

        heightEditText.addTextChangedListener(heightTextWatcher);
        weightEditText.addTextChangedListener(weightTextWatcher);
    }

    private final TextWatcher heightTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            height = Double.parseDouble(charSequence.toString());
            bmiTextView.setText(bmiFormat.format(calculateBMI()));
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
            bmiTextView.setText(bmiFormat.format(calculateBMI()));
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private double calculateBMI() {
        double heightInMeters = height / 100;
        return weight / Math.pow(heightInMeters, 2);
    }
}