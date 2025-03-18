package com.csit284.wolfgang.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.csit284.wolfgang.R
import com.csit284.wolfgang.data.Album

class CustomListAdapter (
    val context: Context,
    val listOfAlbums: List<Album>
): BaseAdapter() {
    override fun getView(position: Int, contentView: View?, parent: ViewGroup?): View {
        val view = contentView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_albums, parent, false)

        val albums = listOfAlbums[position]

        val album_name = view.findViewById<TextView>(R.id.album_name)
        val artist_name = view.findViewById<TextView>(R.id.artist_name)
        val album_cover = view.findViewById<ImageView>(R.id.album_cover)

        album_name.setText(albums.album_name)
        artist_name.setText(albums.artist_name)
        album_cover.setImageResource(albums.album_cover)

        return view
    }
    override fun getCount(): Int = listOfAlbums.size

    override fun getItem(position: Int): Any = listOfAlbums[position]

    override fun getItemId(position: Int): Long = position.toLong()


}