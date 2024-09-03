package dev.aggregate.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.presentation.navigation.Screen

@androidx.compose.runtime.Composable
fun AppLanguageScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel<MainProfileViewModel>()
) {
    val state by viewModel.appLanguageState.collectAsStateWithLifecycle()

    AppLanguageScreenContent(
        languages = state.languages,
        onBackButtonClick = { navController.popBackStack(route = dev.aggregate.app.presentation.navigation.Screen.Profile.route, inclusive = false) }
    )
}

@androidx.compose.runtime.Composable
fun AppLanguageScreenContent(
    languages: List<UiAppLanguage>,
    onBackButtonClick: () -> Unit
) {
    dev.aggregate.app.ui.CommonColumn {
        dev.aggregate.app.ui.BackButtonAndHeadlineText(
            headline = androidx.compose.ui.res.stringResource(dev.aggregate.app.R.string.language),
            onClick = onBackButtonClick
        )
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            languages.forEach { language ->
                dev.aggregate.app.ui.PreferencesButton(
                    title = language.language,
                    buttonType = dev.aggregate.app.ui.ButtonType.SELECT,
                    selected = language.selected,
                    onClick = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun AppLanguageScreenPreview() {
    dev.aggregate.app.designsystem.theme.AggregateTheme {
        AppLanguageScreenContent(
            languages = listOf(
                UiAppLanguage(language = dev.aggregate.app.R.string.system, selected = false),
                UiAppLanguage(language = dev.aggregate.app.R.string.english, selected = true),
                UiAppLanguage(language = dev.aggregate.app.R.string.russian, selected = false),
            ),
            onBackButtonClick = {}
        )
    }
}