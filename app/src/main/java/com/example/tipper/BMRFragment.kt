package com.example.tipper

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

internal enum class Gender {
    FEMALE, MALE
}

class BMRFragment : Fragment() {
    private var genderRadioGroup: RadioGroup? = null
    private var ageEditText: EditText? = null
    private var heightEditText: EditText? = null
    private var weightEditText: EditText? = null
    private var age = 0
    private var height = 0.0
    private var weight = 0.0
    private var gender: Gender? = null
    private var bmrTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_b_m_r, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        genderRadioGroup = view.findViewById(R.id.genderRadioGroup)
        ageEditText = view.findViewById(R.id.ageEditText)
        heightEditText = view.findViewById(R.id.heightEditText)
        weightEditText = view.findViewById(R.id.weightEditText)
        bmrTextView = view.findViewById(R.id.bmrTextView)
        genderRadioGroup?.setOnCheckedChangeListener(genderRadioGroupListener)
        ageEditText?.addTextChangedListener(ageTextWatcher)
        heightEditText?.addTextChangedListener(heightTextWatcher)
        weightEditText?.addTextChangedListener(weightTextWatcher)
    }

    private val genderRadioGroupListener =
        RadioGroup.OnCheckedChangeListener { radioGroup, checkedButtonId ->
            when (checkedButtonId) {
                R.id.male -> gender = Gender.MALE
                R.id.female -> gender = Gender.FEMALE
            }
            updateTextView(calculateBMR())
        }

    private fun updateTextView(bmr: Double) {
        bmrTextView!!.text = bmr.toString()
    }

    private fun calculateBMR(): Double {
        val constantValue = if (gender == Gender.MALE) 88.362 else 447.593
        val ageScoreMultiplier = if (gender == Gender.MALE) 5.677 else 4.330
        val weightScoreMultiplier = if (gender == Gender.MALE) 13.397 else 9.247
        val heightScoreMultiplier = if (gender == Gender.MALE) 4.799 else 3.098
        val ageScore = age * ageScoreMultiplier
        val weightScore = weight * weightScoreMultiplier
        val heightScore = height * heightScoreMultiplier
        return constantValue + weightScore + heightScore - ageScore
    }

    private val ageTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            age = charSequence.toString().toInt()
            updateTextView(calculateBMR())
        }

        override fun afterTextChanged(editable: Editable) {}
    }
    private val heightTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            height = charSequence.toString().toDouble()
            updateTextView(calculateBMR())
        }

        override fun afterTextChanged(editable: Editable) {}
    }
    private val weightTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            weight = charSequence.toString().toDouble()
            updateTextView(calculateBMR())
        }

        override fun afterTextChanged(editable: Editable) {}
    }
}