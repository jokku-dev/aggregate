package dev.aggregate.account

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
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.presentation.navigation.Screen
import dev.aggregate.ui.HelpBottomText
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch
import kotlin.compareTo

@OptIn(androidx.compose.ui.ExperimentalComposeUiApi::class)
@androidx.compose.runtime.Composable
fun VerificationCodeScreen(
    navController: NavHostController,
    email: String
) {
    var otpValue by androidx.compose.runtime.saveable.rememberSaveable {
        androidx.compose.runtime.mutableStateOf(
            ""
        )
    }
    val focusRequester =
        androidx.compose.runtime.remember { androidx.compose.ui.focus.FocusRequester() }
    val lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current
    val scope = androidx.compose.runtime.rememberCoroutineScope()
    val keyboardController = androidx.compose.ui.platform.LocalSoftwareKeyboardController.current

    VerificationCodeScreenContent(
        email = email,
        otpValue = otpValue,
        focusRequester = focusRequester,
        onOtpValueChange = { newOtpValue -> otpValue = newOtpValue },
        onButtonClick = {
            if (otpValue.length == 4) {
                navController.popBackStack(route = dev.aggregate.app.presentation.navigation.Screen.SignIn.route, inclusive = false)
                navController.navigate(route = dev.aggregate.app.presentation.navigation.Screen.CreateNewPassword.route)
            }
        },
        onBottomHelpTextClick = {
            navController.popBackStack(route = dev.aggregate.app.presentation.navigation.Screen.ForgotPassword.route, inclusive = false)
        }
    )
    // Request textField focus and show keyboard in case of onResume event
    androidx.compose.runtime.DisposableEffect(key1 = lifecycleOwner) {
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

@androidx.compose.runtime.Composable
fun VerificationCodeScreenContent(
    email: String,
    otpValue: String,
    focusRequester: androidx.compose.ui.focus.FocusRequester,
    onOtpValueChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    onBottomHelpTextClick: () -> Unit
) {
    dev.aggregate.app.ui.CommonColumn {
        Text(
            modifier = androidx.compose.ui.Modifier.align(androidx.compose.ui.Alignment.Start),
            text = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.verification_code),
            style = typography.headlineLarge,
            color = colorScheme.onSurfaceVariant
        )
        Text(
            modifier = androidx.compose.ui.Modifier
                .align(androidx.compose.ui.Alignment.Start)
                .padding(top = 8.dp),
            text = androidx.compose.ui.text.buildAnnotatedString {
                append(androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.you_need_to_enter_code))
                withStyle(
                    style = androidx.compose.ui.text.SpanStyle(
                        color = colorScheme.onBackground,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold
                    )
                ) { append(" $email ") }
                append(androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.email_address))
            },
            style = typography.bodyMedium,
            color = colorScheme.onSecondary
        )
        BasicTextField(
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .focusRequester(focusRequester),
            value = otpValue,
            onValueChange = { newValue -> if (newValue.length <= 4) onOtpValueChange(newValue) },
            keyboardOptions = KeyboardOptions(
                keyboardType = androidx.compose.ui.text.input.KeyboardType.NumberPassword, imeAction = androidx.compose.ui.text.input.ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { onButtonClick() })
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                repeat(4) { index ->
                    val char = when {
                        index >= otpValue.length -> "-"
                        else -> otpValue[index].toString()
                    }
                    val isFocused = otpValue.length == index
                    Box(
                        modifier = androidx.compose.ui.Modifier
                            .size(72.dp)
                            .clip(shapes.medium)
                            .border(
                                width = 1.dp,
                                shape = shapes.medium,
                                color = if (isFocused) colorScheme.primary else colorScheme.secondary
                            )
                            .background(
                                color = if (isFocused) colorScheme.surface else colorScheme.secondary
                            ),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Text(
                            text = char,
                            style = typography.titleLarge,
                            color = if (isFocused) colorScheme.primary else colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
        dev.aggregate.app.ui.BigActionButton(
            modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
            text = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.confirm),
            onClick = onButtonClick
        )
        Spacer(modifier = androidx.compose.ui.Modifier.weight(1f))
        dev.aggregate.app.ui.HelpBottomText(
            questionText = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.did_not_receive_an_email),
            actionText = androidx.compose.ui.text.AnnotatedString(
                androidx.compose.ui.res.stringResource(
                    id = dev.aggregate.app.R.string.send_again
                )
            ),
            onClick = onBottomHelpTextClick
        )
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun VerificationCodeScreenPreview() {
    dev.aggregate.app.designsystem.theme.AggregateTheme {
        VerificationCodeScreenContent(
            email = "email@gmail.com",
            otpValue = "12",
            focusRequester = androidx.compose.ui.focus.FocusRequester(),
            onOtpValueChange = {},
            onButtonClick = {},
            onBottomHelpTextClick = {}
        )
    }
}