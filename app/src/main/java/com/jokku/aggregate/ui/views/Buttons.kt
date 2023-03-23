package com.jokku.aggregate.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jokku.aggregate.R

@Composable
fun BigActionButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun SignInWithButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(0.08f),
                painter = painter,
                contentDescription = text
            )
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(
                modifier = Modifier.fillMaxWidth(0.08f)
            )
        }
    }
}


@Preview(apiLevel = 30, showBackground = true)
@Composable
fun BigActionButtonPreview() {
    BigActionButton(text = "Get Started") {}
}

@Preview(apiLevel = 30, showBackground = true, device = "spec:parent=pixel_5")
@Composable
fun SignInButtonPreview() {
    SignInWithButton(
        painter = painterResource(id = R.drawable.ic_logo_google),
        text = stringResource(id = R.string.sign_in_with_google)
    ) {}
}