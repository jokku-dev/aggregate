package com.jokku.aggregate.ui.screens.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.nav.Screen
import com.jokku.aggregate.ui.theme.AggregateTheme
import com.jokku.aggregate.ui.viewmodel.AppLanguageState
import com.jokku.aggregate.ui.viewmodel.Language
import com.jokku.aggregate.ui.viewmodel.MainProfileViewModel
import com.jokku.aggregate.ui.viewmodel.ProfileViewModel
import com.jokku.aggregate.ui.views.BackButtonAndHeadlineText
import com.jokku.aggregate.ui.views.CommonColumn

@Composable
fun AppLanguageScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel<MainProfileViewModel>()
) {
    val state = viewModel.appLanguageState.collectAsStateWithLifecycle().value

    AppLanguageScreenContent(
        state = state,
        navController = navController
    )
}

@Composable
fun AppLanguageScreenContent(
    state: AppLanguageState,
    navController: NavController,
) {
    CommonColumn {
        BackButtonAndHeadlineText(
            headline = stringResource(R.string.language),
            onClick = {
                navController.navigate(
                    route = Screen.Profile.route,
                    navOptions = NavOptions.Builder()
                        .setPopUpTo(route = Screen.Profile.route, inclusive = true)
                        .build()
                )
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            state.languages.forEach { language ->
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


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun AppLanguageScreenPreview() {
    AggregateTheme {
        AppLanguageScreenContent(
            state = AppLanguageState(
                listOf(
                    Language(R.string.system, true),
                    Language(R.string.english, false),
                    Language(R.string.russian, false)
                )
            ),
            navController = rememberNavController()
        )
    }
}