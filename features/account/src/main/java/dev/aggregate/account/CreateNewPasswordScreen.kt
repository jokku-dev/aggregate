package dev.aggregate.account

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.aggregate.app.R
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.presentation.navigation.Screen
import dev.aggregate.ui.HelpBottomText

@Composable
fun CreateNewPasswordScreen(
    navController: NavHostController
) {
    var password by androidx.compose.runtime.saveable.rememberSaveable {
        androidx.compose.runtime.mutableStateOf(
            ""
        )
    }
    var passwordRepeat by androidx.compose.runtime.saveable.rememberSaveable {
        androidx.compose.runtime.mutableStateOf(
            ""
        )
    }

    CreateNewPasswordScreenContent(
        password = password,
        passwordRepeat = passwordRepeat,
        onPasswordChange = { newPassword -> password = newPassword },
        onPasswordRepeatChange = { newPasswordRepeat -> passwordRepeat = newPasswordRepeat },
        onButtonClick = {
            navController.popBackStack(
                route = dev.aggregate.app.presentation.navigation.Screen.SignIn.route,
                inclusive = false
            )
        },
        onBottomTextClick = {
            navController.popBackStack(
                route = dev.aggregate.app.presentation.navigation.Screen.SignIn.route,
                inclusive = false
            )
        }
    )
}

@Composable
fun CreateNewPasswordScreenContent(
    password: String,
    passwordRepeat: String,
    onPasswordChange: (String) -> Unit,
    onPasswordRepeatChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    onBottomTextClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    dev.aggregate.app.ui.CommonColumn(modifier = androidx.compose.ui.Modifier.verticalScroll(state = scrollState)) {
        dev.aggregate.app.ui.HeadlineAndDescriptionText(
            headline = stringResource(id = R.string.create_new_password),
            description = stringResource(id = R.string.you_can_create_a_new_password)
        )
        dev.aggregate.app.ui.PasswordTextField(
            modifier = androidx.compose.ui.Modifier.padding(top = 32.dp),
            placeholder = androidx.compose.ui.res.stringResource(id = R.string.new_password),
            imeAction = androidx.compose.ui.text.input.ImeAction.Next,
            password = password,
            onPasswordChange = { newPassword -> onPasswordChange(newPassword) }
        )
        dev.aggregate.app.ui.PasswordTextField(
            modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
            placeholder = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.repeat_new_password),
            imeAction = androidx.compose.ui.text.input.ImeAction.Done,
            keyboardAction = { onButtonClick() },
            password = passwordRepeat,
            onPasswordChange = { newPasswordRepeat -> onPasswordRepeatChange(newPasswordRepeat) }
        )
        dev.aggregate.app.ui.BigActionButton(
            modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
            text = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.confirm),
            onClick = onButtonClick
        )
        Spacer(modifier = androidx.compose.ui.Modifier.weight(1f))
        dev.aggregate.app.ui.HelpBottomText(
            questionText = androidx.compose.ui.res.stringResource(id = R.string.remember_an_old_password),
            actionText = androidx.compose.ui.text.AnnotatedString(
                androidx.compose.ui.res.stringResource(
                    id = dev.aggregate.app.R.string.try_again
                )
            ),
            onClick = onBottomTextClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreateNewPasswordScreenPreview() {
    dev.aggregate.app.designsystem.theme.AggregateTheme {
        CreateNewPasswordScreenContent(
            password = "password",
            passwordRepeat = "",
            onPasswordChange = {},
            onPasswordRepeatChange = {},
            onButtonClick = {},
            onBottomTextClick = {}
        )
    }
}
