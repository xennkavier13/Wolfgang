package com.csit284.wolfgang.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.csit284.wolfgang.R
import com.csit284.wolfgang.data.SettingsBlock

class SettingsRecyclerViewAdapter(
    private val listOfBlocks: List<SettingsBlock>,
    private val onClick: (SettingsBlock) -> Unit
): RecyclerView.Adapter<SettingsRecyclerViewAdapter.ItemViewHolder>(){
        class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val iconBlock = view.findViewById<ImageView>(R.id.iconBlock)
            val blockName = view.findViewById<TextView>(R.id.blockName)
            val arrowIcon = view.findViewById<ImageView>(R.id.arrowIcon)
        }

        override fun onCreateViewHolder(
            parentt: ViewGroup,
            viewType: Int
        ): ItemViewHolder {
            val view = LayoutInflater.from(parentt.context)
                .inflate(R.layout.settings_recycler_view, parentt, false)

            return ItemViewHolder(view)
        }

        override fun onBindViewHolder(
            holder: ItemViewHolder,
            position: Int
        ){
            val item = listOfBlocks[position]

            holder.iconBlock.setImageResource(item.icon)
            holder.blockName.setText(item.blockName)
            holder.arrowIcon.setImageResource(item.arrowIcon)

            holder.itemView.setOnClickListener {
                onClick(item)
            }
        }

        override fun getItemCount(): Int = listOfBlocks.size
}