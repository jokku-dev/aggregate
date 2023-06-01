package com.jokku.aggregate.presentation.screens.account

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
import com.jokku.aggregate.R
import com.jokku.aggregate.presentation.nav.Screen
import com.jokku.aggregate.presentation.theme.AggregateTheme
import com.jokku.aggregate.presentation.views.BigActionButton
import com.jokku.aggregate.presentation.views.CommonColumn
import com.jokku.aggregate.presentation.views.EmailTextField
import com.jokku.aggregate.presentation.views.HeadlineAndDescriptionText
import com.jokku.aggregate.presentation.views.HelpBottomText

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController
) {
    var email by rememberSaveable { mutableStateOf("") }

    ForgotPasswordScreenContent(
        email = email,
        onEmailChange = { newEmail -> email = newEmail },
        onButtonClick = {
            if (email.isNotEmpty())
                navController.navigate(route = Screen.Verification.withArgs(email))
        },
        onBottomTextClick = {
            navController.popBackStack(route = Screen.SignIn.route, inclusive = false)
        }
    )
}

@Composable
fun ForgotPasswordScreenContent(
    email: String,
    onEmailChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    onBottomTextClick: () -> Unit
) {
    CommonColumn {
        HeadlineAndDescriptionText(
            headline = stringResource(id = R.string.forgot_password),
            description = stringResource(id = R.string.we_need_your_email)
        )
        EmailTextField(
            modifier = Modifier.padding(top = 32.dp),
            imeAction = ImeAction.Done,
            email = email,
            onEmailChange = { newEmail -> onEmailChange(newEmail) },
            keyboardAction = { onButtonClick() }
        )
        BigActionButton(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(id = R.string.next),
            onClick = onButtonClick
        )
        Spacer(modifier = Modifier.weight(1f))
        HelpBottomText(
            questionText = stringResource(id = R.string.remember_the_password),
            actionText = AnnotatedString(stringResource(id = R.string.try_again)),
            onClick = onBottomTextClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    AggregateTheme {
        ForgotPasswordScreenContent(
            email = "email@gmail.com",
            onEmailChange = {},
            onButtonClick = {},
            onBottomTextClick = {}
        )
    }
}