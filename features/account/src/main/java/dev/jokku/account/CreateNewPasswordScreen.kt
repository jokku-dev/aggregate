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
            navController.popBackStack(route = dev.jokku.aggregate.presentation.navigation.Screen.SignIn.route, inclusive = false)
        },
        onBottomTextClick = {
            navController.popBackStack(route = dev.jokku.aggregate.presentation.navigation.Screen.SignIn.route, inclusive = false)
        }
    )
}

@androidx.compose.runtime.Composable
fun CreateNewPasswordScreenContent(
    password: String,
    passwordRepeat: String,
    onPasswordChange: (String) -> Unit,
    onPasswordRepeatChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    onBottomTextClick: () -> Unit
) {
val scrollState = rememberScrollState()

    dev.jokku.ui.CommonColumn(modifier = androidx.compose.ui.Modifier.verticalScroll(state = scrollState)) {
        dev.jokku.ui.HeadlineAndDescriptionText(
            headline = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.create_new_password),
            description = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.you_can_create_a_new_password)
        )
        dev.jokku.ui.PasswordTextField(
            modifier = androidx.compose.ui.Modifier.padding(top = 32.dp),
            placeholder = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.new_password),
            imeAction = androidx.compose.ui.text.input.ImeAction.Next,
            password = password,
            onPasswordChange = { newPassword -> onPasswordChange(newPassword) }
        )
        dev.jokku.ui.PasswordTextField(
            modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
            placeholder = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.repeat_new_password),
            imeAction = androidx.compose.ui.text.input.ImeAction.Done,
            keyboardAction = { onButtonClick() },
            password = passwordRepeat,
            onPasswordChange = { newPasswordRepeat -> onPasswordRepeatChange(newPasswordRepeat) }
        )
        dev.jokku.ui.BigActionButton(
            modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
            text = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.confirm),
            onClick = onButtonClick
        )
        Spacer(modifier = androidx.compose.ui.Modifier.weight(1f))
        dev.jokku.ui.HelpBottomText(
            questionText = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.remember_an_old_password),
            actionText = androidx.compose.ui.text.AnnotatedString(
                androidx.compose.ui.res.stringResource(
                    id = dev.jokku.aggregate.R.string.try_again
                )
            ),
            onClick = onBottomTextClick
        )
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun CreateNewPasswordScreenPreview() {
    dev.jokku.designsystem.theme.AggregateTheme {
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