package com.csit284.wolfgang

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class ProfilePage : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        val buttonBack = findViewById<ImageButton>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            Log.e("Profile page", "Back button has been pressed")
            val intent = Intent(this, LandingPageActivity::class.java)
            startActivity(intent)
        }

        val buttonEditProfile = findViewById<ImageButton>(R.id.buttonEditProfile)
        buttonEditProfile.setOnClickListener {
            Log.e("Profile page", "Edit Profile button has been pressed")
            displayProfileEdit()
        }

        // local database implementation (sharedPreferences)
        val sharedPreferences = getSharedPreferences("login_data", Context.MODE_PRIVATE)

        val storedUserName = sharedPreferences.getString("USERNAME", "")
        val storedEmail = sharedPreferences.getString("EMAIL", "")
        val storedPhoneNum = sharedPreferences.getString("PHONE", "")

        findViewById<TextView>(R.id.userName).text = storedUserName
        findViewById<TextView>(R.id.emailAcc).text = storedEmail
        findViewById<TextView>(R.id.phoneNum).text = storedPhoneNum

        val buttonSettings = findViewById<ImageButton>(R.id.buttonSettings)
        buttonSettings.setOnClickListener {
            Log.e("Profile page", "Settings Profile button has been pressed")
            val intent = Intent(this, SettingsPage::class.java)
            startActivity(intent)
        }
    }

    private fun displayProfileEdit() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.edit_profile_page, null)
        val dialogBuilder = AlertDialog.Builder(this).setView(dialogView)
        val alertDialog = dialogBuilder.create()

        val nameEditText = dialogView.findViewById<EditText>(R.id.userName)
        val emailEditText = dialogView.findViewById<EditText>(R.id.email)
        val mobileNumberEditText = dialogView.findViewById<EditText>(R.id.mobileNumber)

        val sharedPreferences = getSharedPreferences("login_data", Context.MODE_PRIVATE)

        val currentName = sharedPreferences.getString("USERNAME", "")
        val currentEmail = sharedPreferences.getString("EMAIL", "")
        val currentMobileNumber = sharedPreferences.getString("PHONE", "")

        nameEditText.setText(currentName)
        emailEditText.setText(currentEmail)
        mobileNumberEditText.setText(currentMobileNumber)

        val cancelButton = dialogView.findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            Log.e("Edit Profile page", "Back button has been pressed")
            alertDialog.dismiss()
        }

        val saveButton = dialogView.findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            Log.e("Edit Profile page", "Save button has been pressed")

            if (nameEditText.text.toString().isEmpty()) {
                Toast.makeText(this, "Incomplete requirements", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val newName = nameEditText.text.toString()
            val electroMail = emailEditText.text.toString()
            val mobNum = mobileNumberEditText.text.toString()

            val editor = sharedPreferences.edit()
            editor.putString("USERNAME", newName)
            editor.putString("EMAIL", electroMail)
            editor.putString("PHONE", mobNum)
            editor.apply()

            findViewById<TextView>(R.id.userName).text = newName
            findViewById<TextView>(R.id.phoneNum).text = mobNum
            findViewById<TextView>(R.id.emailAcc).text = electroMail

            Log.e("Profile Edit", "Full Name: $newName")
            Log.e("Profile Edit", "Phone Number : $mobNum")
            Log.e("Profile Edit", "Email: $electroMail")

            Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }
        alertDialog.show()
    }
}