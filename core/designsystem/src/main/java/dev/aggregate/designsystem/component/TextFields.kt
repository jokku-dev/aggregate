package dev.aggregate.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import dev.aggregate.presentation.theme.AggregateTheme

@androidx.compose.runtime.Composable
fun UsernameTextField(
    username: String,
    imeAction: androidx.compose.ui.text.input.ImeAction,
    onUsernameChange: (String) -> Unit,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
    placeholder: String = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.username_hint)
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = username,
        onValueChange = { newValue -> onUsernameChange(newValue) },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = androidx.compose.ui.graphics.vector.ImageVector.vectorResource(id = dev.aggregate.app.R.drawable.ic_profile),
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
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = colorScheme.onSurfaceVariant,
            unfocusedTextColor = colorScheme.onSurfaceVariant,
            focusedContainerColor = colorScheme.surface,
            unfocusedContainerColor = colorScheme.secondary,
            focusedBorderColor = colorScheme.primary,
            unfocusedBorderColor = colorScheme.secondary,
            focusedLeadingIconColor = colorScheme.primary,
            unfocusedLeadingIconColor = colorScheme.onSecondary,
            focusedPlaceholderColor = colorScheme.onSecondary,
            unfocusedPlaceholderColor = colorScheme.onSecondary
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = androidx.compose.ui.text.input.KeyboardType.Text,
            imeAction = imeAction
        )
    )
}

@androidx.compose.runtime.Composable
fun EmailTextField(
    email: String,
    imeAction: androidx.compose.ui.text.input.ImeAction,
    onEmailChange: (String) -> Unit,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
    placeholder: String = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.email_hint),
    keyboardAction: KeyboardActionScope.() -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = email,
        onValueChange = { newValue -> onEmailChange(newValue) },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = androidx.compose.ui.graphics.vector.ImageVector.vectorResource(id = dev.aggregate.app.R.drawable.ic_email),
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
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = colorScheme.onSurfaceVariant,
            unfocusedTextColor = colorScheme.onSurfaceVariant,
            focusedContainerColor = colorScheme.surface,
            unfocusedContainerColor = colorScheme.secondary,
            focusedBorderColor = colorScheme.primary,
            unfocusedBorderColor = colorScheme.secondary,
            focusedLeadingIconColor = colorScheme.primary,
            unfocusedLeadingIconColor = colorScheme.onSecondary,
            focusedPlaceholderColor = colorScheme.onSecondary,
            unfocusedPlaceholderColor = colorScheme.onSecondary
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = androidx.compose.ui.text.input.KeyboardType.Email,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = keyboardAction)
    )
}

@androidx.compose.runtime.Composable
fun PasswordTextField(
    placeholder: String,
    password: String,
    imeAction: androidx.compose.ui.text.input.ImeAction,
    onPasswordChange: (String) -> Unit,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
    keyboardAction: KeyboardActionScope.() -> Unit = {},
    visible: Boolean = false
) {
    var passwordVisible by androidx.compose.runtime.saveable.rememberSaveable {
        androidx.compose.runtime.mutableStateOf(
            visible
        )
    }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = password,
        onValueChange = { newValue -> onPasswordChange(newValue) },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = androidx.compose.ui.graphics.vector.ImageVector.vectorResource(id = dev.aggregate.app.R.drawable.ic_lock),
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
                    imageVector = if (passwordVisible) androidx.compose.ui.graphics.vector.ImageVector.vectorResource(id = dev.aggregate.app.R.drawable.ic_visibility_off)
                    else androidx.compose.ui.graphics.vector.ImageVector.vectorResource(id = dev.aggregate.app.R.drawable.ic_visibility),
                    contentDescription = if (passwordVisible) androidx.compose.ui.res.stringResource(
                        id = dev.aggregate.app.R.string.hide_password
                    )
                    else androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.show_password)
                )
            }
        },
        visualTransformation = if (passwordVisible) androidx.compose.ui.text.input.VisualTransformation.None
        else androidx.compose.ui.text.input.PasswordVisualTransformation('\u002a'),
        shape = shapes.medium,
        textStyle = typography.bodyLarge,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = colorScheme.onSurfaceVariant,
            unfocusedTextColor = colorScheme.onSurfaceVariant,
            focusedContainerColor = colorScheme.surface,
            unfocusedContainerColor = colorScheme.secondary,
            focusedBorderColor = colorScheme.primary,
            unfocusedBorderColor = colorScheme.secondary,
            focusedLeadingIconColor = colorScheme.primary,
            unfocusedLeadingIconColor = colorScheme.onSecondary,
            focusedPlaceholderColor = colorScheme.onSecondary,
            unfocusedPlaceholderColor = colorScheme.onSecondary,
            focusedTrailingIconColor = colorScheme.primary,
            unfocusedTrailingIconColor = colorScheme.onSecondary
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = androidx.compose.ui.text.input.KeyboardType.Password,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = keyboardAction)
    )
}

