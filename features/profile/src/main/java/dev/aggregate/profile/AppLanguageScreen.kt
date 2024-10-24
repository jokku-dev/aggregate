package dev.aggregate.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.aggregate.designsystem.Screen
import dev.aggregate.designsystem.component.BackButtonAndHeadlineText
import dev.aggregate.designsystem.component.ButtonType
import dev.aggregate.designsystem.component.CommonColumn
import dev.aggregate.designsystem.component.PreferencesButton
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.model.ui.UiAppLanguage
import dev.aggregate.model.ui.UiText

@Composable
fun AppLanguageScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel<MainProfileViewModel>()
) {
    val state by viewModel.profileState.collectAsStateWithLifecycle()

    AppLanguageScreenContent(
        languages = state.languages,
        onBackButtonClick = {
            navController.popBackStack(
                route = Screen.Profile.route,
                inclusive = false
            )
        }
    )
}

@Composable
fun AppLanguageScreenContent(
    languages: List<UiAppLanguage>,
    onBackButtonClick: () -> Unit
) {
    CommonColumn {
        BackButtonAndHeadlineText(
            headline = stringResource(R.string.language),
            onClick = onBackButtonClick
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            languages.forEach { language ->
                PreferencesButton(
                    title = language.language.asString(),
                    buttonType = ButtonType.SELECT,
                    selected = language.selected,
                    onClick = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppLanguageScreenPreview() {
    AggregateTheme {
        AppLanguageScreenContent(
            languages = listOf(
                UiAppLanguage(language = UiText.StringResource(R.string.system), selected = false),
                UiAppLanguage(language = UiText.StringResource(R.string.english), selected = true),
                UiAppLanguage(language = UiText.StringResource(R.string.russian), selected = false),
            ),
            onBackButtonClick = {}
        )
    }
}
