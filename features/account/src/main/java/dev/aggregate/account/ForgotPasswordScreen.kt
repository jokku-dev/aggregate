package dev.aggregate.account

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.aggregate.app.account.R
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.presentation.navigation.Screen
import dev.aggregate.ui.HelpBottomText
import kotlin.text.isNotEmpty

@androidx.compose.runtime.Composable
fun ForgotPasswordScreen(
    navController: NavHostController
) {
    var email by androidx.compose.runtime.saveable.rememberSaveable {
        androidx.compose.runtime.mutableStateOf(
            ""
        )
    }

    ForgotPasswordScreenContent(
        email = email,
        onEmailChange = { newEmail -> email = newEmail },
        onButtonClick = {
            if (email.isNotEmpty())
                navController.navigate(
                    route = dev.aggregate.app.presentation.navigation.Screen.Verification.withArgs(
                        email
                    )
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

@androidx.compose.runtime.Composable
fun ForgotPasswordScreenContent(
    email: String,
    onEmailChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    onBottomTextClick: () -> Unit
) {
    dev.aggregate.app.ui.CommonColumn {
        dev.aggregate.app.ui.HeadlineAndDescriptionText(
            headline = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.forgot_password),
            description = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.we_need_your_email)
        )
        dev.aggregate.app.ui.EmailTextField(
            modifier = androidx.compose.ui.Modifier.padding(top = 32.dp),
            imeAction = androidx.compose.ui.text.input.ImeAction.Done,
            email = email,
            onEmailChange = { newEmail -> onEmailChange(newEmail) },
            keyboardAction = { onButtonClick() }
        )
        dev.aggregate.app.ui.BigActionButton(
            modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
            text = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.next),
            onClick = onButtonClick
        )
        Spacer(modifier = androidx.compose.ui.Modifier.weight(1f))
        dev.aggregate.app.ui.HelpBottomText(
            questionText = stringResource(id = R.string.remember_the_password),
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
@androidx.compose.runtime.Composable
fun ForgotPasswordScreenPreview() {
    dev.aggregate.app.designsystem.theme.AggregateTheme {
        ForgotPasswordScreenContent(
            email = "email@gmail.com",
            onEmailChange = {},
            onButtonClick = {},
            onBottomTextClick = {}
        )
    }
}
