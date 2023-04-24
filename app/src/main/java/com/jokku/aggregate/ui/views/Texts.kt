package com.jokku.aggregate.ui.views

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
import androidx.compose.material3.MaterialTheme
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
import com.jokku.aggregate.ui.theme.AggregateTheme

@Composable
fun HeadlineAndDescriptionText(
    headline: Int,
    description: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = headline),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = stringResource(id = description),
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary
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
        IconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
        Text(
            text = headline,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.size(48.dp))
    }
}

@Composable
fun HelpBottomText(
    questionText: String,
    actionText: AnnotatedString,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$questionText ",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondary
        )
        ClickableText(
            text = actionText,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            onClick = onClick
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun BackButtonAndHeadlineTextPreview() {
    AggregateTheme {
        BackButtonAndHeadlineText(headline = stringResource(R.string.language), onClick = {})
    }
}