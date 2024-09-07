package dev.aggregate.account

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.aggregate.app.account.R
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.presentation.navigation.Screen
import dev.aggregate.ui.HelpBottomText

@Composable
fun SignInScreen(
    navController: NavHostController
) {
    var email by androidx.compose.runtime.saveable.rememberSaveable {
        mutableStateOf(
            ""
        )
    }
    var password by androidx.compose.runtime.saveable.rememberSaveable {
        mutableStateOf(
            ""
        )
    }

    SignInScreenContent(
        email = email,
        password = password,
        onEmailChange = { newEmail -> email = newEmail },
        onPasswordChange = { newPassword -> password = newPassword },
        onButtonClick = {
            navController.popBackStack(
                route = dev.aggregate.app.presentation.navigation.Screen.SignIn.route,
                inclusive = true
            )
            navController.navigate(route = dev.aggregate.app.presentation.navigation.Screen.TopHeadlines.route)
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

    dev.aggregate.app.ui.CommonColumn(modifier = androidx.compose.ui.Modifier.verticalScroll(state = scrollState)) {
        dev.aggregate.app.ui.HeadlineAndDescriptionText(
            headline = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.welcome_back),
            description = androidx.compose.ui.res.stringResource(id = R.string.i_am_happy_to_see_you_again)
        )
        dev.aggregate.app.ui.EmailTextField(
            modifier = androidx.compose.ui.Modifier.padding(top = 32.dp),
            imeAction = androidx.compose.ui.text.input.ImeAction.Next,
            email = email,
            onEmailChange = { newEmail -> onEmailChange(newEmail) }
        )
        dev.aggregate.app.ui.PasswordTextField(
            modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
            placeholder = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.password_hint),
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
                    id = dev.aggregate.app.R.string.forgot_password_question
                )
            ),
            style = typography.bodyLarge.copy(color = colorScheme.onSurfaceVariant),
            onClick = { onForgotPasswordClick() }
        )
        dev.aggregate.app.ui.BigActionButton(
            modifier = androidx.compose.ui.Modifier.padding(top = 24.dp),
            text = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.sign_in),
            onClick = onButtonClick
        )
        Text(
            modifier = androidx.compose.ui.Modifier.padding(top = 48.dp),
            text = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.or),
            style = typography.titleLarge,
            color = colorScheme.onSecondary
        )
        dev.aggregate.app.ui.SignInWithButton(
            modifier = androidx.compose.ui.Modifier.padding(top = 48.dp),
            painter = androidx.compose.ui.res.painterResource(id = dev.aggregate.app.R.drawable.ic_logo_google),
            text = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.sign_in_with_google),
            onClick = onSignInWithGoogleClick
        )
        dev.aggregate.app.ui.SignInWithButton(
            modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
            painter = androidx.compose.ui.res.painterResource(id = dev.aggregate.app.R.drawable.ic_logo_facebook),
            text = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.sign_in_with_facebook),
            onClick = onSignInWithFacebookClick
        )
        Spacer(modifier = androidx.compose.ui.Modifier.weight(1f))
        dev.aggregate.app.ui.HelpBottomText(
            questionText = androidx.compose.ui.res.stringResource(id = R.string.do_not_have_an_account),
            actionText = androidx.compose.ui.text.AnnotatedString(
                text = androidx.compose.ui.res.stringResource(
                    id = dev.aggregate.app.R.string.sign_up
                )
            ),
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
