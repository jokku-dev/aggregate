package com.jokku.aggregate.ui.screens.preferences

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
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.nav.Screen
import com.jokku.aggregate.ui.theme.AggregateTheme
import com.jokku.aggregate.ui.viewmodel.Language
import com.jokku.aggregate.ui.viewmodel.MainProfileViewModel
import com.jokku.aggregate.ui.viewmodel.ProfileViewModel
import com.jokku.aggregate.ui.views.BackButtonAndHeadlineText
import com.jokku.aggregate.ui.views.ButtonType
import com.jokku.aggregate.ui.views.CommonColumn
import com.jokku.aggregate.ui.views.PreferencesButton

@Composable
fun AppLanguageScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel<MainProfileViewModel>()
) {
    val state by viewModel.appLanguageState.collectAsStateWithLifecycle()

    AppLanguageScreenContent(
        languages = state.languages,
        onBackButtonClick = { navController.popBackStack(route = Screen.Profile.route, inclusive = false) }
    )
}

@Composable
fun AppLanguageScreenContent(
    languages: List<Language>,
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
                    title = language.language,
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
                Language(language = R.string.system, selected = false),
                Language(language = R.string.english, selected = true),
                Language(language = R.string.russian, selected = false),
            ),
            onBackButtonClick = {}
        )
    }
}