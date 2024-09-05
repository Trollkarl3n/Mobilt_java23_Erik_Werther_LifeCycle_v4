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

// Login Activity for the Android app
class Login : AppCompatActivity() {

    // Declaring UI elements and Firebase Authentication instance
    var editTextEmail: TextInputEditText? = null
    var editTextPassword: TextInputEditText? = null
    var buttonLogin: Button? = null
    var mAuth: FirebaseAuth? = null
    var progressBar: ProgressBar? = null
    var textView: TextView? = null

    // Method that runs when the activity becomes visible to the user
    public override fun onStart() {
        super.onStart()

        // Check if a user is already logged in
        val currentUser = mAuth!!.currentUser
        if (currentUser != null) {
            // If user is logged in, redirect them to the MainActivity
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish() // Close the Login activity so the user can't come back to it with the back button
        }
    }

    // Method that runs when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enabling edge-to-edge UI experience
        this.enableEdgeToEdge()

        // Set the content view to the login layout
        setContentView(R.layout.activity_login)

        // Initialize Firebase Authentication instance
        mAuth = FirebaseAuth.getInstance()

        // Link UI elements to their respective views in the layout
        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        buttonLogin = findViewById(R.id.btn_login)
        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.registerNow)

        // Set a click listener on the "Register Now" text to redirect to the Register activity
        textView?.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, Register::class.java)
            startActivity(intent)
            finish() // Finish the Login activity to prevent going back to it
        })

        // Set a click listener on the login button to trigger the login process
        buttonLogin?.setOnClickListener(View.OnClickListener {
            // Show the progress bar to indicate that the login process is in progress
            progressBar?.visibility = View.VISIBLE

            // Get the email and password entered by the user
            val email = editTextEmail?.text.toString()
            val password = editTextPassword?.text.toString()

            // Check if email is empty
            if (TextUtils.isEmpty(email)) {
                // If email is empty, show a toast message to prompt the user to enter it
                Toast.makeText(this@Login, "Enter email", Toast.LENGTH_SHORT).show()
                progressBar?.visibility = View.GONE // Hide the progress bar
                return@OnClickListener // Stop the login process
            }

            // Check if password is empty
            if (TextUtils.isEmpty(password)) {
                // If password is empty, show a toast message to prompt the user to enter it
                Toast.makeText(this@Login, "Enter password", Toast.LENGTH_SHORT).show()
                progressBar?.visibility = View.GONE // Hide the progress bar
                return@OnClickListener // Stop the login process
            }

            // If both email and password are provided, attempt to sign in with Firebase Authentication
            mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    // Hide the progress bar once the login process completes
                    progressBar?.visibility = View.GONE

                    if (task.isSuccessful) {
                        // If login is successful, show a toast message
                        Toast.makeText(this@Login, "Login Success", Toast.LENGTH_SHORT).show()

                        // Redirect the user to the MainActivity
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish() // Close the Login activity
                    } else {
                        // If login fails, show a toast message indicating the failure
                        Toast.makeText(this@Login, "Login failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        })
    }
}