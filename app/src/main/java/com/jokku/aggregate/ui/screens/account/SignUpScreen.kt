package com.jokku.aggregate.ui.screens.account

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.compose.rememberNavController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.nav.Screen
import com.jokku.aggregate.ui.views.BigActionButton
import com.jokku.aggregate.ui.views.CommonColumn
import com.jokku.aggregate.ui.views.EmailTextField
import com.jokku.aggregate.ui.views.HeadlineAndDescriptionText
import com.jokku.aggregate.ui.views.HelpBottomText
import com.jokku.aggregate.ui.views.PasswordTextField
import com.jokku.aggregate.ui.views.UsernameTextField

@Composable
fun SignUpScreen(
    navController: NavHostController
) {
    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordRepeat by rememberSaveable { mutableStateOf("") }

    CommonColumn {
        HeadlineAndDescriptionText(
            headline = stringResource(id = R.string.welcome_to_aggregate),
            description = stringResource(id = R.string.i_guess_you_are_new_around)
        )
        UsernameTextField(
            modifier = Modifier.padding(top = 32.dp),
            imeAction = ImeAction.Next,
            username = username,
            onUsernameChange = { newUsername -> username = newUsername }
        )
        EmailTextField(
            modifier = Modifier.padding(top = 16.dp),
            imeAction = ImeAction.Next,
            email = email,
            onEmailChange = { newEmail -> email = newEmail }
        )
        PasswordTextField(
            modifier = Modifier.padding(top = 16.dp),
            placeholder = stringResource(id = R.string.password_hint),
            imeAction = ImeAction.Next,
            password = password,
            onPasswordChange = { newPassword -> password = newPassword }
        )
        PasswordTextField(
            modifier = Modifier.padding(top = 16.dp),
            placeholder = stringResource(id = R.string.repeat_password),
            imeAction = ImeAction.Done,
            password = passwordRepeat,
            onPasswordChange = { newPasswordRepeat -> passwordRepeat = newPasswordRepeat },
            keyboardAction = {
                navController.popBackStack(route = Screen.SignIn.route, inclusive = true)
                navController.navigate(route = Screen.SignIn.route)
            }
        )
        BigActionButton(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(id = R.string.sign_up),
            onClick = {
                navController.popBackStack(route = Screen.SignIn.route, inclusive = true)
                navController.navigate(route = Screen.SignIn.route)
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        HelpBottomText(
            questionText = stringResource(id = R.string.already_have_an_account),
            actionText = AnnotatedString(text = stringResource(id = R.string.sign_in)),
            onClick = {
                navController.popBackStack(route = Screen.SignIn.route, inclusive = true)
                navController.navigate(route = Screen.SignIn.route)
            }
        )
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navController = rememberNavController())
}