package com.jokku.aggregate.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.nav.Screen
import com.jokku.aggregate.ui.viewmodel.HomeViewModel
import com.jokku.aggregate.ui.views.CommonColumn

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val state = viewModel.profileState.collectAsStateWithLifecycle().value

    CommonColumn {
        Text(
            text = stringResource(id = R.string.profile),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.align(Alignment.Start)
        )
        ProfileInfo(
            signedIn = state.signedIn,
            modifier = Modifier.padding(top = 32.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp)
                .verticalScroll(
                    state = scrollState,
                    flingBehavior = ScrollableDefaults.flingBehavior()
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PreferencesButton(
                title = R.string.notifications,
                buttonType = ButtonType.SWITCH,
                checked = state.notifications,
                onCheckedChanged = { checked -> }
            )
            PreferencesButton(
                title = R.string.language,
                buttonType = ButtonType.DIRECTION,
                onClick = { navController.navigate(route = Screen.Language.route) }
            )
            if (state.signedIn)
                PreferencesButton(
                    title = R.string.change_password,
                    buttonType = ButtonType.DIRECTION,
                    onClick = { navController.navigate(route = Screen.ChangePassword.route) }
                )
            PreferencesButton(
                title = R.string.privacy,
                buttonType = ButtonType.DIRECTION,
                modifier = Modifier.padding(top = 16.dp),
                onClick = { navController.navigate(route = Screen.Privacy.route) }
            )
            PreferencesButton(
                title = R.string.terms_and_conditions,
                buttonType = ButtonType.DIRECTION,
                onClick = { navController.navigate(route = Screen.TermsAndConditions.route) }
            )
            PreferencesButton(
                title = if (state.signedIn) R.string.sign_out else R.string.sign_in,
                buttonType = ButtonType.SIGN_IN_OUT,
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    if (state.signedIn) viewModel.changeSignedIn()
                    else navController.navigate(route = Screen.SignIn.route)
                }
            )
        }
    }
}

@Composable
fun ProfileInfo(
    signedIn: Boolean,
    modifier: Modifier = Modifier
) {
    if (signedIn) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_avatar_mock),
                contentDescription = "Name Surname",
                modifier = Modifier
                    .size(72.dp)
                    .clip(shape = CircleShape)
            )
            Column(
                modifier = Modifier.padding(start = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Name Surname",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "emailname@gmail.com",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

@Composable
fun PreferencesButton(
    title: Int,
    buttonType: ButtonType,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    checked: Boolean = false,
    onCheckedChanged: (Boolean) -> Unit = {},
    signedIn: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(horizontal = 24.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        when (buttonType) {
            ButtonType.DIRECTION -> {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_outline_arrow_right),
                    contentDescription = stringResource(id = title),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            ButtonType.SIGN_IN_OUT -> {
                Icon(
                    imageVector = if (signedIn) ImageVector.vectorResource(R.drawable.ic_outline_logout)
                    else ImageVector.vectorResource(R.drawable.ic_outline_login),
                    contentDescription = stringResource(id = title),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            ButtonType.SWITCH -> {
                Switch(
                    checked = checked,
                    onCheckedChange = onCheckedChanged,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.surface,
                        checkedTrackColor = MaterialTheme.colorScheme.primary,
                        uncheckedThumbColor = MaterialTheme.colorScheme.surface,
                        uncheckedTrackColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }
    }
}

enum class ButtonType {
    DIRECTION, SIGN_IN_OUT, SWITCH
}

@Preview(showBackground = true)
@Composable
fun PreferencesButtonPreview() {
    PreferencesButton(
        title = R.string.notifications,
        onClick = {},
        buttonType = ButtonType.SWITCH,
        checked = true
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileInfoPreview() {
    ProfileInfo(signedIn = true)
}