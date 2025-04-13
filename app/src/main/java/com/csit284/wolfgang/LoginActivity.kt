package com.csit284.wolfgang

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.csit284.wolfgang.app.DataManagement

class LoginActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.loginbtn)
        val createAccButton = findViewById<Button>(R.id.createAccBtn)
        val emailInput = findViewById<EditText>(R.id.email)
        val passwordInput = findViewById<EditText>(R.id.password)

        // local database implementation (sharedPreferences)
        val sharedPreferences = getSharedPreferences("login_data", Context.MODE_PRIVATE)

        val savedUsername = sharedPreferences.getString("USERNAME", "")
        val savedEmail = sharedPreferences.getString("EMAIL", "")
        val savedPassword = sharedPreferences.getString("PASSWORD", "")

        val emailFromIntent = intent.getStringExtra("EMAIL")
        val passwordFromIntent = intent.getStringExtra("PASSWORD")

        if (emailFromIntent != null) {
            emailInput.setText(emailFromIntent)
        } else if (savedEmail.isNullOrEmpty()) {
            emailInput.setText(savedEmail)
        }

        if (passwordFromIntent != null) {
            passwordInput.setText(passwordFromIntent)
        }

        loginButton.setOnClickListener {
            val loginEmail = emailInput.text.toString().trim()
            val loginPassword = passwordInput.text.toString().trim()

            if (validateLogin(loginEmail, loginPassword)) {
                if (loginEmail == savedEmail && loginPassword == savedPassword) {
                    Log.e("csit284", "Login successful")

                    val intent2 = Intent(this, LandingPageActivity::class.java)
                    intent2.putExtra("USERNAME", savedUsername)
                    intent2.putExtra("EMAIL", loginEmail)
                    startActivity(intent2)
                    finish()
                } else {
                    Log.e("csit284", "Invalid login credentials")
                    emailInput.error = "Invalid email or password"
                    passwordInput.error = "Invalid email or password"
                }
            }
        }

        createAccButton.setOnClickListener {
            Log.e("csit284", "Navigating to CreateAccountActivity")
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateLogin(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            findViewById<EditText>(R.id.email).error = "Email is required"
            return false
        }
        if (password.isEmpty()) {
            findViewById<EditText>(R.id.password).error = "Password is required"
            return false
        }
        if (password.length < 6) {
            findViewById<EditText>(R.id.password).error = "Password must be 8 or more characters"
            return false
        }
        return true
    }
}