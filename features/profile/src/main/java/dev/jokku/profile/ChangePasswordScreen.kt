package dev.jokku.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.jokku.aggregate.presentation.navigation.Screen
import dev.jokku.designsystem.theme.AggregateTheme
import dev.jokku.ui.BigActionButton

@androidx.compose.runtime.Composable
fun ChangePasswordScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel<MainProfileViewModel>()
) {
    var currentPassword by androidx.compose.runtime.saveable.rememberSaveable {
        androidx.compose.runtime.mutableStateOf(
            ""
        )
    }
    var newPassword by androidx.compose.runtime.saveable.rememberSaveable {
        androidx.compose.runtime.mutableStateOf(
            ""
        )
    }
    var repeatedPassword by androidx.compose.runtime.saveable.rememberSaveable {
        androidx.compose.runtime.mutableStateOf(
            ""
        )
    }

    ChangePasswordScreenContent(
        currentPassword = currentPassword,
        newPassword = newPassword,
        repeatedPassword = repeatedPassword,
        onCurrentPasswordChange = { newCurrentPassword -> currentPassword = newCurrentPassword },
        onNewPasswordChange = { newNewPassword -> newPassword = newNewPassword },
        onRepeatedPasswordChange = { newRepeatedPassword -> repeatedPassword = newRepeatedPassword },
        onBackButtonClick = { navController.popBackStack(route = dev.jokku.aggregate.presentation.navigation.Screen.Profile.route, inclusive = false) },
        onButtonClick = {
            navController.popBackStack(route = dev.jokku.aggregate.presentation.navigation.Screen.Profile.route, inclusive = false)
            navController.navigate(route = dev.jokku.aggregate.presentation.navigation.Screen.SignIn.route)
        }
    )
}

@androidx.compose.runtime.Composable
fun ChangePasswordScreenContent(
    currentPassword: String,
    newPassword: String,
    repeatedPassword: String,
    onCurrentPasswordChange: (String) -> Unit,
    onNewPasswordChange: (String) -> Unit,
    onRepeatedPasswordChange: (String) -> Unit,
    onBackButtonClick: () -> Unit,
    onButtonClick: () -> Unit
) {
    dev.jokku.ui.CommonColumn {
        dev.jokku.ui.BackButtonAndHeadlineText(
            headline = androidx.compose.ui.res.stringResource(dev.jokku.aggregate.R.string.change_password),
            onClick = onBackButtonClick
        )
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            dev.jokku.ui.PasswordTextField(
                placeholder = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.current_password),
                imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                password = currentPassword,
                onPasswordChange = { newCurrentPassword ->
                    onCurrentPasswordChange(
                        newCurrentPassword
                    )
                }
            )
            dev.jokku.ui.PasswordTextField(
                placeholder = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.new_password),
                imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                password = newPassword,
                onPasswordChange = { newNewPassword -> onNewPasswordChange(newNewPassword) }
            )
            dev.jokku.ui.PasswordTextField(
                placeholder = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.repeat_new_password),
                imeAction = androidx.compose.ui.text.input.ImeAction.Done,
                password = repeatedPassword,
                onPasswordChange = { newRepeatedPassword ->
                    onRepeatedPasswordChange(
                        newRepeatedPassword
                    )
                },
                keyboardAction = { onButtonClick() }
            )
            dev.jokku.ui.BigActionButton(
                text = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.change_password),
                onClick = onButtonClick
            )
        }
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun ChangePasswordScreenPreview() {
    dev.jokku.designsystem.theme.AggregateTheme {
        ChangePasswordScreenContent(
            currentPassword = "",
            newPassword = "",
            repeatedPassword = "",
            onCurrentPasswordChange = {},
            onNewPasswordChange = {},
            onRepeatedPasswordChange = {},
            onBackButtonClick = {},
            onButtonClick = {}
        )
    }
}