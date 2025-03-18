package com.csit284.wolfgang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.csit284.wolfgang.app.DataManagement

class CreateAccountActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val username = findViewById<EditText>(R.id.username)
        val createAcc = findViewById<Button>(R.id.createAccBtn)
        val emailInput = findViewById<EditText>(R.id.email)
        val passwordInput = findViewById<EditText>(R.id.password)
        val passwordConfirmInput = findViewById<EditText>(R.id.passwordConfirm)

        createAcc.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val confirmPassword = passwordConfirmInput.text.toString().trim()

            if (!emailValidate(emailInput, email)) return@setOnClickListener
            if (!passwordValidate(passwordInput, password)) return@setOnClickListener
            if (!passwordConfirmValidate(passwordConfirmInput, password, confirmPassword)) return@setOnClickListener

            (application as DataManagement).username = username.text.toString()
            (application as DataManagement).email = email
            (application as DataManagement).password = password

            Log.e("csit284", "Account created successfully!")
            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LogicActivity::class.java)
            intent.putExtra("EMAIL", email)
            intent.putExtra("PASSWORD", password)
            startActivity(intent)
        }
    }

    private fun passwordValidate(passwordInput: EditText, password: String): Boolean {
        if (password.isEmpty()) {
            passwordInput.error = "Please enter a password"
            return false
        }
        if (password.length < 8) {
            passwordInput.error = "Password must be 8 or more characters"
            return false
        }
        return true
    }

    private fun passwordConfirmValidate(passwordConfirmInput: EditText, password: String, confirmPassword: String): Boolean {
        if (confirmPassword.isEmpty()) {
            passwordConfirmInput.error = "Please confirm your password"
            return false
        }
        if (password != confirmPassword) {
            passwordConfirmInput.error = "Passwords do not match"
            return false
        }
        return true
    }

    private fun emailValidate(emailInput: EditText, email: String): Boolean {
        if (email.isEmpty()) {
            emailInput.error = "Please enter an email"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.error = "Please enter a valid email"
            return false
        }
        return true
    }
}
