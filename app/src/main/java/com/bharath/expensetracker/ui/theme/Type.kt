package com.bharath.expensetracker.ui.theme

import androidx.compose.material3.Typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bharath.expensetracker.R

// Set of Material typography styles to start with

val Inter_Regular = FontFamily(
    Font(R.font.inter_regular)
)
val Inter_Light = FontFamily(
    Font(R.font.inter_light)
)
val Inter_SemiBold = FontFamily(
    Font(R.font.inter_semibold)
)
val Inter_Bold = FontFamily(
    Font(R.font.inter_bold)
)

val Lato_Regular= FontFamily(

    Font(R.font.lato_regular)
)
val Lato_LightItalic= FontFamily(
    Font(R.font.lato_lightitalic)
)
val Lato_Bold = FontFamily(
    Font(R.font.lato_bold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)