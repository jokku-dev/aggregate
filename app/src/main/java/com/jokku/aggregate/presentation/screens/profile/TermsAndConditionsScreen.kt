package com.jokku.aggregate.presentation.screens.profile

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
import com.jokku.aggregate.R
import com.jokku.aggregate.presentation.nav.Screen
import com.jokku.aggregate.presentation.theme.AggregateTheme
import com.jokku.aggregate.presentation.views.BackButtonAndHeadlineText
import com.jokku.aggregate.presentation.views.CommonColumn

@Composable
fun TermsAndConditionsScreen(
    navController: NavController
) {
    TermsAndConditionsScreenContent(
        onBackButtonClick = {
            navController.popBackStack(route = Screen.Profile.route, inclusive = false)
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
            modifier = Modifier
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