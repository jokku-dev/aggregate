package dev.aggregate.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.presentation.navigation.Screen
import dev.aggregate.ui.PreferencesButton

@androidx.compose.runtime.Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel<MainProfileViewModel>()
) {
    val state by viewModel.profileState.collectAsStateWithLifecycle()

    ProfileScreenContent(
        userSignedIn = state.userSignedIn,
        notificationsSwitchedOn = state.notificationsSwitchedOn,
        navigate = { route ->
            navController.navigate(route = route)
        },
        onNotificationsCheckedChanged = { status ->
            viewModel.changeNotificationsStatus(status = status)
        },
        onSignInOutClick = {
            if (state.userSignedIn) viewModel.changeSignInStatus(status = false)
            else navController.navigate(route = dev.aggregate.app.presentation.navigation.Screen.SignIn.route)
        }
    )
}

@androidx.compose.runtime.Composable
fun ProfileScreenContent(
    userSignedIn: Boolean,
    notificationsSwitchedOn: Boolean,
    navigate: (String) -> Unit,
    onNotificationsCheckedChanged: (Boolean) -> Unit,
    onSignInOutClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    dev.aggregate.app.ui.CommonColumn {
        Text(
            text = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.profile),
            style = typography.headlineLarge,
            color = colorScheme.onSurfaceVariant,
            modifier = androidx.compose.ui.Modifier.align(androidx.compose.ui.Alignment.Start)
        )
        ProfileInfo(
            userSignedIn = userSignedIn,
            modifier = androidx.compose.ui.Modifier.padding(top = 24.dp)
        )
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(top = 32.dp)
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            dev.aggregate.app.ui.PreferencesButton(
                title = dev.aggregate.app.R.string.notifications,
                buttonType = dev.aggregate.app.ui.ButtonType.SWITCH,
                checked = notificationsSwitchedOn,
                onCheckedChanged = onNotificationsCheckedChanged
            )
            dev.aggregate.app.ui.PreferencesButton(
                title = dev.aggregate.app.R.string.language,
                buttonType = dev.aggregate.app.ui.ButtonType.DIRECTION,
                onClick = { navigate(dev.aggregate.app.presentation.navigation.Screen.Language.route) }
            )
            if (userSignedIn)
                dev.aggregate.app.ui.PreferencesButton(
                    title = dev.aggregate.app.R.string.change_password,
                    buttonType = dev.aggregate.app.ui.ButtonType.DIRECTION,
                    onClick = { navigate(dev.aggregate.app.presentation.navigation.Screen.ChangePassword.route) }
                )
            dev.aggregate.app.ui.PreferencesButton(
                title = dev.aggregate.app.R.string.privacy,
                buttonType = dev.aggregate.app.ui.ButtonType.DIRECTION,
                modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
                onClick = { navigate(dev.aggregate.app.presentation.navigation.Screen.Privacy.route) }
            )
            dev.aggregate.app.ui.PreferencesButton(
                title = dev.aggregate.app.R.string.terms_and_conditions,
                buttonType = dev.aggregate.app.ui.ButtonType.DIRECTION,
                onClick = { navigate(dev.aggregate.app.presentation.navigation.Screen.TermsAndConditions.route) }
            )
            dev.aggregate.app.ui.PreferencesButton(
                title = if (userSignedIn) dev.aggregate.app.R.string.sign_out else dev.aggregate.app.R.string.sign_in,
                buttonType = dev.aggregate.app.ui.ButtonType.SIGN_IN_OUT,
                modifier = androidx.compose.ui.Modifier.padding(top = 16.dp),
                onClick = onSignInOutClick
            )
        }
    }
}

@androidx.compose.runtime.Composable
fun ProfileInfo(
    userSignedIn: Boolean,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    if (userSignedIn) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Image(
                painter = androidx.compose.ui.res.painterResource(id = dev.aggregate.app.R.drawable.img_avatar_mock),
                contentDescription = androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.profile_image),
                modifier = androidx.compose.ui.Modifier
                    .size(72.dp)
                    .clip(shape = CircleShape)
            )
            Column(
                modifier = androidx.compose.ui.Modifier.padding(start = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.Start
            ) {
                Text(
                    text = "Name Surname",
                    style = typography.titleLarge,
                    color = colorScheme.onSurfaceVariant
                )
                Text(
                    text = "emailname@gmail.com",
                    style = typography.labelMedium,
                    color = colorScheme.onSecondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun SignedInProfileScreenPreview() {
    dev.aggregate.app.designsystem.theme.AggregateTheme {
        ProfileScreenContent(
            userSignedIn = true,
            notificationsSwitchedOn = true,
            navigate = {},
            onNotificationsCheckedChanged = {},
            onSignInOutClick = {}
        )
    }

}