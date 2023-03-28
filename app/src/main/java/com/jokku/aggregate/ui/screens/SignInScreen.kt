package com.jokku.aggregate.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
import com.jokku.aggregate.ui.views.EmailTextField
import com.jokku.aggregate.ui.views.HelpBottomText
import com.jokku.aggregate.ui.views.PasswordTextField
import com.jokku.aggregate.ui.views.SignInWithButton

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = stringResource(id = R.string.welcome_back),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 32.dp)
            )
            Text(
                text = stringResource(id = R.string.i_am_happy_to_see_you_again),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 8.dp)
            )
            EmailTextField(
                modifier = Modifier.padding(top = 32.dp),
                imeAction = ImeAction.Next,
                email = email,
                onEmailChange = { newEmail -> email = newEmail }
            )
            PasswordTextField(
                modifier = Modifier.padding(top = 16.dp),
                placeholder = stringResource(id = R.string.password_hint),
                imeAction = ImeAction.Done,
                keyboardAction = {
                    navController.popBackStack()
                    navController.navigate(route = Screen.Verification.route)
                },
                password = password,
                onPasswordChange = { newPassword -> password = newPassword }
            )
            ClickableText(
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .padding(top = 16.dp),
                text = AnnotatedString(text = stringResource(id = R.string.forgot_password_question)),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                navController.navigate(route = Screen.ForgotPassword.route)
            }
            BigActionButton(
                modifier = Modifier.padding(top = 24.dp),
                text = stringResource(id = R.string.sign_in)
            ) {
                navController.popBackStack()
                navController.navigate(route = Screen.Home.route)
            }
            Text(
                modifier = Modifier.padding(top = 48.dp),
                text = stringResource(id = R.string.or),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondary
            )
            SignInWithButton(
                modifier = Modifier.padding(top = 48.dp),
                painter = painterResource(id = R.drawable.ic_logo_google),
                text = stringResource(id = R.string.sign_in_with_google)
            ) {
                TODO()
            }
            SignInWithButton(
                modifier = Modifier.padding(top = 16.dp),
                painter = painterResource(id = R.drawable.ic_logo_facebook),
                text = stringResource(id = R.string.sign_in_with_facebook)
            ) {
                TODO()
            }
            Spacer(modifier = Modifier.weight(1f))
            HelpBottomText(
                questionText = stringResource(id = R.string.do_not_have_an_account),
                actionText = AnnotatedString(text = stringResource(id = R.string.sign_up))
            ) {
                TODO()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(navController = rememberNavController())
}