package com.csit284.wolfgang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.csit284.wolfgang.helper.NavigationHelper


class SearchActivity : NavigationHelper() {

    private var isShuffleOn = false
    private var isRepeatOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        val playBtn: ImageView = findViewById(R.id.playBtn)
        val pauseBtn: ImageView = findViewById(R.id.pauseBtn)
        val shuffleBtn: ImageView = findViewById(R.id.shuffleBtn)
        val repeatBtn: ImageView = findViewById(R.id.repeatBtn)


        val homeBtn: ImageView = findViewById(R.id.homeBtn)
        val searchBtn: ImageView = findViewById(R.id.searchBtn)
        val profileBtn: ImageView = findViewById(R.id.profileBtn)

        searchBtn.setImageResource(R.drawable.search_on_btn)

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
            val intent = Intent(this, LandingPageActivity::class.java)
            startActivity(intent)
        }
        searchBtn.setOnClickListener {
            setActiveNavButton(searchBtn, homeBtn, profileBtn)
        }
        profileBtn.setOnClickListener {
            setActiveNavButton(profileBtn, homeBtn, searchBtn)
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }

    }
}