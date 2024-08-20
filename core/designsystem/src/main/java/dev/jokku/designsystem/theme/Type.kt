package dev.jokku.designsystem.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val SfProText = androidx.compose.ui.text.font.FontFamily(
    androidx.compose.ui.text.font.Font(
        dev.jokku.aggregate.R.font.sf_pro_text_regular,
        androidx.compose.ui.text.font.FontWeight.Normal
    ),
    androidx.compose.ui.text.font.Font(
        dev.jokku.aggregate.R.font.sf_pro_text_medium,
        androidx.compose.ui.text.font.FontWeight.Medium
    ),
    androidx.compose.ui.text.font.Font(
        dev.jokku.aggregate.R.font.sf_pro_text_semibold,
        androidx.compose.ui.text.font.FontWeight.SemiBold
    ),
    androidx.compose.ui.text.font.Font(
        dev.jokku.aggregate.R.font.sf_pro_text_bold,
        androidx.compose.ui.text.font.FontWeight.Bold
    )
)

val Typography = Typography(

    //Top bar headlines
    headlineLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = SfProText,
        fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    //Article headlines
    headlineMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = SfProText,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),

    //Buttons/category tiles - big, author/user, digits
    titleLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = SfProText,
        fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    //Category buttons/tiles - little
    titleSmall = androidx.compose.ui.text.TextStyle(
        fontFamily = SfProText,
        fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),

    //Fields, clickable/help text
    bodyLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = SfProText,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    //Articles body
    bodyMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = SfProText,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),

    //Category over title, user/author info
    labelMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = SfProText,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    //Category over title capitalized
    labelSmall = androidx.compose.ui.text.TextStyle(
        fontFamily = SfProText,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),

    //Badge counter
    displaySmall = androidx.compose.ui.text.TextStyle(
        fontFamily = SfProText,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
        fontSize = 8.sp
    )
)