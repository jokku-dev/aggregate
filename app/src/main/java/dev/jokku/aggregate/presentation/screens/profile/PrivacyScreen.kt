package dev.jokku.aggregate.presentation.screens.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.jokku.aggregate.R
import dev.jokku.aggregate.presentation.nav.Screen
import dev.jokku.aggregate.presentation.theme.AggregateTheme
import dev.jokku.aggregate.presentation.views.BackButtonAndHeadlineText
import dev.jokku.aggregate.presentation.views.CommonColumn

@Composable
fun PrivacyScreen(
    navController: NavController
) {
    PrivacyScreenContent(
        onBackButtonClick = {
            navController.popBackStack(route = Screen.Profile.route, inclusive = false)
        }
    )
}

@Composable
fun PrivacyScreenContent(
    onBackButtonClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    CommonColumn {
        BackButtonAndHeadlineText(
            headline = stringResource(R.string.privacy),
            onClick = onBackButtonClick
        )
        Text(
            text = stringResource(id = R.string.terms_and_conditions_text) +
                    stringResource(id = R.string.terms_and_conditions_text),
            style = typography.bodyLarge,
            color = colorScheme.onSecondary,
            modifier = Modifier
                .padding(top = 16.dp)
                .verticalScroll(state = scrollState)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacyScreenPreview() {
    AggregateTheme {
        PrivacyScreenContent(onBackButtonClick = {})
    }
}