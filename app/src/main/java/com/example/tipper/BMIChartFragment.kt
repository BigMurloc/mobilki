package com.example.tipper

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.tipper.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.util.*

class BMIChartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bmi_chart_fragment, container, false)
        val chart: LineChart = view.findViewById(R.id.chart)
        chart.data = generateData()
        return view
    }

    private fun generateData(): LineData {
        val entries: MutableList<Entry> = ArrayList()
        for (i in 0..11) {
            // Randomly generate BMI values between 18.5 and 30
            val randomBmiValue = Random().nextFloat() * (30 - 18.5f) + 18.5f
            entries.add(Entry(i.toFloat(), randomBmiValue))
        }
        val dataSet = LineDataSet(entries, "BMI over Year")
        dataSet.color = Color.RED
        dataSet.valueTextColor = Color.BLUE
        dataSet.lineWidth = 2f
        return LineData(dataSet)
    }

}