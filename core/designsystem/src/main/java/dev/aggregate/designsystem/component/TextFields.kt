package dev.aggregate.designsystem.component

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import dev.aggregate.app.R
import dev.aggregate.designsystem.theme.AggregateTheme

@Composable
fun UsernameTextField(
    username: String,
    imeAction: ImeAction,
    onUsernameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(id = dev.aggregate.app.R.string.username_hint)
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
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
            keyboardType = KeyboardType.Text,
            imeAction = imeAction
        )
    )
}

@Composable
fun EmailTextField(
    email: String,
    imeAction: ImeAction,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(id = dev.aggregate.app.R.string.email_hint),
    keyboardAction: KeyboardActionScope.() -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
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
            keyboardType = KeyboardType.Email,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = keyboardAction)
    )
}

@Composable
fun PasswordTextField(
    placeholder: String,
    password: String,
    imeAction: ImeAction,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardAction: KeyboardActionScope.() -> Unit = {},
    visible: Boolean = false
) {
    var passwordVisible by rememberSaveable {
        mutableStateOf(
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
                    imageVector = if (passwordVisible) ImageVector.vectorResource(
                        id = R.drawable.ic_visibility_off
                    )
                    else ImageVector.vectorResource(id = R.drawable.ic_visibility),
                    contentDescription = if (passwordVisible) stringResource(
                        id = dev.aggregate.app.R.string.hide_password
                    )
                    else stringResource(id = dev.aggregate.app.R.string.show_password)
                )
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None
        else PasswordVisualTransformation('\u002a'),
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
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = keyboardAction)
    )
}

@Composable
fun SearchTextField(
    search: String,
    onSearchChange: (String) -> Unit,
    keyboardAction: KeyboardActionScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = search,
        onValueChange = { newValue -> onSearchChange(newValue) },
        modifier = modifier.fillMaxWidth(),
        textStyle = typography.bodyLarge,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                contentDescription = stringResource(id = dev.aggregate.app.R.string.search)
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = dev.aggregate.app.R.string.search),
                style = typography.bodyLarge
            )
        },
        trailingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_mic),
                    contentDescription = stringResource(id = dev.aggregate.app.R.string.voice_input)
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
                onUsernameChange = {}
            )
            UsernameTextField(
                username = "Username",
                imeAction = ImeAction.Next,
                onUsernameChange = {}
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
                onEmailChange = {}
            )
            EmailTextField(
                email = "Email",
                imeAction = ImeAction.Next,
                onEmailChange = {}
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
                visible = true
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
