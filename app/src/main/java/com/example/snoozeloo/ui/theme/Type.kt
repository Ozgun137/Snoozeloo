package com.example.snoozeloo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.snoozeloo.R

// Set of Material typography styles to start with

val MontSerrat = FontFamily(
    Font(
        resId = R.font.montserrat_light,
        weight = FontWeight.Light
    ),

    Font(
        resId = R.font.montserrat_regular,
        weight = FontWeight.Normal
    ),

    Font(
        resId = R.font.montserrat_medium,
        weight = FontWeight.Medium
    ),

    Font(
        resId = R.font.montserrat_semibold,
        weight = FontWeight.SemiBold
    ),

    Font(
        resId = R.font.montserrat_bold,
        weight = FontWeight.Bold
    ),
)

val Typography = Typography(

    bodySmall = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 19.5.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 29.26.sp
    ),


    bodyLarge = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.Medium,
        fontSize = 42.sp,
        lineHeight = 51.2.sp,
    ),

    labelLarge = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.Medium,
        fontSize = 52.sp,
        lineHeight = 63.5.sp
    )

)