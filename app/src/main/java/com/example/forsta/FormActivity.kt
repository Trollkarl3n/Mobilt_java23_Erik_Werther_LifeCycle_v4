package com.example.forsta

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// FormActivity allows users to input their age, email, gender, and license status
class FormActivity : AppCompatActivity() {

    // Declaring UI elements: EditTexts for age and email, a CheckBox for license, RadioGroup for gender, and a Button for form submission
    var ageInput: EditText? = null
    var emailInput: EditText? = null
    var hasLicenseCheckBox: CheckBox? = null
    var genderRadioGroup: RadioGroup? = null
    var submitButton: Button? = null

    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout of the activity to the corresponding XML file (activity_form.xml)
        setContentView(R.layout.activity_form)

        // Initialize UI elements by linking them to their corresponding views in the layout
        ageInput = findViewById(R.id.age_input)
        emailInput = findViewById(R.id.name_input)  // This is named incorrectly, should probably be 'email_input'
        hasLicenseCheckBox = findViewById(R.id.license_checkbox)
        genderRadioGroup = findViewById(R.id.gender_radiogroup)
        submitButton = findViewById(R.id.submit_button)

        // Set an OnClickListener to the submit button to trigger the submitForm() method when clicked
        submitButton?.setOnClickListener(View.OnClickListener { submitForm() })
    }

    // Method to handle form submission
    fun submitForm() {
        // Get the values from the input fields and trim any leading/trailing spaces
        val age = ageInput!!.text.toString().trim { it <= ' ' }
        val email = emailInput!!.text.toString().trim { it <= ' ' }
        val hasLicense = hasLicenseCheckBox!!.isChecked // Get the state of the checkbox (checked or not)
        val selectedGenderId = genderRadioGroup!!.checkedRadioButtonId // Get the ID of the selected RadioButton in the RadioGroup

        // Validate form: Check if age, email, or gender are not filled
        if (age.isEmpty() || email.isEmpty() || selectedGenderId == -1) {
            // Show a toast message prompting the user to fill all fields
            Toast.makeText(this, "Fill all boxes", Toast.LENGTH_SHORT).show()
            return // Stop the form submission process if validation fails
        }

        // If a gender is selected, find the selected RadioButton and get its text (Male/Female/Other)
        val selectedGenderButton = findViewById<RadioButton>(selectedGenderId)
        val gender = selectedGenderButton.text.toString()

        // Display the collected data in a toast message
        val result = "Age: $age\nEmail: $email\nLicense: $hasLicense\nGender: $gender"
        Toast.makeText(this, result, Toast.LENGTH_LONG).show()
    }
}