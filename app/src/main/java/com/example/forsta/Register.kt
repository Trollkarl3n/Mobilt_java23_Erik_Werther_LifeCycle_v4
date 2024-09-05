package com.example.forsta

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

// Activity for user registration using Firebase Authentication
class Register : AppCompatActivity() {

    // Declaring UI elements and Firebase Authentication instance
    var editTextEmail: TextInputEditText? = null
    var editTextPassword: TextInputEditText? = null
    var buttonRegister: Button? = null
    var mAuth: FirebaseAuth? = null
    var progressBar: ProgressBar? = null
    var textView: TextView? = null

    // Method that runs when the activity becomes visible to the user
    public override fun onStart() {
        super.onStart()

        // Check if a user is already logged in
        val currentUser = mAuth!!.currentUser
        if (currentUser != null) {
            // If the user is already logged in, redirect to MainActivity
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish() // Close the Register activity
        }
    }

    // Method that runs when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge UI experience
        this.enableEdgeToEdge()

        // Set the content view to the register layout
        setContentView(R.layout.activity_register)

        // Initialize Firebase Authentication instance
        mAuth = FirebaseAuth.getInstance()

        // Link UI elements to their corresponding views in the layout
        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        buttonRegister = findViewById(R.id.btn_register)
        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.loginNow)

        // Set a click listener on the "Login Now" text to redirect to the Login activity
        textView?.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish() // Close the Register activity
        })

        // Set a click listener on the register button to handle registration logic
        buttonRegister?.setOnClickListener(View.OnClickListener {
            // Show the progress bar to indicate that the registration process is in progress
            progressBar?.visibility = View.VISIBLE

            // Get the email and password entered by the user
            val email = editTextEmail?.text.toString()
            val password = editTextPassword?.text.toString()

            // Check if the email field is empty
            if (TextUtils.isEmpty(email)) {
                // If email is empty, show a toast message to prompt the user to enter it
                Toast.makeText(this@Register, "Enter email", Toast.LENGTH_SHORT).show()
                progressBar?.visibility = View.GONE // Hide the progress bar
                return@OnClickListener // Stop the registration process
            }

            // Check if the password field is empty
            if (TextUtils.isEmpty(password)) {
                // If password is empty, show a toast message to prompt the user to enter it
                Toast.makeText(this@Register, "Enter password", Toast.LENGTH_SHORT).show()
                progressBar?.visibility = View.GONE // Hide the progress bar
                return@OnClickListener // Stop the registration process
            }

            // If both email and password are provided, attempt to create a new user with Firebase Authentication
            mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    // Hide the progress bar once the registration process is complete
                    progressBar?.visibility = View.GONE

                    if (task.isSuccessful) {
                        // If the account creation is successful, show a toast message
                        Toast.makeText(this@Register, "Account Created", Toast.LENGTH_SHORT).show()

                        // Redirect the user to the Login activity after successful registration
                        val intent = Intent(applicationContext, Login::class.java)
                        startActivity(intent)
                        finish() // Close the Register activity
                    } else {
                        // If the account creation fails, show a toast message indicating the failure
                        Toast.makeText(this@Register, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        })
    }
}