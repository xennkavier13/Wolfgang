package com.csit284.wolfgang

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView


class AlbumActivity : Activity() {

    private var isShuffleOn = false
    private var isRepeatOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        val playBtn: ImageView = findViewById(R.id.playBtn)
        val pauseBtn: ImageView = findViewById(R.id.pauseBtn)
        val shuffleBtn: ImageView = findViewById(R.id.shuffleBtn)
        val repeatBtn: ImageView = findViewById(R.id.repeatBtn)


        val homeBtn: ImageView = findViewById(R.id.homeBtn)
        val searchBtn: ImageView = findViewById(R.id.searchBtn)
        val profileBtn: ImageView = findViewById(R.id.profileBtn)

        

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
            finish()
        }

        searchBtn.setOnClickListener {
            setActiveNavButton(searchBtn, homeBtn, profileBtn)
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        profileBtn.setOnClickListener {
            setActiveNavButton(profileBtn, homeBtn, searchBtn)
        }


    }


    private fun setActiveNavButton(activeBtn: ImageView, btn1: ImageView, btn2: ImageView) {
        activeBtn.setImageResource(getActiveImage(activeBtn.id))
        btn1.setImageResource(getInactiveImage(btn1.id))
        btn2.setImageResource(getInactiveImage(btn2.id))
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

}