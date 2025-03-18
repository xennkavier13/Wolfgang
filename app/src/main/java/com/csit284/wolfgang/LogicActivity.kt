package com.csit284.wolfgang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.csit284.wolfgang.app.DataManagement

class LogicActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logic)

        val loginbtn = findViewById<Button>(R.id.loginbtn)
        val createAcc = findViewById<Button>(R.id.createAccBtn)
        val emailInput = findViewById<EditText>(R.id.email)
        val passwordInput = findViewById<EditText>(R.id.password)

        val email = intent.getStringExtra("EMAIL")
        val password = intent.getStringExtra("PASSWORD")

        if (!email.isNullOrEmpty()) {
            emailInput.setText(email)
        }
        if (!password.isNullOrEmpty()) {
            passwordInput.setText(password)
        }

        loginbtn.setOnClickListener {
            val loginEmail = emailInput.text.toString().trim()
            val loginPassword = passwordInput.text.toString().trim()

            if (validateLogin(loginEmail, loginPassword)) {
                val savedEmail = (application as DataManagement).email
                val savedPassword = (application as DataManagement).password

                if (loginEmail == savedEmail && loginPassword == savedPassword) {
                    Log.e("csit284", "Login successful")

                    (application as DataManagement).username = loginEmail
                    (application as DataManagement).password = loginPassword

                    val intent2 = Intent(this, LandingPageActivity::class.java)
                    intent2.putExtra("USERNAME", loginEmail)
                    startActivity(intent2)
                } else {
                    Log.e("csit284", "Invalid login credentials")
                    emailInput.error = "Invalid email or password"
                    passwordInput.error = "Invalid email or password"
                }
            }
        }

        createAcc.setOnClickListener {
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
