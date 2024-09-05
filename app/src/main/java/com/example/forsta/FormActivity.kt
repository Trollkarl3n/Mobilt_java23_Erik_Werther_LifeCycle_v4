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

class FormActivity : AppCompatActivity() {
    var ageInput: EditText? = null
    var emailInput: EditText? = null
    var hasLicenseCheckBox: CheckBox? = null
    var genderRadioGroup: RadioGroup? = null
    var submitButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        ageInput = findViewById(R.id.age_input)
        emailInput = findViewById(R.id.name_input)
        hasLicenseCheckBox = findViewById(R.id.license_checkbox)
        genderRadioGroup = findViewById(R.id.gender_radiogroup)
        submitButton = findViewById(R.id.submit_button)

        submitButton?.setOnClickListener(View.OnClickListener { submitForm() })
    }

    fun submitForm() {
        val age = ageInput!!.text.toString().trim { it <= ' ' }
        val email = emailInput!!.text.toString().trim { it <= ' ' }
        val hasLicense = hasLicenseCheckBox!!.isChecked
        val selectedGenderId = genderRadioGroup!!.checkedRadioButtonId

        if (age.isEmpty() || email.isEmpty() || selectedGenderId == -1) {
            Toast.makeText(this, "Fill all boxes", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedGenderButton = findViewById<RadioButton>(selectedGenderId)
        val gender = selectedGenderButton.text.toString()

        // Här kan du hantera inskickad data, t.ex. spara i Firebase eller visa en sammanfattning
        val result = "Age: $age\nEmail: $email\nLicense: $hasLicense\nGender: $gender"
        Toast.makeText(this, result, Toast.LENGTH_LONG).show()
    }
}