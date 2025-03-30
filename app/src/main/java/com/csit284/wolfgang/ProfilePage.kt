package com.csit284.wolfgang

import android.app.Activity
import android.app.AlertDialog
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

        val userName = intent.getStringExtra("USERNAME")
        if (userName != null) {
            findViewById<TextView>(R.id.userName).text = userName
        }

        val email = intent.getStringExtra("EMAIL")
        if (email != null) {
            findViewById<TextView>(R.id.emailAcc).text = email        }

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

        val name = dialogView.findViewById<EditText>(R.id.userName)
        val email = dialogView.findViewById<EditText>(R.id.email)
        val mobileNumber = dialogView.findViewById<EditText>(R.id.mobileNumber)

        val cancelButton = dialogView.findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            Log.e("Edit Profile page", "Back button has been pressed")

            alertDialog.dismiss()
        }

        val saveButton = dialogView.findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            Log.e("Edit Profile page", "Save button has been pressed")

            if(name.text.toString().isEmpty() || email.text.toString().isEmpty() || mobileNumber.text.toString().isEmpty()){
                Toast.makeText(this, "Incomplete requirements", Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }

            val newName = name.text.toString()
            val electroMail = email.text.toString()
            val mobNum = mobileNumber.text.toString()

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