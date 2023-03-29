package com.jokku.aggregate.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jokku.aggregate.R

val SfProText = FontFamily(
    Font(R.font.sf_pro_text_regular, FontWeight.Normal),
    Font(R.font.sf_pro_text_medium, FontWeight.Medium),
    Font(R.font.sf_pro_text_semibold, FontWeight.SemiBold),
    Font(R.font.sf_pro_text_bold, FontWeight.Bold)
)

val Typography = Typography(

    //Top bar headlines
    headlineLarge = TextStyle(
        fontFamily = SfProText,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    //Article headlines
    headlineMedium = TextStyle(
        fontFamily = SfProText,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),

    //Buttons/category tiles - big, author/user, digits
    titleLarge = TextStyle(
        fontFamily = SfProText,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    //Category buttons/tiles - little
    titleSmall = TextStyle(
        fontFamily = SfProText,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),

    //Fields, clickable/help text
    bodyLarge = TextStyle(
        fontFamily = SfProText,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    //Articles body
    bodyMedium = TextStyle(
        fontFamily = SfProText,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),

    //Category over title, user/author info
    labelMedium = TextStyle(
        fontFamily = SfProText,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    //Category over title capitalized
    labelSmall = TextStyle(
        fontFamily = SfProText,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)