package com.jokku.aggregate.presentation.views

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jokku.aggregate.R
import com.jokku.aggregate.presentation.theme.AggregateTheme

@Composable
fun HeadlineAndDescriptionText(
    headline: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
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

@Composable
fun BackButtonAndHeadlineText(
    headline: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = colorScheme.onSecondary
            )
        }
        Text(
            text = headline,
            style = typography.headlineLarge,
            color = colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.size(48.dp))
    }
}

@Composable
fun HelpBottomText(
    questionText: String,
    actionText: AnnotatedString,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
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
@Composable
fun HeadlineAndDescriptionTextPreview() {
    AggregateTheme {
        HeadlineAndDescriptionText(headline = "Headline", description = "Long description")
    }
}

@Preview(showBackground = true)
@Composable
fun BackButtonAndHeadlineTextPreview() {
    AggregateTheme {
        BackButtonAndHeadlineText(headline = stringResource(R.string.language), onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HelpBottomTextPreview() {
    AggregateTheme {
        HelpBottomText(
            questionText = "Question text?",
            actionText = AnnotatedString("Action text"),
            onClick = {}
        )
    }
}