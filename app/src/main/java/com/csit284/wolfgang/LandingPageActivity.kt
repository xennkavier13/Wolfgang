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
import android.widget.ListView
import com.csit284.wolfgang.data.Album
import com.csit284.wolfgang.helper.CustomListAdapter

class LandingPageActivity : Activity() {

    private var isShuffleOn = false
    private var isRepeatOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        val listview = findViewById<ListView>(R.id.listview)

        val albums = listOf(
            Album("Album 1", "Artist 1", R.drawable.album_placeholder),
            Album("Album 2", "Artist 2", R.drawable.album_placeholder),
            Album("Album 3", "Artist 3", R.drawable.album_placeholder),
            Album("Album 4", "Artist 4", R.drawable.album_placeholder),
            Album("Album 5", "Artist 5", R.drawable.album_placeholder),
            Album("Album 6", "Artist 6", R.drawable.album_placeholder),
            Album("Album 7", "Artist 7", R.drawable.album_placeholder),
            Album("Album 8", "Artist 8", R.drawable.album_placeholder)

        )

        val adapter = CustomListAdapter(this, albums)
        listview.adapter = adapter

        listview.setOnItemClickListener { _, _, position, _ ->
            val selectedAlbum = albums[position]
            val intent = Intent(this, AlbumActivity::class.java).apply {
                putExtra("album_name", selectedAlbum.album_name)
                putExtra("artist_name", selectedAlbum.artist_name)
                putExtra("album_cover", selectedAlbum.album_cover)
            }
            startActivity(intent)
        }

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