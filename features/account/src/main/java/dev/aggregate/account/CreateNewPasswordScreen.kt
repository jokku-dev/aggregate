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
import dev.aggregate.designsystem.component.HeadlineAndDescriptionText
import dev.aggregate.designsystem.component.HelpBottomText
import dev.aggregate.designsystem.component.PasswordTextField
import dev.aggregate.designsystem.theme.AggregateTheme

@Composable
fun CreateNewPasswordScreen(
    navController: NavHostController
) {
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var passwordRepeat by rememberSaveable {
        mutableStateOf("")
    }

    CreateNewPasswordScreenContent(
        password = password,
        passwordRepeat = passwordRepeat,
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
fun CreateNewPasswordScreenContent(
    password: String,
    passwordRepeat: String,
    onPasswordChange: (String) -> Unit,
    onPasswordRepeatChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    onBottomTextClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    CommonColumn(modifier = Modifier.verticalScroll(state = scrollState)) {
        HeadlineAndDescriptionText(
            headline = stringResource(id = R.string.create_new_password),
            description = stringResource(id = R.string.you_can_create_a_new_password)
        )
        PasswordTextField(
            modifier = Modifier.padding(top = 32.dp),
            placeholder = stringResource(id = R.string.new_password),
            imeAction = ImeAction.Next,
            password = password,
            onPasswordChange = { newPassword -> onPasswordChange(newPassword) }
        )
        PasswordTextField(
            modifier = Modifier.padding(top = 16.dp),
            placeholder = stringResource(id = R.string.repeat_new_password),
            imeAction = ImeAction.Done,
            keyboardAction = { onButtonClick() },
            password = passwordRepeat,
            onPasswordChange = { newPasswordRepeat -> onPasswordRepeatChange(newPasswordRepeat) }
        )
        BigActionButton(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(id = R.string.confirm),
            onClick = onButtonClick
        )
        Spacer(modifier = Modifier.weight(1f))
        HelpBottomText(
            questionText = stringResource(id = R.string.remember_an_old_password),
            actionText = AnnotatedString(
                stringResource(id = R.string.try_again)
            ),
            onClick = onBottomTextClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreateNewPasswordScreenPreview() {
    AggregateTheme {
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
