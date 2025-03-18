package com.csit284.wolfgang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.app.AlertDialog
import android.widget.ImageView
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.media.Image
import android.provider.ContactsContract.Profile
import android.view.View
import android.widget.LinearLayout

class LandingPageActivity : Activity() {

    private var isShuffleOn = false
    private var isRepeatOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        val signOutTxt: TextView = findViewById(R.id.signout_txt)
        signOutTxt.setOnClickListener {
            showSignOutConfirmation()
        }

        val playBtn: ImageView = findViewById(R.id.playBtn)
        val pauseBtn: ImageView = findViewById(R.id.pauseBtn)
        val shuffleBtn: ImageView = findViewById(R.id.shuffleBtn)
        val repeatBtn: ImageView = findViewById(R.id.repeatBtn)


        val homeBtn: ImageView = findViewById(R.id.homeBtn)
        val searchBtn: ImageView = findViewById(R.id.searchBtn)
        val profileBtn: ImageView = findViewById(R.id.profileBtn)

        homeBtn.setImageResource(R.drawable.home_on_btn)

        playBtn.setOnClickListener {
            animateButton(playBtn)
            playBtn.visibility = ImageView.GONE
            pauseBtn.visibility = ImageView.VISIBLE
        }

        pauseBtn.setOnClickListener {
            animateButton(pauseBtn)
            pauseBtn.visibility = ImageView.GONE
            playBtn.visibility = ImageView.VISIBLE
        }

        profileBtn.setOnClickListener {
            animateButton(profileBtn)
            profileBtn.visibility = ImageView.GONE
            profileBtn.visibility = ImageView.VISIBLE
        }

        shuffleBtn.setOnClickListener {
            isShuffleOn = !isShuffleOn
            shuffleBtn.setImageResource(if (isShuffleOn) R.drawable.shuffling_on_btn else R.drawable.shuffling_btn)
            animateButton(shuffleBtn)
        }

        repeatBtn.setOnClickListener {
            isRepeatOn = !isRepeatOn
            repeatBtn.setImageResource(if (isRepeatOn) R.drawable.repeat_on_btn else R.drawable.repeat_btn)
            animateButton(repeatBtn)
        }

        homeBtn.setOnClickListener {
            setActiveNavButton(homeBtn, searchBtn, profileBtn)
        }
        searchBtn.setOnClickListener {
            setActiveNavButton(searchBtn, homeBtn, profileBtn)
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        profileBtn.setOnClickListener {
            setActiveNavButton(profileBtn, homeBtn, searchBtn)
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }

        setupAlbumClickListeners()
    }

    private fun setupAlbumClickListeners() {
        val album1: LinearLayout = findViewById(R.id.album1)
        val album2: LinearLayout = findViewById(R.id.album2)
        val album3: LinearLayout = findViewById(R.id.album3)
        val album4: LinearLayout = findViewById(R.id.album4)
        val album5: LinearLayout = findViewById(R.id.album5)
        val album6: LinearLayout = findViewById(R.id.album6)
        val album7: LinearLayout = findViewById(R.id.album7)
        val album8: LinearLayout = findViewById(R.id.album8)

        val albumClickListener = View.OnClickListener { view ->
            val intent = Intent(this, AlbumActivity::class.java)

            when (view.id) {
                R.id.album1 -> intent.putExtra("album_name", "Album 1")
                R.id.album2 -> intent.putExtra("album_name", "Album 2")
                R.id.album3 -> intent.putExtra("album_name", "Album 3")
                R.id.album4 -> intent.putExtra("album_name", "Album 4")
                R.id.album5 -> intent.putExtra("album_name", "Album 5")
                R.id.album6 -> intent.putExtra("album_name", "Album 6")
                R.id.album7 -> intent.putExtra("album_name", "Album 7")
                R.id.album8-> intent.putExtra("album_name", "Album 8")

            }

            startActivity(intent)
        }

        album1.setOnClickListener(albumClickListener)
        album2.setOnClickListener(albumClickListener)
        album3.setOnClickListener(albumClickListener)
        album4.setOnClickListener(albumClickListener)
        album5.setOnClickListener(albumClickListener)
        album6.setOnClickListener(albumClickListener)
        album7.setOnClickListener(albumClickListener)
        album8.setOnClickListener(albumClickListener)
    }

    private fun setActiveNavButton(activeBtn: ImageView, btn1: ImageView, btn2: ImageView) {
        activeBtn.setImageResource(getActiveImage(activeBtn.id))
        btn1.setImageResource(getInactiveImage(btn1.id))
        btn2.setImageResource(getInactiveImage(btn2.id))
        animateButton(activeBtn)
    }

    private fun getActiveImage(buttonId: Int): Int {
        return when (buttonId) {
            R.id.homeBtn -> R.drawable.home_on_btn
            R.id.searchBtn -> R.drawable.search_on_btn
            R.id.profileBtn -> R.drawable.profile_on_btn
            else -> throw IllegalArgumentException("Unknown button ID")
        }
    }

    private fun getInactiveImage(buttonId: Int): Int {
        return when (buttonId) {
            R.id.homeBtn -> R.drawable.home_btn
            R.id.searchBtn -> R.drawable.search_btn
            R.id.profileBtn -> R.drawable.profile_btn
            else -> throw IllegalArgumentException("Unknown button ID")
        }
    }


    private fun animateButton(button: ImageView) {
        val scaleDown = ObjectAnimator.ofPropertyValuesHolder(
            button,
            PropertyValuesHolder.ofFloat("scaleX", 0.8f),
            PropertyValuesHolder.ofFloat("scaleY", 0.8f)
        )
        scaleDown.duration = 100
        scaleDown.repeatCount = 1
        scaleDown.repeatMode = ObjectAnimator.REVERSE
        scaleDown.start()
    }

    private fun showSignOutConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Sign Out")
            .setMessage("Are you sure you want to sign out?")
            .setPositiveButton("Yes") { _, _ ->
                val intent = Intent(this, LogicActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}