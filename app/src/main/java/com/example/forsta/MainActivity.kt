package com.example.forsta

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

// MainActivity manages the main screen after a user logs in and allows user logout and navigation to the FormActivity
class MainActivity : AppCompatActivity() {

    // Declaring variables for Firebase authentication, logout button, user details display, and the current user
    var auth: FirebaseAuth? = null
    var logoutButton: Button? = null
    var userDetailsTextView: TextView? = null
    var user: FirebaseUser? = null

    // Method that is called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables an edge-to-edge UI experience
        this.enableEdgeToEdge()

        // Sets the content view to the corresponding layout for MainActivity
        setContentView(R.layout.activity_main)

        // Initialize Firebase Authentication instance and retrieve the current user
        auth = FirebaseAuth.getInstance()
        user = auth!!.currentUser

        // Link the UI elements to their corresponding views in the layout
        logoutButton = findViewById(R.id.logout)
        userDetailsTextView = findViewById(R.id.user_details)

        // Set an OnClickListener for the logout button to sign out the user
        logoutButton?.setOnClickListener(View.OnClickListener {
            // Sign out the user from Firebase Authentication
            FirebaseAuth.getInstance().signOut()

            // Redirect the user to the Login activity after signing out
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish() // Close MainActivity after logout
        })
    }

    // Method to inflate the options menu (menu_main.xml) in the action bar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu, adding items to the action bar
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Method to handle menu item selections from the options menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Check if the selected menu item is the 'action_form' option
        if (item.itemId == R.id.action_form) {
            // If the 'action_form' item is selected, start the FormActivity
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
            return true
        }
        // If another option is selected, call the parent class method
        return super.onOptionsItemSelected(item)
    }
}