package dev.jokku.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.jokku.aggregate.presentation.theme.AggregateTheme

@androidx.compose.runtime.Composable
fun HeadlineAndDescriptionText(
    headline: String,
    description: String,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = androidx.compose.ui.Alignment.Start
    ) {
        Text(
            text = headline,
            style = typography.headlineLarge,
            color = colorScheme.onSurfaceVariant
        )
        Text(
            text = description,
            style = typography.bodyMedium,
            color = colorScheme.onSecondary
        )
    }
}

@androidx.compose.runtime.Composable
fun BackButtonAndHeadlineText(
    headline: String,
    onClick: () -> Unit,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = androidx.compose.ui.graphics.vector.ImageVector.vectorResource(id = dev.jokku.aggregate.R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = colorScheme.onSecondary
            )
        }
        Text(
            text = headline,
            style = typography.headlineLarge,
            color = colorScheme.onSurfaceVariant
        )
        Spacer(modifier = androidx.compose.ui.Modifier.size(48.dp))
    }
}

@androidx.compose.runtime.Composable
fun HelpBottomText(
    questionText: String,
    actionText: androidx.compose.ui.text.AnnotatedString,
    onClick: () -> Unit,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$questionText ",
            style = typography.bodyLarge,
            color = colorScheme.onSecondary
        )
        ClickableText(
            text = actionText,
            style = typography.bodyLarge.copy(
                color = colorScheme.onSurfaceVariant
            ),
            onClick = { onClick() },
        )
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun HeadlineAndDescriptionTextPreview() {
    dev.jokku.aggregate.presentation.theme.AggregateTheme {
        HeadlineAndDescriptionText(headline = "Headline", description = "Long description")
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun BackButtonAndHeadlineTextPreview() {
    dev.jokku.aggregate.presentation.theme.AggregateTheme {
        BackButtonAndHeadlineText(
            headline = androidx.compose.ui.res.stringResource(dev.jokku.aggregate.R.string.language),
            onClick = {})
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun HelpBottomTextPreview() {
    dev.jokku.aggregate.presentation.theme.AggregateTheme {
        HelpBottomText(
            questionText = "Question text?",
            actionText = androidx.compose.ui.text.AnnotatedString("Action text"),
            onClick = {}
        )
    }
}