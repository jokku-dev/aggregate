package com.jokku.aggregate.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.jokku.aggregate.ui.views.HelpBottomText
import com.jokku.aggregate.ui.views.PasswordTextField

@Composable
fun CreateNewPasswordScreen(
    navController: NavHostController
) {
    var password by rememberSaveable { mutableStateOf("") }
    var passwordRepeat by rememberSaveable { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .align(Alignment.Start),
                text = stringResource(id = R.string.create_new_password),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.Start),
                text = stringResource(id = R.string.you_can_create_a_new_password),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
            PasswordTextField(
                modifier = Modifier.padding(top = 32.dp),
                placeholder = stringResource(id = R.string.new_password),
                imeAction = ImeAction.Next,
                password = password,
                onPasswordChange = { newPassword -> password = newPassword }
            )
            PasswordTextField(
                modifier = Modifier.padding(top = 16.dp),
                placeholder = stringResource(id = R.string.repeat_new_password),
                imeAction = ImeAction.Done,
                keyboardAction = { navController.navigate(route = Screen.SignIn.route) },
                password = passwordRepeat,
                onPasswordChange = { newPassword -> passwordRepeat = newPassword }
            )
            BigActionButton(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.confirm)
            ) {
                navController.popBackStack()
                navController.navigate(route = Screen.SignIn.route)
            }
            Spacer(modifier = Modifier.weight(1f))
            HelpBottomText(
                questionText = stringResource(id = R.string.remember_an_old_password),
                actionText = AnnotatedString(stringResource(id = R.string.try_again))
            ) {
                navController.popBackStack()
                navController.navigate(route = Screen.SignIn.route)
            }
        }
    }
}

@Preview
@Composable
fun CreateNewPasswordScreenPreview() {
    CreateNewPasswordScreen(navController = rememberNavController())
}