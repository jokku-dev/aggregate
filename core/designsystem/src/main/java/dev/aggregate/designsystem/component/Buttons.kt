package dev.aggregate.designsystem.component

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.aggregate.designsystem.R
import dev.aggregate.designsystem.theme.AggregateTheme

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
                textAlign = TextAlign.Center,
                softWrap = false
            )
            Spacer(
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun PreferencesButton(
    title: String,
    buttonType: ButtonType,
    modifier: Modifier = Modifier,
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
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = typography.titleLarge,
            color = if (selected) colorScheme.surface else colorScheme.onBackground,
        )
        when (buttonType) {
            ButtonType.DIRECTION -> {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_right),
                    contentDescription = title,
                    tint = colorScheme.onBackground
                )
            }

            ButtonType.SIGN_IN_OUT -> {
                Icon(
                    imageVector = if (signedIn) {
                        ImageVector.vectorResource(R.drawable.ic_logout)
                    } else {
                        ImageVector.vectorResource(R.drawable.ic_login)
                    },
                    contentDescription = title,
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
                if (selected) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_check),
                        contentDescription = stringResource(id = R.string.selected),
                        tint = colorScheme.surface
                    )
                } else {
                    Spacer(modifier = Modifier.size(24.dp))
                }
            }
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
            painter = androidx.compose.ui.res.painterResource(id = R.drawable.ic_logo_google),
            text = stringResource(id = R.string.sign_in_with_google),
            onClick = {}
        )
    }
}

@Preview
@Composable
fun PreferencesButtonPreview() {
    AggregateTheme {
        Column {
            PreferencesButton(
                title = stringResource(R.string.notifications),
                onClick = {},
                buttonType = ButtonType.SWITCH,
                checked = true
            )
            PreferencesButton(
                title = stringResource(R.string.notifications),
                onClick = {},
                buttonType = ButtonType.SWITCH,
                checked = false
            )
            PreferencesButton(
                title = stringResource(R.string.language),
                onClick = {},
                buttonType = ButtonType.DIRECTION
            )
            PreferencesButton(
                title = stringResource(R.string.sign_in),
                onClick = {},
                buttonType = ButtonType.SIGN_IN_OUT
            )
            PreferencesButton(
                title = stringResource(R.string.system),
                onClick = {},
                buttonType = ButtonType.SELECT
            )
            PreferencesButton(
                title = stringResource(R.string.system),
                onClick = {},
                buttonType = ButtonType.SELECT,
                selected = true
            )
        }
    }
}

enum class ButtonType {
    DIRECTION,
    SIGN_IN_OUT,
    SWITCH,
    SELECT
}
