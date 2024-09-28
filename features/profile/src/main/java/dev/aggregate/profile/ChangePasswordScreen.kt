package dev.aggregate.profile

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
import dev.aggregate.designsystem.Screen
import dev.aggregate.designsystem.component.BackButtonAndHeadlineText
import dev.aggregate.designsystem.component.BigActionButton
import dev.aggregate.designsystem.component.CommonColumn
import dev.aggregate.designsystem.component.PasswordTextField
import dev.aggregate.designsystem.theme.AggregateTheme

@Composable
fun ChangePasswordScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel<MainProfileViewModel>()
) {
    var currentPassword by rememberSaveable {
        mutableStateOf("")
    }
    var newPassword by rememberSaveable {
        mutableStateOf("")
    }
    var repeatedPassword by rememberSaveable {
        mutableStateOf("")
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
                route = Screen.Profile.route,
                inclusive = false
            )
        },
        onButtonClick = {
            navController.popBackStack(
                route = Screen.Profile.route,
                inclusive = false
            )
            navController.navigate(route = Screen.SignIn.route)
        }
    )
}

@Composable
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
    CommonColumn {
        BackButtonAndHeadlineText(
            headline = stringResource(R.string.change_password),
            onClick = onBackButtonClick
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
                onPasswordChange = { newCurrentPassword ->
                    onCurrentPasswordChange(
                        newCurrentPassword
                    )
                }
            )
            PasswordTextField(
                placeholder = stringResource(id = R.string.new_password),
                imeAction = ImeAction.Next,
                password = newPassword,
                onPasswordChange = { newNewPassword -> onNewPasswordChange(newNewPassword) }
            )
            PasswordTextField(
                placeholder = stringResource(id = R.string.repeat_new_password),
                imeAction = ImeAction.Done,
                password = repeatedPassword,
                onPasswordChange = { newRepeatedPassword ->
                    onRepeatedPasswordChange(
                        newRepeatedPassword
                    )
                },
                keyboardAction = { onButtonClick() }
            )
            BigActionButton(
                text = stringResource(id = R.string.change_password),
                onClick = onButtonClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordScreenPreview() {
    AggregateTheme {
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
