package dev.jokku.account

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.jokku.aggregate.presentation.navigation.Screen
import dev.jokku.designsystem.theme.AggregateTheme
import dev.jokku.ui.HelpBottomText

@androidx.compose.runtime.Composable
fun SignUpScreen(
    navController: NavHostController
) {
    var username by androidx.compose.runtime.saveable.rememberSaveable {
        androidx.compose.runtime.mutableStateOf(
            ""
        )
    }
    var email by androidx.compose.runtime.saveable.rememberSaveable {
        androidx.compose.runtime.mutableStateOf(
            ""
        )
    }
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

    SignUpScreenContent(
        username = username,
        email = email,
        password = password,
        passwordRepeat = passwordRepeat,
        onUsernameChange = { newUsername -> username = newUsername },
        onEmailChange = { newEmail -> email = newEmail },
        onPasswordChange = { newPassword -> password = newPassword },
        onPasswordRepeatChange = { newPasswordRepeat -> passwordRepeat = newPasswordRepeat },
        onButtonClick = {
            navController.popBackStack(route = dev.jokku.aggregate.presentation.navigation.Screen.SignIn.route, inclusive = false)
        },
        onBottomTextClick = {
            navController.popBackStack(route = dev.jokku.aggregate.presentation.navigation.Screen.SignIn.route, inclusive = false)
        }
    )
}

@androidx.compose.runtime.Composable
fun SignUpScreenContent(
    username: String,
    email: String,
    password: String,
    passwordRepeat: String,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordRepeatChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    onBottomTextClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    dev.jokku.ui.CommonColumn(modifier = androidx.compose.ui.Modifier.verticalScroll(state = scrollState)) {
        dev.jokku.ui.HeadlineAndDescriptionText(
            headline = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.welcome_to_aggregate),
            description = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.i_guess_you_are_new_around)
        )
        dev.jokku.ui.UsernameTextField(
            modifier = androidx.compose.ui.Modifier.padding(top = 32.dp),
            imeAction = androidx.compose.ui.text.input.ImeAction.Next,
            username = username,
            onUsernameChange = { newUsername -> onUsernameChange(newUsername) }
        )
        dev.jokku.ui.EmailTextField(
            modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
            imeAction = androidx.compose.ui.text.input.ImeAction.Next,
            email = email,
            onEmailChange = { newEmail -> onEmailChange(newEmail) }
        )
        dev.jokku.ui.PasswordTextField(
            modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
            placeholder = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.password_hint),
            imeAction = androidx.compose.ui.text.input.ImeAction.Next,
            password = password,
            onPasswordChange = { newPassword -> onPasswordChange(newPassword) }
        )
        dev.jokku.ui.PasswordTextField(
            modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
            placeholder = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.repeat_password),
            imeAction = androidx.compose.ui.text.input.ImeAction.Done,
            password = passwordRepeat,
            onPasswordChange = { newPasswordRepeat -> onPasswordRepeatChange(newPasswordRepeat) },
            keyboardAction = { onButtonClick() }
        )
        dev.jokku.ui.BigActionButton(
            modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
            text = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.sign_up),
            onClick = onButtonClick
        )
        Spacer(modifier = androidx.compose.ui.Modifier.weight(1f))
        dev.jokku.ui.HelpBottomText(
            questionText = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.already_have_an_account),
            actionText = androidx.compose.ui.text.AnnotatedString(
                text = androidx.compose.ui.res.stringResource(
                    id = dev.jokku.aggregate.R.string.sign_in
                )
            ),
            onClick = onBottomTextClick
        )
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun SignUpScreenPreview() {
    dev.jokku.designsystem.theme.AggregateTheme {
        SignUpScreenContent(
            username = "Username",
            email = "Email",
            password = "Password",
            passwordRepeat = "PasswordRepeat",
            onUsernameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onPasswordRepeatChange = {},
            onButtonClick = {},
            onBottomTextClick = {}
        )
    }
}