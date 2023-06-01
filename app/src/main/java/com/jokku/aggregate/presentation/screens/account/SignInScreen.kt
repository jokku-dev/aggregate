package com.jokku.aggregate.presentation.screens.account

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
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
import com.jokku.aggregate.R
import com.jokku.aggregate.presentation.nav.Screen
import com.jokku.aggregate.presentation.theme.AggregateTheme
import com.jokku.aggregate.presentation.views.BigActionButton
import com.jokku.aggregate.presentation.views.CommonColumn
import com.jokku.aggregate.presentation.views.EmailTextField
import com.jokku.aggregate.presentation.views.HeadlineAndDescriptionText
import com.jokku.aggregate.presentation.views.HelpBottomText
import com.jokku.aggregate.presentation.views.PasswordTextField
import com.jokku.aggregate.presentation.views.SignInWithButton

@Composable
fun SignInScreen(
    navController: NavHostController
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    SignInScreenContent(
        email = email,
        password = password,
        onEmailChange = { newEmail -> email = newEmail },
        onPasswordChange = { newPassword -> password = newPassword },
        onButtonClick = {
            navController.popBackStack(route = Screen.SignIn.route, inclusive = true)
            navController.navigate(route = Screen.TopHeadlines.route)
        },
        onForgotPasswordClick = { navController.navigate(route = Screen.ForgotPassword.route) },
        onSignInWithGoogleClick = {},
        onSignInWithFacebookClick = {},
        onBottomTextClick = { navController.navigate(route = Screen.SignUp.route) }
    )
}

@Composable
fun SignInScreenContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignInWithGoogleClick: () -> Unit,
    onSignInWithFacebookClick: () -> Unit,
    onBottomTextClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    CommonColumn(modifier = Modifier.verticalScroll(state = scrollState)) {
        HeadlineAndDescriptionText(
            headline = stringResource(id = R.string.welcome_back),
            description = stringResource(id = R.string.i_am_happy_to_see_you_again)
        )
        EmailTextField(
            modifier = Modifier.padding(top = 32.dp),
            imeAction = ImeAction.Next,
            email = email,
            onEmailChange = { newEmail -> onEmailChange(newEmail) }
        )
        PasswordTextField(
            modifier = Modifier.padding(top = 16.dp),
            placeholder = stringResource(id = R.string.password_hint),
            imeAction = ImeAction.Done,
            keyboardAction = { onButtonClick() },
            password = password,
            onPasswordChange = { newPassword -> onPasswordChange(newPassword) }
        )
        ClickableText(
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(top = 16.dp),
            text = AnnotatedString(text = stringResource(id = R.string.forgot_password_question)),
            style = typography.bodyLarge.copy(color = colorScheme.onSurfaceVariant),
            onClick = { onForgotPasswordClick() }
        )
        BigActionButton(
            modifier = Modifier.padding(top = 24.dp),
            text = stringResource(id = R.string.sign_in),
            onClick = onButtonClick
        )
        Text(
            modifier = Modifier.padding(top = 48.dp),
            text = stringResource(id = R.string.or),
            style = typography.titleLarge,
            color = colorScheme.onSecondary
        )
        SignInWithButton(
            modifier = Modifier.padding(top = 48.dp),
            painter = painterResource(id = R.drawable.ic_logo_google),
            text = stringResource(id = R.string.sign_in_with_google),
            onClick = onSignInWithGoogleClick
        )
        SignInWithButton(
            modifier = Modifier.padding(top = 16.dp),
            painter = painterResource(id = R.drawable.ic_logo_facebook),
            text = stringResource(id = R.string.sign_in_with_facebook),
            onClick = onSignInWithFacebookClick
        )
        Spacer(modifier = Modifier.weight(1f))
        HelpBottomText(
            questionText = stringResource(id = R.string.do_not_have_an_account),
            actionText = AnnotatedString(text = stringResource(id = R.string.sign_up)),
            onClick = onBottomTextClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    AggregateTheme {
        SignInScreenContent(
            email = "Email",
            password = "Password",
            onEmailChange = {},
            onPasswordChange = {},
            onButtonClick = {},
            onForgotPasswordClick = {},
            onSignInWithGoogleClick = {},
            onSignInWithFacebookClick = {},
            onBottomTextClick = {}
        )
    }
}