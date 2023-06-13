package com.example.tipper;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BMIChartFragment extends Fragment {

    public BMIChartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bmi_chart_fragment, container, false);

        LineChart chart = view.findViewById(R.id.chart);
        chart.setData(generateData());

        return view;
    }

    private LineData generateData() {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            // Randomly generate BMI values between 18.5 and 30
            float randomBmiValue = new Random().nextFloat() * (30 - 18.5f) + 18.5f;
            entries.add(new Entry(i, randomBmiValue));
        }

        LineDataSet dataSet = new LineDataSet(entries, "BMI over Year");
        dataSet.setColor(Color.RED);
        dataSet.setValueTextColor(Color.BLUE);
        dataSet.setLineWidth(2f);

        return new LineData(dataSet);
    }
}