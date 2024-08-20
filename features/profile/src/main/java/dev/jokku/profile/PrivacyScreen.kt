package dev.jokku.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.jokku.aggregate.presentation.navigation.Screen
import dev.jokku.designsystem.theme.AggregateTheme

@androidx.compose.runtime.Composable
fun PrivacyScreen(
    navController: NavController
) {
    PrivacyScreenContent(
        onBackButtonClick = {
            navController.popBackStack(route = dev.jokku.aggregate.presentation.navigation.Screen.Profile.route, inclusive = false)
        }
    )
}

@androidx.compose.runtime.Composable
fun PrivacyScreenContent(
    onBackButtonClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    dev.jokku.ui.CommonColumn {
        dev.jokku.ui.BackButtonAndHeadlineText(
            headline = androidx.compose.ui.res.stringResource(dev.jokku.aggregate.R.string.privacy),
            onClick = onBackButtonClick
        )
        Text(
            text = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.terms_and_conditions_text) +
                    androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.terms_and_conditions_text),
            style = typography.bodyLarge,
            color = colorScheme.onSecondary,
            modifier = androidx.compose.ui.Modifier
                .padding(top = 16.dp)
                .verticalScroll(state = scrollState)
        )
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun PrivacyScreenPreview() {
    dev.jokku.designsystem.theme.AggregateTheme {
        PrivacyScreenContent(onBackButtonClick = {})
    }
}