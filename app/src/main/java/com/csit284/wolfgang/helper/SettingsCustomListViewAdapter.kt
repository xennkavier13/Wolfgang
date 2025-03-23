package com.csit284.wolfgang.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.csit284.wolfgang.R
import com.csit284.wolfgang.data.SettingsBlock

class SettingsCustomListViewAdapter(
    private val context: Context,
    private val settingsListView: List<SettingsBlock>,
    private val onIconClick: (SettingsBlock) -> Unit,
    private val onBlockNameClick: (SettingsBlock) -> Unit,
    private val onArrowClick: (SettingsBlock) -> Unit
): BaseAdapter() {
    override fun getCount(): Int = settingsListView.size

    override fun getItem(position: Int): Any = settingsListView[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.custom_settings_list_view, parent, false)

        val iconBlock = view.findViewById<ImageView>(R.id.iconBlock)
        val blockName = view.findViewById<TextView>(R.id.blockName)
        val arrowIcon = view.findViewById<ImageView>(R.id.arrowIcon)

        val block = settingsListView[position]

        iconBlock.setImageResource(block.icon)
        blockName.setText("${block.blockName}")
        arrowIcon.setImageResource(block.arrowIcon)

        iconBlock.setOnClickListener {
            onIconClick(block)
        }

        view.setOnClickListener {
            onBlockNameClick(block)
        }

        arrowIcon.setOnClickListener {
            onArrowClick(block)
        }

        return view
    }
}