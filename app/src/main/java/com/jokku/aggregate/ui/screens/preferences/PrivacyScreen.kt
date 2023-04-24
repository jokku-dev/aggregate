package com.jokku.aggregate.ui.screens.preferences

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.nav.Screen
import com.jokku.aggregate.ui.theme.AggregateTheme
import com.jokku.aggregate.ui.views.BackButtonAndHeadlineText
import com.jokku.aggregate.ui.views.CommonColumn

@Composable
fun PrivacyScreen(
    navController: NavController
) {
    val scrollState = rememberScrollState()

    CommonColumn {
        BackButtonAndHeadlineText(
            headline = stringResource(R.string.privacy),
            onClick = {
                navController.navigate(
                    route = Screen.Profile.route,
                    navOptions = NavOptions.Builder()
                        .setPopUpTo(route = Screen.Profile.route, inclusive = true)
                        .build()
                )
            }
        )
        Text(
            text = stringResource(id = R.string.terms_and_conditions_text),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .padding(top = 16.dp)
                .verticalScroll(state = scrollState)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PrivacyScreenPreview() {
    AggregateTheme {
        PrivacyScreen(
            navController = rememberNavController()
        )
    }
}