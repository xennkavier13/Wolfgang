package com.csit284.wolfgang.data

import com.csit284.wolfgang.R

data class SettingsBlock(
    var icon: Int = R.drawable.settings_white_icon,
    var blockName: String = "",
    var arrowIcon: Int = R.drawable.arrow_white_icon2,
    var action: String = ""
)