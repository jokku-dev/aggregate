package com.jokku.aggregate.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.theme.AggregateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameTextField(
    username: String,
    imeAction: ImeAction,
    onUsernameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(id = R.string.username_hint),
    focused: Boolean = false
) {
    var fieldFocused by rememberSaveable { mutableStateOf(focused) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState -> fieldFocused = focusState.hasFocus },
        value = username,
        onValueChange = { newValue -> onUsernameChange(newValue) },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_profile),
                contentDescription = placeholder
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                style = typography.bodyLarge
            )
        },
        shape = shapes.medium,
        textStyle = typography.bodyLarge,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colorScheme.onSurfaceVariant,
            containerColor = if (fieldFocused) colorScheme.surface else colorScheme.secondary,
            focusedBorderColor = colorScheme.primary,
            unfocusedBorderColor = colorScheme.secondary,
            focusedLeadingIconColor = colorScheme.primary,
            unfocusedLeadingIconColor = colorScheme.onSecondary,
            placeholderColor = colorScheme.onSecondary
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = imeAction
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    email: String,
    imeAction: ImeAction,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(id = R.string.email_hint),
    focused: Boolean = false,
    keyboardAction: KeyboardActionScope.() -> Unit = {}
) {
    var fieldFocused by rememberSaveable { mutableStateOf(focused) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState -> fieldFocused = focusState.hasFocus },
        value = email,
        onValueChange = { newValue -> onEmailChange(newValue) },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_email),
                contentDescription = placeholder
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                style = typography.bodyLarge
            )
        },
        shape = shapes.medium,
        textStyle = typography.bodyLarge,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colorScheme.onSurfaceVariant,
            containerColor = if (fieldFocused) colorScheme.surface else colorScheme.secondary,
            focusedBorderColor = colorScheme.primary,
            unfocusedBorderColor = colorScheme.secondary,
            focusedLeadingIconColor = colorScheme.primary,
            unfocusedLeadingIconColor = colorScheme.onSecondary,
            placeholderColor = colorScheme.onSecondary
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = keyboardAction)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    placeholder: String,
    password: String,
    imeAction: ImeAction,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardAction: KeyboardActionScope.() -> Unit = {},
    visible: Boolean = false,
    focused: Boolean = false
) {
    var passwordVisible by rememberSaveable { mutableStateOf(visible) }
    var fieldFocused by rememberSaveable { mutableStateOf(focused) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState -> fieldFocused = focusState.hasFocus },
        value = password,
        onValueChange = { newValue -> onPasswordChange(newValue) },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_lock),
                contentDescription = placeholder
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                style = typography.bodyLarge
            )
        },
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = if (passwordVisible) ImageVector.vectorResource(id = R.drawable.ic_visibility_off)
                    else ImageVector.vectorResource(id = R.drawable.ic_visibility),
                    contentDescription = if (passwordVisible) stringResource(id = R.string.hide_password)
                    else stringResource(id = R.string.show_password)
                )
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None
        else PasswordVisualTransformation('\u002a'),
        shape = shapes.medium,
        textStyle = typography.bodyLarge,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colorScheme.onSurfaceVariant,
            containerColor = if (fieldFocused) colorScheme.surface else colorScheme.secondary,
            focusedBorderColor = colorScheme.primary,
            unfocusedBorderColor = colorScheme.secondary,
            focusedLeadingIconColor = colorScheme.primary,
            unfocusedLeadingIconColor = colorScheme.onSecondary,
            placeholderColor = colorScheme.onSecondary,
            focusedTrailingIconColor = colorScheme.primary,
            unfocusedTrailingIconColor = colorScheme.onSecondary
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = keyboardAction)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    search: String,
    onSearchChange: (String) -> Unit,
    keyboardAction: KeyboardActionScope.() -> Unit,
    modifier: Modifier = Modifier,
    focused: Boolean = false
) {
    var fieldFocused by rememberSaveable { mutableStateOf(focused) }

    OutlinedTextField(
        value = search,
        onValueChange = { newValue -> onSearchChange(newValue) },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState -> fieldFocused = focusState.hasFocus },
        textStyle = typography.bodyLarge,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                contentDescription = stringResource(id = R.string.search)
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                style = typography.bodyLarge
            )
        },
        trailingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_mic),
                    contentDescription = stringResource(id = R.string.voice_input)
                )
            }
        },
        singleLine = true,
        shape = shapes.medium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colorScheme.onSurfaceVariant,
            containerColor = if (fieldFocused) colorScheme.surface else colorScheme.secondary,
            focusedBorderColor = colorScheme.primary,
            unfocusedBorderColor = colorScheme.secondary,
            focusedLeadingIconColor = colorScheme.primary,
            unfocusedLeadingIconColor = colorScheme.onSecondary,
            placeholderColor = colorScheme.onSecondary,
            focusedTrailingIconColor = colorScheme.primary,
            unfocusedTrailingIconColor = colorScheme.onSecondary
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onDone = keyboardAction)
    )
}

@Preview(showBackground = true, name = "Unfocused and Focused")
@Composable
fun UsernameTextFieldPreview() {
    AggregateTheme {
        Column {
            UsernameTextField(
                username = "Username",
                imeAction = ImeAction.Next,
                onUsernameChange = {},
                focused = false
            )
            UsernameTextField(
                username = "Username",
                imeAction = ImeAction.Next,
                onUsernameChange = {},
                focused = true
            )
        }

    }
}

@Preview(showBackground = true, name = "Unfocused and Focused")
@Composable
private fun EmailTextFieldPreview() {
    AggregateTheme {
        Column {
            EmailTextField(
                email = "Email",
                imeAction = ImeAction.Next,
                onEmailChange = {},
                focused = false
            )
            EmailTextField(
                email = "Email",
                imeAction = ImeAction.Next,
                onEmailChange = {},
                focused = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview() {
    AggregateTheme {
        Column {
            PasswordTextField(
                placeholder = "Password",
                password = "password",
                imeAction = ImeAction.Next,
                onPasswordChange = {}
            )
            PasswordTextField(
                placeholder = "Password",
                password = "password",
                imeAction = ImeAction.Next,
                onPasswordChange = {},
                visible = true,
                focused = true
            )
        }
    }
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    AggregateTheme {
        Column {
            SearchTextField(
                search = "Search",
                onSearchChange = {},
                keyboardAction = {},
                focused = false
            )
            SearchTextField(
                search = "Search",
                onSearchChange = {},
                keyboardAction = {},
                focused = true
            )
        }
    }
}