package com.csit284.wolfgang.helper

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.widget.ImageView
import com.csit284.wolfgang.LoginActivity
import com.csit284.wolfgang.R

abstract class NavigationHelper : Activity() {
    protected fun setActiveNavButton(activeBtn: ImageView, btn1: ImageView, btn2: ImageView) {
        activeBtn.setImageResource(getActiveImage(activeBtn.id))
        btn1.setImageResource(getInactiveImage(btn1.id))
        btn2.setImageResource(getInactiveImage(btn2.id))
    }

    protected fun getActiveImage(buttonId: Int): Int {
        return when (buttonId) {
            R.id.homeBtn -> R.drawable.home_on_btn
            R.id.searchBtn -> R.drawable.search_on_btn
            R.id.profileBtn -> R.drawable.profile_on_btn
            else -> throw IllegalArgumentException("Unknown button ID")
        }
    }

    protected fun getInactiveImage(buttonId: Int): Int {
        return when (buttonId) {
            R.id.homeBtn -> R.drawable.home_btn
            R.id.searchBtn -> R.drawable.search_btn
            R.id.profileBtn -> R.drawable.profile_btn
            else -> throw IllegalArgumentException("Unknown button ID")
        }
    }

    protected fun showSignOutConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Sign Out")
            .setMessage("Are you sure you want to sign out?")
            .setPositiveButton("Yes") { _, _ ->
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
