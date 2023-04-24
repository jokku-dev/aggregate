package com.jokku.aggregate.ui.screens.account

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.nav.Screen
import com.jokku.aggregate.ui.views.BigActionButton
import com.jokku.aggregate.ui.views.CommonColumn
import com.jokku.aggregate.ui.views.HelpBottomText
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VerificationCodeScreen(
    navController: NavHostController,
    email: String
) {
    var otpValue by rememberSaveable { mutableStateOf("") }
    val lifecycleOwner = LocalLifecycleOwner.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val scope = rememberCoroutineScope()

    CommonColumn {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = stringResource(id = R.string.verification_code),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 8.dp),
            text = buildAnnotatedString {
                append(stringResource(id = R.string.you_need_to_enter_code))
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold
                    )
                ) { append(" $email ") }
                append(stringResource(id = R.string.email_address))
            },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .focusRequester(focusRequester),
            value = otpValue,
            onValueChange = { value -> if (value.length <= 4) otpValue = value },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                if (otpValue.length < 4) TODO()
            })
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                repeat(4) { index ->
                    val char = when {
                        index >= otpValue.length -> "-"
                        else -> otpValue[index].toString()
                    }
                    val isFocused = otpValue.length == index
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .border(
                                width = 1.dp,
                                shape = MaterialTheme.shapes.medium,
                                color = if (isFocused) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.secondary
                            )
                            .background(
                                color = if (isFocused) MaterialTheme.colorScheme.surface
                                else MaterialTheme.colorScheme.secondary
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char,
                            style = MaterialTheme.typography.titleLarge,
                            color = if (isFocused) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
        BigActionButton(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(id = R.string.confirm)
        ) {
            navController.popBackStack(route = Screen.ForgotPassword.route, inclusive = true)
            navController.navigate(route = Screen.CreateNewPassword.route)
        }
        Spacer(modifier = Modifier.weight(1f))
        HelpBottomText(
            questionText = stringResource(id = R.string.did_not_receive_an_email),
            actionText = AnnotatedString(stringResource(id = R.string.send_again))
        ) {
            TODO()
        }
        //request textField focus and show keyboard in case of onResume event
        DisposableEffect(key1 = lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                scope.launch {
                    if (event == Lifecycle.Event.ON_RESUME) {
                        focusRequester.requestFocus()
                        awaitFrame()
                        keyboardController?.show()
                    }
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    }
}

@Preview
@Composable
fun VerificationCodeScreenPreview() {
    VerificationCodeScreen(
        navController = rememberNavController(),
        email = "example@example.com"
    )
}