package dev.aggregate.account

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.aggregate.designsystem.Screen
import dev.aggregate.designsystem.component.BigActionButton
import dev.aggregate.designsystem.component.CommonColumn
import dev.aggregate.designsystem.component.EmailTextField
import dev.aggregate.designsystem.component.HeadlineAndDescriptionText
import dev.aggregate.designsystem.component.HelpBottomText
import dev.aggregate.designsystem.component.PasswordTextField
import dev.aggregate.designsystem.component.UsernameTextField
import dev.aggregate.designsystem.theme.AggregateTheme

@Composable
fun SignUpScreen(
    navController: NavHostController
) {
    var username by rememberSaveable {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var passwordRepeat by rememberSaveable {
        mutableStateOf("")
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
            navController.popBackStack(
                route = Screen.SignIn.route,
                inclusive = false
            )
        },
        onBottomTextClick = {
            navController.popBackStack(
                route = Screen.SignIn.route,
                inclusive = false
            )
        }
    )
}

@Composable
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

    CommonColumn(modifier = Modifier.verticalScroll(state = scrollState)) {
        HeadlineAndDescriptionText(
            headline = stringResource(id = R.string.welcome_to_aggregate),
            description = stringResource(id = R.string.i_guess_you_are_new_around)
        )
        UsernameTextField(
            modifier = Modifier.padding(top = 32.dp),
            imeAction = ImeAction.Next,
            username = username,
            onUsernameChange = { newUsername -> onUsernameChange(newUsername) }
        )
        EmailTextField(
            modifier = Modifier.padding(top = 16.dp),
            imeAction = ImeAction.Next,
            email = email,
            onEmailChange = { newEmail -> onEmailChange(newEmail) }
        )
        PasswordTextField(
            modifier = Modifier.padding(top = 16.dp),
            placeholder = stringResource(id = R.string.password_hint),
            imeAction = ImeAction.Next,
            password = password,
            onPasswordChange = { newPassword -> onPasswordChange(newPassword) }
        )
        PasswordTextField(
            modifier = Modifier.padding(top = 16.dp),
            placeholder = stringResource(id = R.string.repeat_password),
            imeAction = ImeAction.Done,
            password = passwordRepeat,
            onPasswordChange = { newPasswordRepeat -> onPasswordRepeatChange(newPasswordRepeat) },
            keyboardAction = { onButtonClick() }
        )
        BigActionButton(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(id = R.string.sign_up),
            onClick = onButtonClick
        )
        Spacer(modifier = Modifier.weight(1f))
        HelpBottomText(
            questionText = stringResource(id = R.string.already_have_an_account),
            actionText = AnnotatedString(
                text = stringResource(id = R.string.sign_in)
            ),
            onClick = onBottomTextClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    AggregateTheme {
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
