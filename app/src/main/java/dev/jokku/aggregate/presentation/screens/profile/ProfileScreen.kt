package dev.jokku.aggregate.presentation.screens.profile

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.jokku.aggregate.R
import dev.jokku.aggregate.presentation.nav.Screen
import dev.jokku.aggregate.presentation.theme.AggregateTheme
import dev.jokku.aggregate.presentation.views.ButtonType
import dev.jokku.aggregate.presentation.views.CommonColumn
import dev.jokku.aggregate.presentation.views.PreferencesButton

@Composable
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
            else navController.navigate(route = Screen.SignIn.route)
        }
    )
}

@Composable
fun ProfileScreenContent(
    userSignedIn: Boolean,
    notificationsSwitchedOn: Boolean,
    navigate: (String) -> Unit,
    onNotificationsCheckedChanged: (Boolean) -> Unit,
    onSignInOutClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    CommonColumn {
        Text(
            text = stringResource(id = R.string.profile),
            style = typography.headlineLarge,
            color = colorScheme.onSurfaceVariant,
            modifier = Modifier.align(Alignment.Start)
        )
        ProfileInfo(
            userSignedIn = userSignedIn,
            modifier = Modifier.padding(top = 24.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp)
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PreferencesButton(
                title = R.string.notifications,
                buttonType = ButtonType.SWITCH,
                checked = notificationsSwitchedOn,
                onCheckedChanged = onNotificationsCheckedChanged
            )
            PreferencesButton(
                title = R.string.language,
                buttonType = ButtonType.DIRECTION,
                onClick = { navigate(Screen.Language.route) }
            )
            if (userSignedIn)
                PreferencesButton(
                    title = R.string.change_password,
                    buttonType = ButtonType.DIRECTION,
                    onClick = { navigate(Screen.ChangePassword.route) }
                )
            PreferencesButton(
                title = R.string.privacy,
                buttonType = ButtonType.DIRECTION,
                modifier = Modifier.padding(top = 16.dp),
                onClick = { navigate(Screen.Privacy.route) }
            )
            PreferencesButton(
                title = R.string.terms_and_conditions,
                buttonType = ButtonType.DIRECTION,
                onClick = { navigate(Screen.TermsAndConditions.route) }
            )
            PreferencesButton(
                title = if (userSignedIn) R.string.sign_out else R.string.sign_in,
                buttonType = ButtonType.SIGN_IN_OUT,
                modifier = Modifier.padding(top = 16.dp),
                onClick = onSignInOutClick
            )
        }
    }
}

@Composable
fun ProfileInfo(
    userSignedIn: Boolean,
    modifier: Modifier = Modifier
) {
    if (userSignedIn) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_avatar_mock),
                contentDescription = stringResource(id = R.string.profile_image),
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
@Composable
fun SignedInProfileScreenPreview() {
    AggregateTheme {
        ProfileScreenContent(
            userSignedIn = true,
            notificationsSwitchedOn = true,
            navigate = {},
            onNotificationsCheckedChanged = {},
            onSignInOutClick = {}
        )
    }

}