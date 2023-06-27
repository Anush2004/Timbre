package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity2 extends AppCompatActivity {
    private EditText editTextFirstName, editTextLastName, editTextAge, editTextHeightFeet, editTextHeightInches, editTextWeight;
    private TextView textViewBMIResult;
    RadioGroup radioGroupGender;
    Button buttonSubmit;

    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private float heightFeet;
    private float heightInches;
    private float weight;
    private float bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize EditText fields
        editTextHeightFeet = findViewById(R.id.editTextHeightFeet);
        editTextHeightInches = findViewById(R.id.editTextHeightInches);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAge = findViewById(R.id.editTextAge);


        // Initialize TextView field
        textViewBMIResult = findViewById(R.id.textViewBMIResult);

        // Initialize Button
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Set the initial state of the button
        buttonSubmit.setEnabled(false);

        // Add onTextChanged listeners
        editTextHeightFeet.addTextChangedListener(textWatcher);
        editTextHeightInches.addTextChangedListener(textWatcher);
        editTextWeight.addTextChangedListener(textWatcher);

        editTextFirstName.addTextChangedListener(checkFields);
        editTextLastName.addTextChangedListener(checkFields);
        editTextAge.addTextChangedListener(checkFields);
        editTextHeightFeet.addTextChangedListener(checkFields);
        editTextHeightInches.addTextChangedListener(checkFields);
        editTextWeight.addTextChangedListener(checkFields);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Do nothing
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Do nothing
        }

        public void afterTextChanged(Editable s) {
            calculateBMI();
        }
    };

    private TextWatcher checkFields = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            boolean anyEmpty = TextUtils.isEmpty(editTextAge.getText())
                    || TextUtils.isEmpty(editTextWeight.getText())
                    || TextUtils.isEmpty(editTextHeightFeet.getText())
                    || TextUtils.isEmpty(editTextHeightInches.getText())
                    || TextUtils.isEmpty(editTextFirstName.getText())
                    || TextUtils.isEmpty(editTextLastName.getText());
            radioGroupGender = findViewById(R.id.radioGroupGender);
            boolean genderSelected = radioGroupGender.getCheckedRadioButtonId() != -1;
            buttonSubmit.setEnabled(!anyEmpty && genderSelected);
        }
    };

    private void calculateBMI() {
        // Get the user inputs for height and weight
        EditText heightFeetEditText = findViewById(R.id.editTextHeightFeet);
        EditText heightInchesEditText = findViewById(R.id.editTextHeightInches);
        EditText weightEditText = findViewById(R.id.editTextWeight);

        String heightFeetStr = heightFeetEditText.getText().toString();
        String heightInchesStr = heightInchesEditText.getText().toString();
        String weightStr = weightEditText.getText().toString();

        // Convert the height and weight strings to float values
        heightFeet = heightFeetStr.isEmpty() ? 0 : Float.parseFloat(heightFeetStr);
        heightInches = heightInchesStr.isEmpty() ? 0 : Float.parseFloat(heightInchesStr);
        weight = weightStr.isEmpty() ? 0 : Float.parseFloat(weightStr);

        // Convert the height values from feet and inches to meters
        float heightMeters = (heightFeet * 0.3048f) + (heightInches * 0.0254f);

        // Calculate the BMI
        bmi = weight / (heightMeters * heightMeters);

        // Display the BMI result in the textViewBMIResult
        textViewBMIResult = findViewById(R.id.textViewBMIResult);
        textViewBMIResult.setText("BMI: " + String.format("%.2f", bmi));
    }

    public void submitForm(View view) {
        if (!buttonSubmit.isEnabled()) {
            return;
        }
        else {
            // Get the values from the EditText fields
            editTextFirstName = findViewById(R.id.editTextFirstName);
            editTextLastName = findViewById(R.id.editTextLastName);
            editTextAge = findViewById(R.id.editTextAge);

            firstName = editTextFirstName.getText().toString();
            lastName = editTextLastName.getText().toString();
            age = Integer.parseInt(editTextAge.getText().toString());

            // Get the radio group gender
            radioGroupGender = findViewById(R.id.radioGroupGender);
            int selectedId = radioGroupGender.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton radioButton = findViewById(selectedId);
                gender = radioButton.getText().toString();
            }

            heightFeet = Integer.parseInt(editTextHeightFeet.getText().toString());
            heightInches = Integer.parseInt(editTextHeightInches.getText().toString());
            weight = Float.parseFloat(editTextWeight.getText().toString());
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            intent.putExtra("firstname", firstName);
            intent.putExtra("lastname", lastName);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("bmi", bmi);
            startActivity(intent);
            finish();
        }
    }


}