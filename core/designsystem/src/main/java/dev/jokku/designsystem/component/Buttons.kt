package dev.jokku.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.jokku.aggregate.presentation.theme.AggregateTheme

enum class ButtonType {
    DIRECTION, SIGN_IN_OUT, SWITCH, SELECT
}

@androidx.compose.runtime.Composable
fun BigActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
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

@androidx.compose.runtime.Composable
fun SignInWithButton(
    painter: androidx.compose.ui.graphics.painter.Painter,
    text: String,
    onClick: () -> Unit,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
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
            modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Image(
                modifier = androidx.compose.ui.Modifier.size(24.dp),
                painter = painter,
                contentDescription = text
            )
            Text(
                text = text,
                style = typography.titleLarge,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                softWrap = false
            )
            Spacer(
                modifier = androidx.compose.ui.Modifier.size(24.dp)
            )
        }
    }
}

@androidx.compose.runtime.Composable
fun PreferencesButton(
    title: Int,
    buttonType: ButtonType,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
    onClick: () -> Unit = {},
    checked: Boolean = false,
    onCheckedChanged: (Boolean) -> Unit = {},
    selected: Boolean = false,
    signedIn: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(shapes.medium)
            .background(color = if (selected) colorScheme.primary else colorScheme.secondary)
            .padding(horizontal = 24.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Text(
            text = androidx.compose.ui.res.stringResource(id = title),
            style = typography.titleLarge,
            color = if (selected) colorScheme.surface else colorScheme.onBackground,
        )
        when (buttonType) {
            ButtonType.DIRECTION -> {
                Icon(
                    imageVector = androidx.compose.ui.graphics.vector.ImageVector.vectorResource(dev.jokku.aggregate.R.drawable.ic_arrow_right),
                    contentDescription = androidx.compose.ui.res.stringResource(id = title),
                    tint = colorScheme.onBackground
                )
            }

            ButtonType.SIGN_IN_OUT -> {
                Icon(
                    imageVector = if (signedIn) androidx.compose.ui.graphics.vector.ImageVector.vectorResource(
                        dev.jokku.aggregate.R.drawable.ic_logout
                    )
                    else androidx.compose.ui.graphics.vector.ImageVector.vectorResource(dev.jokku.aggregate.R.drawable.ic_login),
                    contentDescription = androidx.compose.ui.res.stringResource(id = title),
                    tint = colorScheme.onBackground
                )
            }

            ButtonType.SWITCH -> {
                Switch(
                    checked = checked,
                    onCheckedChange = onCheckedChanged,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colorScheme.surface,
                        checkedTrackColor = colorScheme.primary,
                        uncheckedThumbColor = colorScheme.surface,
                        uncheckedTrackColor = colorScheme.onBackground
                    )
                )
            }

            ButtonType.SELECT -> {
                if (selected)
                    Icon(
                        imageVector = androidx.compose.ui.graphics.vector.ImageVector.vectorResource(id = dev.jokku.aggregate.R.drawable.ic_round_check),
                        contentDescription = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.selected),
                        tint = colorScheme.surface
                    )
                else
                    Spacer(modifier = androidx.compose.ui.Modifier.size(24.dp))
            }
        }
    }
}

@Preview
@androidx.compose.runtime.Composable
fun BigActionButtonPreview() {
    dev.jokku.aggregate.presentation.theme.AggregateTheme {
        BigActionButton(
            text = "Get Started",
            onClick = {}
        )
    }
}

@Preview
@androidx.compose.runtime.Composable
fun SignInButtonPreview() {
    dev.jokku.aggregate.presentation.theme.AggregateTheme {
        SignInWithButton(
            painter = androidx.compose.ui.res.painterResource(id = dev.jokku.aggregate.R.drawable.ic_logo_google),
            text = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.sign_in_with_google),
            onClick = {}
        )
    }
}

@Preview
@androidx.compose.runtime.Composable
fun PreferencesButtonPreview() {
    dev.jokku.aggregate.presentation.theme.AggregateTheme {
        Column {
            PreferencesButton(
                title = dev.jokku.aggregate.R.string.notifications,
                onClick = {},
                buttonType = ButtonType.SWITCH,
                checked = true
            )
            PreferencesButton(
                title = dev.jokku.aggregate.R.string.notifications,
                onClick = {},
                buttonType = ButtonType.SWITCH,
                checked = false
            )
            PreferencesButton(
                title = dev.jokku.aggregate.R.string.language,
                onClick = {},
                buttonType = ButtonType.DIRECTION
            )
            PreferencesButton(
                title = dev.jokku.aggregate.R.string.sign_in,
                onClick = {},
                buttonType = ButtonType.SIGN_IN_OUT
            )
            PreferencesButton(
                title = dev.jokku.aggregate.R.string.system,
                onClick = {},
                buttonType = ButtonType.SELECT
            )
            PreferencesButton(
                title = dev.jokku.aggregate.R.string.system,
                onClick = {},
                buttonType = ButtonType.SELECT,
                selected = true
            )
        }
    }
}