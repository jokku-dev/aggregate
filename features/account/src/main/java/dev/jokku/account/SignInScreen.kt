package dev.jokku.account

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.jokku.aggregate.presentation.navigation.Screen
import dev.jokku.designsystem.theme.AggregateTheme
import dev.jokku.ui.HelpBottomText

@androidx.compose.runtime.Composable
fun SignInScreen(
    navController: NavHostController
) {
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

    SignInScreenContent(
        email = email,
        password = password,
        onEmailChange = { newEmail -> email = newEmail },
        onPasswordChange = { newPassword -> password = newPassword },
        onButtonClick = {
            navController.popBackStack(route = dev.jokku.aggregate.presentation.navigation.Screen.SignIn.route, inclusive = true)
            navController.navigate(route = dev.jokku.aggregate.presentation.navigation.Screen.TopHeadlines.route)
        },
        onForgotPasswordClick = { navController.navigate(route = dev.jokku.aggregate.presentation.navigation.Screen.ForgotPassword.route) },
        onSignInWithGoogleClick = {},
        onSignInWithFacebookClick = {},
        onBottomTextClick = { navController.navigate(route = dev.jokku.aggregate.presentation.navigation.Screen.SignUp.route) }
    )
}

@androidx.compose.runtime.Composable
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

    dev.jokku.ui.CommonColumn(modifier = androidx.compose.ui.Modifier.verticalScroll(state = scrollState)) {
        dev.jokku.ui.HeadlineAndDescriptionText(
            headline = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.welcome_back),
            description = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.i_am_happy_to_see_you_again)
        )
        dev.jokku.ui.EmailTextField(
            modifier = androidx.compose.ui.Modifier.padding(top = 32.dp),
            imeAction = androidx.compose.ui.text.input.ImeAction.Next,
            email = email,
            onEmailChange = { newEmail -> onEmailChange(newEmail) }
        )
        dev.jokku.ui.PasswordTextField(
            modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
            placeholder = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.password_hint),
            imeAction = androidx.compose.ui.text.input.ImeAction.Done,
            keyboardAction = { onButtonClick() },
            password = password,
            onPasswordChange = { newPassword -> onPasswordChange(newPassword) }
        )
        ClickableText(
            modifier = androidx.compose.ui.Modifier
                .align(alignment = androidx.compose.ui.Alignment.End)
                .padding(top = 16.dp),
            text = androidx.compose.ui.text.AnnotatedString(
                text = androidx.compose.ui.res.stringResource(
                    id = dev.jokku.aggregate.R.string.forgot_password_question
                )
            ),
            style = typography.bodyLarge.copy(color = colorScheme.onSurfaceVariant),
            onClick = { onForgotPasswordClick() }
        )
        dev.jokku.ui.BigActionButton(
            modifier = androidx.compose.ui.Modifier.padding(top = 24.dp),
            text = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.sign_in),
            onClick = onButtonClick
        )
        Text(
            modifier = androidx.compose.ui.Modifier.padding(top = 48.dp),
            text = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.or),
            style = typography.titleLarge,
            color = colorScheme.onSecondary
        )
        dev.jokku.ui.SignInWithButton(
            modifier = androidx.compose.ui.Modifier.padding(top = 48.dp),
            painter = androidx.compose.ui.res.painterResource(id = dev.jokku.aggregate.R.drawable.ic_logo_google),
            text = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.sign_in_with_google),
            onClick = onSignInWithGoogleClick
        )
        dev.jokku.ui.SignInWithButton(
            modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
            painter = androidx.compose.ui.res.painterResource(id = dev.jokku.aggregate.R.drawable.ic_logo_facebook),
            text = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.sign_in_with_facebook),
            onClick = onSignInWithFacebookClick
        )
        Spacer(modifier = androidx.compose.ui.Modifier.weight(1f))
        dev.jokku.ui.HelpBottomText(
            questionText = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.do_not_have_an_account),
            actionText = androidx.compose.ui.text.AnnotatedString(
                text = androidx.compose.ui.res.stringResource(
                    id = dev.jokku.aggregate.R.string.sign_up
                )
            ),
            onClick = onBottomTextClick
        )
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun SignInScreenPreview() {
    dev.jokku.designsystem.theme.AggregateTheme {
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