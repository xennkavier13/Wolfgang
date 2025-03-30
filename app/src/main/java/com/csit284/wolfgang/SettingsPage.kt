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
import android.widget.ListView
import android.widget.RadioButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.csit284.wolfgang.data.SettingsBlock
import com.csit284.wolfgang.helper.SettingsRecyclerViewAdapter

class SettingsPage : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_page)

        val buttonBack = findViewById<ImageButton>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            Log.e("Settings page", "Back button has been pressed")

            val intent = Intent(this, LandingPageActivity::class.java)
            startActivity(intent)
        }

        val listOfBlocks = listOf(
            SettingsBlock(R.drawable.profile_white_icon, "Profile", R.drawable.arrow_white_icon2),
            SettingsBlock(R.drawable.appearance_white_icon, "Appearance", R.drawable.arrow_white_icon2),
            SettingsBlock(R.drawable.send_white_icon, "Send feedback", R.drawable.arrow_white_icon2),
            SettingsBlock(R.drawable.report_white_icon, "Report bug", R.drawable.arrow_white_icon2),
            SettingsBlock(R.drawable.developer_white_icon, "About us", R.drawable.arrow_white_icon2),
            SettingsBlock(R.drawable.logout_white_icon, "Logout", R.drawable.arrow_white_icon2)
        )

        val settingsRecyclerView = findViewById<RecyclerView>(R.id.settingsRecyclerView)
        settingsRecyclerView.layoutManager = LinearLayoutManager(this)
        settingsRecyclerView.adapter = SettingsRecyclerViewAdapter(listOfBlocks) { block ->
            when (block.blockName) {
                "Profile" -> startActivity(Intent(this, ProfilePage::class.java))
                "Appearance" -> displayAppearance()
                "Send feedback" -> displayFeedback()
                "Report bug" -> displayBugReport()
                "About us" -> startActivity(Intent(this, DeveloperPage::class.java))
                "Logout" -> displayLogout()
            }
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

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            alertDialog.dismiss()
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

    private fun displayAppearance() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.appearance_page, null)
        val dialogBuilder = AlertDialog.Builder(this).setView(dialogView)
        val alertDialog = dialogBuilder.create()

        val lightRadio = dialogView.findViewById<RadioButton>(R.id.lightRadio)
        val darkRadio = dialogView.findViewById<RadioButton>(R.id.darkRadio)

        val sharedPreferences = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        lightRadio.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sharedPreferences.edit().putBoolean("darkMode", true).apply()
                applyLightTheme()
            }
        }

        darkRadio.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sharedPreferences.edit().putBoolean("darkMode", false).apply()
                applyDarkTheme()
            }
        }

        val isDarkMode = sharedPreferences.getBoolean("darkMode", false)
        darkRadio.isChecked = isDarkMode
        lightRadio.isChecked = !isDarkMode

        alertDialog.show()
    }

    private fun applyLightTheme() {
        // to be implemented light theme application
    }

    private fun applyDarkTheme() {
        // to be implemented dark theme application
    }
}