package com.jokku.aggregate.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jokku.aggregate.ui.theme.PurplePrimary
import com.jokku.aggregate.ui.theme.Typography

@Composable
fun BigActionButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(PurplePrimary, Color.White)
    ) {
        Text(
            text = text,
            style = Typography.labelMedium
        )
    }
}