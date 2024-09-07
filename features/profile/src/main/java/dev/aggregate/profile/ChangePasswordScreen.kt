package dev.aggregate.profile

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
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.presentation.navigation.Screen
import dev.aggregate.ui.BigActionButton

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
        onRepeatedPasswordChange = { newRepeatedPassword ->
            repeatedPassword = newRepeatedPassword
        },
        onBackButtonClick = {
            navController.popBackStack(
                route = dev.aggregate.app.presentation.navigation.Screen.Profile.route,
                inclusive = false
            )
        },
        onButtonClick = {
            navController.popBackStack(
                route = dev.aggregate.app.presentation.navigation.Screen.Profile.route,
                inclusive = false
            )
            navController.navigate(route = dev.aggregate.app.presentation.navigation.Screen.SignIn.route)
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
    dev.aggregate.app.ui.CommonColumn {
        dev.aggregate.app.ui.BackButtonAndHeadlineText(
            headline = androidx.compose.ui.res.stringResource(dev.aggregate.app.R.string.change_password),
            onClick = onBackButtonClick
        )
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            dev.aggregate.app.ui.PasswordTextField(
                placeholder = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.current_password),
                imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                password = currentPassword,
                onPasswordChange = { newCurrentPassword ->
                    onCurrentPasswordChange(
                        newCurrentPassword
                    )
                }
            )
            dev.aggregate.app.ui.PasswordTextField(
                placeholder = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.new_password),
                imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                password = newPassword,
                onPasswordChange = { newNewPassword -> onNewPasswordChange(newNewPassword) }
            )
            dev.aggregate.app.ui.PasswordTextField(
                placeholder = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.repeat_new_password),
                imeAction = androidx.compose.ui.text.input.ImeAction.Done,
                password = repeatedPassword,
                onPasswordChange = { newRepeatedPassword ->
                    onRepeatedPasswordChange(
                        newRepeatedPassword
                    )
                },
                keyboardAction = { onButtonClick() }
            )
            dev.aggregate.app.ui.BigActionButton(
                text = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.change_password),
                onClick = onButtonClick
            )
        }
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun ChangePasswordScreenPreview() {
    dev.aggregate.app.designsystem.theme.AggregateTheme {
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