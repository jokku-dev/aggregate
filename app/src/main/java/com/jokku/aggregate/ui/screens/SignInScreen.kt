package com.jokku.aggregate.ui.screens

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.theme.BlackPrimary
import com.jokku.aggregate.ui.theme.GreyLighter
import com.jokku.aggregate.ui.theme.GreyPrimary
import com.jokku.aggregate.ui.theme.PurplePrimary
import com.jokku.aggregate.ui.theme.Shapes
import com.jokku.aggregate.ui.theme.Typography

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    navController: NavHostController
    ) {
    Surface(modifier = modifier) {
        Column(
            modifier = modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.welcome_back),
                style = Typography.headlineLarge,
                color = BlackPrimary,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 32.dp)
            )
            Text(
                text = stringResource(id = R.string.i_am_happy_to_see_you_again),
                style = Typography.bodyMedium,
                color = GreyPrimary,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 8.dp)
            )
            EmailTextField(
                modifier = Modifier.padding(top = 32.dp),
                imeAction = ImeAction.Next
            )
            PasswordTextField(
                modifier = Modifier.padding(top = 16.dp),
                value = stringResource(id = R.string.password_hint),
                imeAction = ImeAction.Next
            )
            ClickableText(
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .padding(top = 16.dp),
                text = AnnotatedString(text = stringResource(id = R.string.forgot_password_question)) ,
                style = Typography.bodyMedium.copy(color = GreyPrimary),
            ) {
                TODO()
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    value: String = stringResource(id = R.string.email_hint),
    imeAction: ImeAction
) {
    var text by rememberSaveable { mutableStateOf("") }
    var containerColor by remember { mutableStateOf(GreyLighter) }
    var iconTint by remember { mutableStateOf(GreyPrimary) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                if (focusState.hasFocus) {
                    containerColor = Color.White
                    iconTint = PurplePrimary
                } else {
                    containerColor = GreyLighter
                    iconTint = GreyPrimary
                }
            },
        value = text,
        onValueChange = { newText -> text = newText },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = value,
                tint = iconTint
            )
        },
        placeholder = {
            Text(
                text = value,
                style = Typography.headlineMedium,
                color = GreyPrimary
            )
        },
        shape = Shapes.medium,
        textStyle = Typography.headlineMedium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = BlackPrimary,
            containerColor = containerColor,
            focusedBorderColor = PurplePrimary,
            unfocusedBorderColor = GreyLighter
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = imeAction
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    imeAction: ImeAction
) {
    var text by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    val visibilityIcon = if (isPasswordVisible) {
        painterResource(id = R.drawable.design_ic_outline_visibility_off)
    } else {
        painterResource(id = R.drawable.design_ic_outline_visibility)
    }
    var containerColor by remember { mutableStateOf(GreyLighter) }
    var iconTint by remember { mutableStateOf(GreyPrimary) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                if (focusState.hasFocus) {
                    containerColor = Color.White
                    iconTint = PurplePrimary
                } else {
                    containerColor = GreyLighter
                    iconTint = GreyPrimary
                }
            },
        value = text,
        onValueChange = { newText -> text = newText },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = value,
                tint = iconTint
            )
        },
        placeholder = {
            Text(
                text = value,
                style = Typography.headlineMedium,
                color = GreyPrimary
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible },
            ) {
                Icon(
                    painter = visibilityIcon,
                    contentDescription = null,
                    tint = iconTint
                )
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None
        else PasswordVisualTransformation('\u002a'),
        shape = Shapes.medium,
        textStyle = Typography.headlineMedium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = BlackPrimary,
            containerColor = containerColor,
            focusedBorderColor = PurplePrimary,
            unfocusedBorderColor = GreyLighter
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        )
    )
}

@Preview(showBackground = true)
@Composable
fun InputTextFieldPreview() {
    SignInScreen(navController = rememberNavController())
}