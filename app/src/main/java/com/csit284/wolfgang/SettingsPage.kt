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
import android.widget.Toast
import kotlin.system.exitProcess

class SettingsPage : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_page)

        val buttonBack = findViewById<ImageButton>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            Log.e("Settings page", "Back button has been pressed")

            Toast.makeText(this, "Back button has been pressed", Toast.LENGTH_LONG).show()

            finish()
        }

        val buttonToProfile = findViewById<ImageButton>(R.id.buttonToProfile)
        buttonToProfile.setOnClickListener {
            Log.e("Settings page", "Profile button has been pressed")

            Toast.makeText(this, "Profile button has been pressed", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }

        val buttonToNotification = findViewById<ImageButton>(R.id.buttonToNotification)
        buttonToNotification.setOnClickListener {
            Log.e("Settings page", "Notification button has been pressed")

            Toast.makeText(this, "Notification button has been pressed", Toast.LENGTH_LONG).show()
        }

        val buttonToLogout = findViewById<ImageButton>(R.id.buttonToLogout)
        buttonToLogout.setOnClickListener {
            Log.e("Settings page", "Logout button has been pressed")

            Toast.makeText(this, "Logout button has been pressed", Toast.LENGTH_LONG).show()

            displayLogout()
        }

        val buttonToSendFeedback = findViewById<ImageButton>(R.id.buttonToSendFeedback)
        buttonToSendFeedback.setOnClickListener {
            Log.e("Settings page", "Feedback button has been pressed")

            Toast.makeText(this, "Feedback button has been pressed", Toast.LENGTH_LONG).show()

            displayFeedback()
        }

        val buttonToReportBug = findViewById<ImageButton>(R.id.buttonToReportBug)
        buttonToReportBug.setOnClickListener {
            Log.e("Settings page", "Report Bug button has been pressed")

            Toast.makeText(this, "Report Bug button has been pressed", Toast.LENGTH_LONG).show()

            displayBugReport()
        }

        val buttonToDeveloperInfo = findViewById<ImageButton>(R.id.buttonToDeveloperInfo)
        buttonToDeveloperInfo.setOnClickListener {
            Log.e("Settings page", "About Developers button has been pressed")

            Toast.makeText(this, "About Developers button has been pressed", Toast.LENGTH_LONG).show()

            val intent = Intent(this, DeveloperPage::class.java)
            startActivity(intent)
        }
    }

    private fun displayLogout() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.logout_page, null)
        val dialogBuilder = AlertDialog.Builder(this).setView(dialogView)
        val alertDialog = dialogBuilder.create()

        val yesButton = dialogView.findViewById<Button>(R.id.yesButton)
        yesButton.setOnClickListener {
            Log.e("Logout page", "Yes button has been pressed")

            Toast.makeText(this, "Logout", Toast.LENGTH_LONG).show()

            finishAffinity()
            exitProcess(0)

        }

        val noButton = dialogView.findViewById<Button>(R.id.noButton)
        noButton.setOnClickListener {
            Log.e("Logout page", "no button has been pressed")

            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun displayFeedback() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.feedback_page, null)
        val dialogBuilder = AlertDialog.Builder(this).setView(dialogView)
        val alertDialog = dialogBuilder.create()

        val userFeedback = dialogView.findViewById<EditText>(R.id.userFeedback)

        val cancelButton = dialogView.findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            Log.e("Feedback page", "Cancel button has been pressed")

            Toast.makeText(this, "Cancel button has been pressed", Toast.LENGTH_LONG).show()

            alertDialog.dismiss()
        }

        val sendButton = dialogView.findViewById<Button>(R.id.sendButton)
        sendButton.setOnClickListener {
            Log.e("Feedback page", "Send button has been pressed")

            if(userFeedback.text.toString().isEmpty()){
                Toast.makeText(this, "Incomplete requirements", Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }

            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun displayBugReport() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.bug_report_page, null)
        val dialogBuilder = AlertDialog.Builder(this).setView(dialogView)
        val alertDialog = dialogBuilder.create()

        val userBugReport = dialogView.findViewById<EditText>(R.id.userBugReport)

        val cancelButton = dialogView.findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            Log.e("Bug report page", "Cancel button has been pressed")

            Toast.makeText(this, "Cancel button has been pressed", Toast.LENGTH_LONG).show()

            alertDialog.dismiss()
        }

        val sendButton = dialogView.findViewById<Button>(R.id.sendButton)
        sendButton.setOnClickListener {
            Log.e("Bug report page", "Send button has been pressed")

            if(userBugReport.text.toString().isEmpty()){
                Toast.makeText(this, "Incomplete requirements", Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }

            alertDialog.dismiss()
        }

        alertDialog.show()
    }
}