package com.example.tipper

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.DecimalFormat
import java.text.NumberFormat

class BMIFragment : Fragment() {
    private var heightEditText: EditText? = null
    private var weightEditText: EditText? = null
    private var bmiTextView: TextView? = null
    private var weight = 0.0
    private var height = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_b_m_i, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        heightEditText = view.findViewById(R.id.heightEditText)
        weightEditText = view.findViewById(R.id.weightEditText)
        bmiTextView = view.findViewById(R.id.bmiTextView)
        heightEditText.addTextChangedListener(heightTextWatcher)
        weightEditText.addTextChangedListener(weightTextWatcher)
    }

    private val heightTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            height = charSequence.toString().toDouble()
            bmiTextView!!.text = bmiFormat.format(calculateBMI())
        }

        override fun afterTextChanged(editable: Editable) {}
    }
    private val weightTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            weight = charSequence.toString().toDouble()
            bmiTextView!!.text = bmiFormat.format(calculateBMI())
        }

        override fun afterTextChanged(editable: Editable) {}
    }

    private fun calculateBMI(): Double {
        val heightInMeters = height / 100
        return weight / Math.pow(heightInMeters, 2.0)
    }

    companion object {
        private val bmiFormat: NumberFormat = DecimalFormat("#.##")
    }
}