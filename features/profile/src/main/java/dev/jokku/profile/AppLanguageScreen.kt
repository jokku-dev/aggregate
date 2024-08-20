package dev.jokku.profile

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
import dev.jokku.aggregate.presentation.navigation.Screen
import dev.jokku.designsystem.theme.AggregateTheme

@androidx.compose.runtime.Composable
fun AppLanguageScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel<MainProfileViewModel>()
) {
    val state by viewModel.appLanguageState.collectAsStateWithLifecycle()

    AppLanguageScreenContent(
        languages = state.languages,
        onBackButtonClick = { navController.popBackStack(route = dev.jokku.aggregate.presentation.navigation.Screen.Profile.route, inclusive = false) }
    )
}

@androidx.compose.runtime.Composable
fun AppLanguageScreenContent(
    languages: List<UiAppLanguage>,
    onBackButtonClick: () -> Unit
) {
    dev.jokku.ui.CommonColumn {
        dev.jokku.ui.BackButtonAndHeadlineText(
            headline = androidx.compose.ui.res.stringResource(dev.jokku.aggregate.R.string.language),
            onClick = onBackButtonClick
        )
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            languages.forEach { language ->
                dev.jokku.ui.PreferencesButton(
                    title = language.language,
                    buttonType = dev.jokku.ui.ButtonType.SELECT,
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
    dev.jokku.designsystem.theme.AggregateTheme {
        AppLanguageScreenContent(
            languages = listOf(
                UiAppLanguage(language = dev.jokku.aggregate.R.string.system, selected = false),
                UiAppLanguage(language = dev.jokku.aggregate.R.string.english, selected = true),
                UiAppLanguage(language = dev.jokku.aggregate.R.string.russian, selected = false),
            ),
            onBackButtonClick = {}
        )
    }
}