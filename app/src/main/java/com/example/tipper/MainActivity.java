package com.example.tipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for bill amount input
import android.widget.TextView; // for displaying text

import java.text.DecimalFormat;
import java.text.NumberFormat; // for currency formatting

public class MainActivity extends AppCompatActivity {

    // currency and percent formatter objects
    private static final NumberFormat heightFormat = new DecimalFormat("# cm");
    private static final NumberFormat bmiFormat = new DecimalFormat("#");

    private double billAmount = 0.0; // bill amount entered by the user
    private double percent = 0.15; // initial tip percentage
    private TextView heightTextView; // shows formatted bill amount
    private TextView bmiTextView; // shows calculated total bill amount

    // called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.activity_main); // inflate the GUI

        // get references to programmatically manipulated TextViews
        heightTextView = (TextView) findViewById(R.id.heightTextView);
        bmiTextView = (TextView) findViewById(R.id.totalTextView);
        bmiTextView.setText(bmiFormat.format(0));

        // set amountEditText's TextWatcher
        EditText amountEditText =
                (EditText) findViewById(R.id.heightEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);
    }

    // calculate and display tip and total amounts
    private void calculate() {
        // calculate the tip and total
        double tip = billAmount * percent;
        double total = billAmount + tip;

        // display tip and total formatted as currency
        bmiTextView.setText(bmiFormat.format(total));
    }

    // listener object for the EditText's text-changed events
    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        // called when the user modifies the bill amount
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get bill amount and display currency formatted value
                billAmount = Double.parseDouble(s.toString()) / 100.0;
                heightTextView.setText(heightFormat.format(billAmount));
            }
            catch (NumberFormatException e) { // if s is empty or non-numeric
                heightTextView.setText("");
                billAmount = 0.0;
            }

            calculate(); // update the tip and total TextViews
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };
}


/*************************************************************************
 * (C) Copyright 1992-2016 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
