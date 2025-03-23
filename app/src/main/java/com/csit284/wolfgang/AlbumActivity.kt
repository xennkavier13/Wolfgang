package com.csit284.wolfgang

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import com.csit284.wolfgang.helper.NavigationHelper


class AlbumActivity : NavigationHelper() {

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

        val listview = findViewById<ListView>(R.id.listview)

        val trackList = listOf("Track 1", "Track 2", "Track 3", "Track 4", "Track 5",
                                "Track 6", "Track 7", "Track 8", "Track 9", "Track 10" )

        val adapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1,
            trackList
        )

        listview.adapter = adapter

        listview.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                this,
                "${trackList[position]}  is playing",
                Toast.LENGTH_LONG
            ).show()
        }

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

}