@androidx.compose.runtime.Composable
fun SearchTextField(
    search: String,
    onSearchChange: (String) -> Unit,
    keyboardAction: KeyboardActionScope.() -> Unit,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    OutlinedTextField(
        value = search,
        onValueChange = { newValue -> onSearchChange(newValue) },
        modifier = modifier.fillMaxWidth(),
        textStyle = typography.bodyLarge,
        leadingIcon = {
            Icon(
                imageVector = androidx.compose.ui.graphics.vector.ImageVector.vectorResource(id = dev.aggregate.app.R.drawable.ic_search),
                contentDescription = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.search)
            )
        },
        placeholder = {
            Text(
                text = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.search),
                style = typography.bodyLarge
            )
        },
        trailingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = androidx.compose.ui.graphics.vector.ImageVector.vectorResource(id = dev.aggregate.app.R.drawable.ic_mic),
                    contentDescription = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.voice_input)
                )
            }
        },
        singleLine = true,
        shape = shapes.medium,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = colorScheme.onSurfaceVariant,
            unfocusedTextColor = colorScheme.onSurfaceVariant,
            focusedContainerColor = colorScheme.surface,
            unfocusedContainerColor = colorScheme.secondary,
            focusedBorderColor = colorScheme.primary,
            unfocusedBorderColor = colorScheme.secondary,
            focusedLeadingIconColor = colorScheme.primary,
            unfocusedLeadingIconColor = colorScheme.onSecondary,
            focusedPlaceholderColor = colorScheme.onSecondary,
            unfocusedPlaceholderColor = colorScheme.onSecondary,
            focusedTrailingIconColor = colorScheme.primary,
            unfocusedTrailingIconColor = colorScheme.onSecondary
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = androidx.compose.ui.text.input.KeyboardType.Text,
            imeAction = androidx.compose.ui.text.input.ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onDone = keyboardAction)
    )
}

@Preview(showBackground = true, name = "Unfocused and Focused")
@androidx.compose.runtime.Composable
fun UsernameTextFieldPreview() {
    dev.aggregate.app.presentation.theme.AggregateTheme {
        Column {
            UsernameTextField(
                username = "Username",
                imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                onUsernameChange = {}
            )
            UsernameTextField(
                username = "Username",
                imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                onUsernameChange = {}
            )
        }

    }
}

@Preview(showBackground = true, name = "Unfocused and Focused")
@androidx.compose.runtime.Composable
private fun EmailTextFieldPreview() {
    dev.aggregate.app.presentation.theme.AggregateTheme {
        Column {
            EmailTextField(
                email = "Email",
                imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                onEmailChange = {}
            )
            EmailTextField(
                email = "Email",
                imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                onEmailChange = {}
            )
        }
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
private fun PasswordTextFieldPreview() {
    dev.aggregate.app.presentation.theme.AggregateTheme {
        Column {
            PasswordTextField(
                placeholder = "Password",
                password = "password",
                imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                onPasswordChange = {}
            )
            PasswordTextField(
                placeholder = "Password",
                password = "password",
                imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                onPasswordChange = {},
                visible = true
            )
        }
    }
}

@Preview
@androidx.compose.runtime.Composable
fun SearchTextFieldPreview() {
    dev.aggregate.app.presentation.theme.AggregateTheme {
        Column {
            SearchTextField(
                search = "Search",
                onSearchChange = {},
                keyboardAction = {}
            )
            SearchTextField(
                search = "Search",
                onSearchChange = {},
                keyboardAction = {}
            )
        }
    }
}