package dev.aggregate.profile

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
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.presentation.navigation.Screen

@androidx.compose.runtime.Composable
fun TermsAndConditionsScreen(
    navController: NavController
) {
    TermsAndConditionsScreenContent(
        onBackButtonClick = {
            navController.popBackStack(route = dev.aggregate.app.presentation.navigation.Screen.Profile.route, inclusive = false)
        }
    )
}

@androidx.compose.runtime.Composable
fun TermsAndConditionsScreenContent(
    onBackButtonClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    dev.aggregate.app.ui.CommonColumn {
        dev.aggregate.app.ui.BackButtonAndHeadlineText(
            headline = androidx.compose.ui.res.stringResource(dev.aggregate.app.R.string.terms_and_conditions),
            onClick = onBackButtonClick
        )
        Text(
            text = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.terms_and_conditions_text) +
                    androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.terms_and_conditions_text),
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
fun TermsAndConditionsPreview() {
    dev.aggregate.app.designsystem.theme.AggregateTheme {
        TermsAndConditionsScreenContent(onBackButtonClick = {})
    }
}