package com.jokku.aggregate.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameTextField(
    imeAction: ImeAction,
    username: String,
    onUsernameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(id = R.string.username_hint)
) {
    var focus by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState -> focus = focusState.hasFocus },
        value = username,
        onValueChange = { newValue -> onUsernameChange(newValue) },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_outline_person),
                contentDescription = placeholder,
                tint = if (focus) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSecondary
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        shape = MaterialTheme.shapes.medium,
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            containerColor = if (focus) MaterialTheme.colorScheme.surface
            else MaterialTheme.colorScheme.secondary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary
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
    imeAction: ImeAction,
    email: String,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(id = R.string.email_hint)
) {
    var focus by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState -> focus = focusState.hasFocus },
        value = email,
        onValueChange = { newValue -> onEmailChange(newValue) },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_outline_email),
                contentDescription = placeholder,
                tint = if (focus) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSecondary
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        shape = MaterialTheme.shapes.medium,
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            containerColor = if (focus) MaterialTheme.colorScheme.surface
            else MaterialTheme.colorScheme.secondary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary
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
    placeholder: String,
    imeAction: ImeAction,
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardAction: KeyboardActionScope.() -> Unit = {}
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var focus by rememberSaveable { mutableStateOf(false) }
    val visibilityIcon = if (isPasswordVisible) R.drawable.ic_outline_visibility_off
    else R.drawable.ic_outline_visibility

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState -> focus = focusState.hasFocus },
        value = password,
        onValueChange = { newValue -> onPasswordChange(newValue) },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_outline_lock),
                contentDescription = placeholder,
                tint = if (focus) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSecondary
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = visibilityIcon),
                    contentDescription = null,
                    tint = if (focus) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSecondary
                )
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None
        else PasswordVisualTransformation('\u002a'),
        shape = MaterialTheme.shapes.medium,
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            containerColor = if (focus) MaterialTheme.colorScheme.surface
            else MaterialTheme.colorScheme.secondary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary
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
    modifier: Modifier = Modifier
) {
    var focus by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = search,
        onValueChange = { newValue -> onSearchChange(newValue) },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState -> focus = focusState.hasFocus },
        textStyle = MaterialTheme.typography.bodyLarge,
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_outline_search),
                contentDescription = stringResource(id = R.string.search),
                tint = if (focus) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSecondary
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { TODO() },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_outline_mic),
                    contentDescription = null,
                    tint = if (focus) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSecondary
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onDone = keyboardAction),
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            containerColor = if (focus) MaterialTheme.colorScheme.surface
            else MaterialTheme.colorScheme.secondary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFieldPreview() {

}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview() {

}