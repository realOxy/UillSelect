package com.oxy.uillselect.ui.cl

import androidx.compose.ui.graphics.Color

data class Theme(
    val primary: Color,
    val onPrimary: Color,
    val background: Color,
    val onBackground: Color,
    val agree: Color,
    val onAgree: Color,
    val against: Color,
    val onAgainst: Color,
)

object ThemeDefaults {
    val Light = Theme(
        primary = Color(0xff837fc9),
        onPrimary = Color.White,
        background = Color(0xfffefefe),
        onBackground = Color(0xff2a2a2a),
        agree = Color(0xff9f2d20),
        onAgree = Color.White,
        against = Color(0xff004680),
        onAgainst = Color.White
    )
    val Dark = Theme(
        primary = Color(0xff837fc9),
        onPrimary = Color.White,
        background = Color(0xff181818),
        onBackground = Color.White,
        agree = Color(0xff9f2d20),
        onAgree = Color.White,
        against = Color(0xff004680),
        onAgainst = Color.White
    )
}