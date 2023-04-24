package com.jokku.aggregate.ui.screens.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.nav.Screen
import com.jokku.aggregate.ui.theme.AggregateTheme
import com.jokku.aggregate.ui.viewmodel.MainProfileViewModel
import com.jokku.aggregate.ui.viewmodel.ProfileViewModel
import com.jokku.aggregate.ui.views.BackButtonAndHeadlineText
import com.jokku.aggregate.ui.views.BigActionButton
import com.jokku.aggregate.ui.views.CommonColumn
import com.jokku.aggregate.ui.views.PasswordTextField

@Composable
fun ChangePasswordScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel<MainProfileViewModel>()
) {
    ChangePasswordScreenContent(navController = navController)
}

@Composable
fun ChangePasswordScreenContent(
    navController: NavHostController
) {
    var currentPassword by rememberSaveable { mutableStateOf("") }
    var newPassword by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }

    CommonColumn {
        BackButtonAndHeadlineText(
            headline = stringResource(R.string.change_password),
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
            PasswordTextField(
                placeholder = stringResource(id = R.string.current_password),
                imeAction = ImeAction.Next,
                password = currentPassword,
                onPasswordChange = { newCurrentPassword -> currentPassword = newCurrentPassword }
            )
            PasswordTextField(
                placeholder = stringResource(id = R.string.new_password),
                imeAction = ImeAction.Next,
                password = newPassword,
                onPasswordChange = { newNewPassword -> newPassword = newNewPassword }
            )
            PasswordTextField(
                placeholder = stringResource(id = R.string.repeat_new_password),
                imeAction = ImeAction.Done,
                password = repeatPassword,
                onPasswordChange = { newRepeatPassword -> repeatPassword = newRepeatPassword },
                keyboardAction = {
                    navController.navigate(
                        route = Screen.SignIn.route,
                        navOptions = NavOptions.Builder()
                            .setPopUpTo(route = Screen.Profile.route, inclusive = true)
                            .build()
                    )
                }
            )
            BigActionButton(text = stringResource(id = R.string.change_password)) {
                navController.navigate(
                    route = Screen.SignIn.route,
                    navOptions = NavOptions.Builder()
                        .setPopUpTo(route = Screen.Profile.route, inclusive = true)
                        .build()
                )
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun ChangePasswordScreenPreview() {
    AggregateTheme {
        ChangePasswordScreenContent(
            navController = rememberNavController()
        )
    }

}