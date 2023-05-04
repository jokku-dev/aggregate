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

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController
) {
    var email by rememberSaveable { mutableStateOf("") }

    CommonColumn {
        HeadlineAndDescriptionText(
            headline = R.string.forgot_password,
            description = R.string.we_need_your_email
        )
        EmailTextField(
            modifier = Modifier.padding(top = 32.dp),
            imeAction = ImeAction.Done,
            email = email,
            onEmailChange = { newEmail -> email = newEmail }
        )
        BigActionButton(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(id = R.string.next),
            onClick = {
                navController.popBackStack()
                navController.navigate(route = Screen.Verification.withArgs(email))
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        HelpBottomText(
            questionText = stringResource(id = R.string.remember_the_password),
            actionText = AnnotatedString(stringResource(id = R.string.try_again))
        ) {
            navController.popBackStack(route = Screen.SignIn.route, inclusive = true)
            navController.navigate(route = Screen.SignIn.route)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(navController = rememberNavController())
}