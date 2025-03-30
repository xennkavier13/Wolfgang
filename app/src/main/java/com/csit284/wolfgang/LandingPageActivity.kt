package com.csit284.wolfgang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.app.AlertDialog
import android.widget.ImageView
import android.widget.ListView
import com.csit284.wolfgang.data.Album
import com.csit284.wolfgang.helper.CustomListAdapter
import com.csit284.wolfgang.helper.NavigationHelper

class LandingPageActivity : NavigationHelper() {
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

        val profile: ImageView = findViewById(R.id.profile)

        profile.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }

        homeBtn.setImageResource(R.drawable.home_on_btn)

        playBtn.setOnClickListener {
            playBtn.visibility = ImageView.GONE
            pauseBtn.visibility = ImageView.VISIBLE
        }
        pauseBtn.setOnClickListener {
            pauseBtn.visibility = ImageView.GONE
            playBtn.visibility = ImageView.VISIBLE
        }
        shuffleBtn.setOnClickListener {
            isShuffleOn = !isShuffleOn
            shuffleBtn.setImageResource(if (isShuffleOn) R.drawable.shuffling_on_btn else R.drawable.shuffle_off_btn)
        }
        repeatBtn.setOnClickListener {
            isRepeatOn = !isRepeatOn
            repeatBtn.setImageResource(if (isRepeatOn) R.drawable.repeat_on_btn else R.drawable.repeat_off_btn)
        }
        homeBtn.setOnClickListener {
            setActiveNavButton(homeBtn, searchBtn, profileBtn)
        }
        searchBtn.setOnClickListener {
            setActiveNavButton(searchBtn, homeBtn, profileBtn)
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        val username = intent.getStringExtra("USERNAME")
        val email = intent.getStringExtra("EMAIL")
        profileBtn.setOnClickListener {
            setActiveNavButton(profileBtn, homeBtn, searchBtn)
            val intent = Intent(this, ProfilePage::class.java)
            intent.putExtra("USERNAME", username)
            intent.putExtra("EMAIL", email)
            startActivity(intent)
        }

    }
}