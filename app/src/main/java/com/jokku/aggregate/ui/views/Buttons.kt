package com.jokku.aggregate.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.theme.AggregateTheme

@Composable
fun BigActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = onClick,
        shape = shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorScheme.primary,
            contentColor = colorScheme.surface
        )
    ) {
        Text(
            text = text,
            style = typography.titleLarge
        )
    }
}

@Composable
fun SignInWithButton(
    painter: Painter,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = onClick,
        shape = shapes.medium,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = colorScheme.surface,
            contentColor = colorScheme.onBackground
        ),
        border = BorderStroke(
            width = 1.dp,
            color = colorScheme.secondary
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painter,
                contentDescription = text
            )
            Text(
                text = text,
                style = typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview
@Composable
fun BigActionButtonPreview() {
    AggregateTheme {
        BigActionButton(
            text = "Get Started",
            onClick = {}
        )
    }
}

@Preview
@Composable
fun SignInButtonPreview() {
    AggregateTheme {
        SignInWithButton(
            painter = painterResource(id = R.drawable.ic_logo_google),
            text = stringResource(id = R.string.sign_in_with_google),
            onClick = {}
        )
    }
}