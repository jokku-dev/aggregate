package dev.jokku.account

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
                navController.navigate(route = dev.jokku.aggregate.presentation.navigation.Screen.Verification.withArgs(email))
        },
        onBottomTextClick = {
            navController.popBackStack(route = dev.jokku.aggregate.presentation.navigation.Screen.SignIn.route, inclusive = false)
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
    dev.jokku.ui.CommonColumn {
        dev.jokku.ui.HeadlineAndDescriptionText(
            headline = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.forgot_password),
            description = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.we_need_your_email)
        )
        dev.jokku.ui.EmailTextField(
            modifier = androidx.compose.ui.Modifier.padding(top = 32.dp),
            imeAction = androidx.compose.ui.text.input.ImeAction.Done,
            email = email,
            onEmailChange = { newEmail -> onEmailChange(newEmail) },
            keyboardAction = { onButtonClick() }
        )
        dev.jokku.ui.BigActionButton(
            modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
            text = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.next),
            onClick = onButtonClick
        )
        Spacer(modifier = androidx.compose.ui.Modifier.weight(1f))
        dev.jokku.ui.HelpBottomText(
            questionText = androidx.compose.ui.res.stringResource(id = dev.jokku.aggregate.R.string.remember_the_password),
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
fun ForgotPasswordScreenPreview() {
    dev.jokku.designsystem.theme.AggregateTheme {
        ForgotPasswordScreenContent(
            email = "email@gmail.com",
            onEmailChange = {},
            onButtonClick = {},
            onBottomTextClick = {}
        )
    }
}