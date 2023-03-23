package com.jokku.aggregate.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.screens.SignInScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    value: String = stringResource(id = R.string.email_hint),
    imeAction: ImeAction
) {
    var text by rememberSaveable { mutableStateOf("") }
    var focus by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState -> focus = focusState.hasFocus },
        value = text,
        onValueChange = { newText -> text = newText },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = value,
                tint = if (focus) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSecondary
            )
        },
        placeholder = {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        shape = MaterialTheme.shapes.medium,
        textStyle = MaterialTheme.typography.headlineMedium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            containerColor = if (focus) MaterialTheme.colorScheme.surface
            else MaterialTheme.colorScheme.secondary,
            focusedBorderColor = MaterialTheme.colorScheme.outline,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
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
    var focus by rememberSaveable { mutableStateOf(false) }
    val visibilityIcon = if (isPasswordVisible) {
        painterResource(id = R.drawable.ic_outline_visibility_off)
    } else {
        painterResource(id = R.drawable.ic_outline_visibility)
    }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState -> focus = focusState.hasFocus },
        value = text,
        onValueChange = { newText -> text = newText },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = value,
                tint = if (focus) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSecondary
            )
        },
        placeholder = {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible },
            ) {
                Icon(
                    painter = visibilityIcon,
                    contentDescription = null,
                    tint = if (focus) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSecondary
                )
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None
        else PasswordVisualTransformation('\u002a'),
        shape = MaterialTheme.shapes.medium,
        textStyle = MaterialTheme.typography.headlineMedium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            containerColor = if (focus) MaterialTheme.colorScheme.surface
            else MaterialTheme.colorScheme.secondary,
            focusedBorderColor = MaterialTheme.colorScheme.outline,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun InputTextFieldPreview() {
    SignInScreen(navController = rememberNavController())
}