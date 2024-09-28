package dev.aggregate.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.aggregate.designsystem.Screen
import dev.aggregate.designsystem.component.BackButtonAndHeadlineText
import dev.aggregate.designsystem.component.CommonColumn
import dev.aggregate.designsystem.theme.AggregateTheme

@Composable
fun TermsAndConditionsScreen(
    navController: NavController
) {
    TermsAndConditionsScreenContent(
        onBackButtonClick = {
            navController.popBackStack(
                route = Screen.Profile.route,
                inclusive = false
            )
        }
    )
}

@Composable
fun TermsAndConditionsScreenContent(
    onBackButtonClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    CommonColumn {
        BackButtonAndHeadlineText(
            headline = stringResource(R.string.terms_and_conditions),
            onClick = onBackButtonClick
        )
        Text(
            text = stringResource(id = R.string.terms_and_conditions_text) +
                    stringResource(id = R.string.terms_and_conditions_text),
            style = typography.bodyLarge,
            color = colorScheme.onSecondary,
            modifier = androidx.compose.ui.Modifier
                .padding(top = 16.dp)
                .verticalScroll(state = scrollState)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TermsAndConditionsPreview() {
    AggregateTheme {
        TermsAndConditionsScreenContent(onBackButtonClick = {})
    }
}
