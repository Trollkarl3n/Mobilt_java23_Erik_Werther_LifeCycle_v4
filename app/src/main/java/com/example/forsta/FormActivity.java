package com.example.forsta;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {

    EditText ageInput, emailInput;
    CheckBox hasLicenseCheckBox;
    RadioGroup genderRadioGroup;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        ageInput = findViewById(R.id.age_input);
        emailInput = findViewById(R.id.name_input);
        hasLicenseCheckBox = findViewById(R.id.license_checkbox);
        genderRadioGroup = findViewById(R.id.gender_radiogroup);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                submitForm();
            }
        });
    }

    public void submitForm() {
        String age = ageInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        boolean hasLicense = hasLicenseCheckBox.isChecked();
        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();

        if (age.isEmpty() || email.isEmpty() || selectedGenderId == -1) {
            Toast.makeText(this, "Fill all boxes", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedGenderButton = findViewById(selectedGenderId);
        String gender = selectedGenderButton.getText().toString();

        // Här kan du hantera inskickad data, t.ex. spara i Firebase eller visa en sammanfattning
        String result = "Age: " + age + "\nEmail: " + email + "\nLicense: " + hasLicense + "\nGender: " + gender;
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}