package dev.aggregate.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.accompanist.pager.HorizontalPagerIndicator
import dev.aggregate.app.navigation.Screen
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.presentation.model.UiOnBoardingPage
import dev.aggregate.ui.BigActionButton
import kotlinx.coroutines.launch
import kotlin.compareTo
import kotlin.text.compareTo

@OptIn(ExperimentalFoundationApi::class)
@androidx.compose.runtime.Composable
fun OnBoardingScreen(
    navController: NavHostController,
    viewModel: dev.aggregate.app.presentation.screens.welcome.MainWelcomeViewModel = hiltViewModel()
) {
    val state by viewModel.onBoardingUiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState()
    val scope = androidx.compose.runtime.rememberCoroutineScope()

    OnBoardingScreenContent(
        pages = state.pages,
        pagerState = pagerState,
        onButtonClick = {
            // scrollToPage is suspend and therefore requires scope
            scope.launch {
                if (pagerState.currentPage < state.pages.size) {
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                } else {
                    viewModel.setLaunchScreen(Screen.SelectFavoriteTopics.route)
                    navController.popBackStack(route = Screen.OnBoarding.route, inclusive = true)
                    navController.navigate(route = Screen.SelectFavoriteTopics.route)
                }
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@androidx.compose.runtime.Composable
fun OnBoardingScreenContent(
    pages: List<dev.aggregate.app.presentation.model.UiOnBoardingPage>,
    pagerState: PagerState,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HorizontalPager(
            pageCount = pages.size,
            state = pagerState,
            pageSpacing = (-65).dp
        ) { page ->
            PagerScreen(
                image = androidx.compose.ui.res.painterResource(id = pages[page].image),
                title = androidx.compose.ui.res.stringResource(id = pages[page].title),
                description = androidx.compose.ui.res.stringResource(id = pages[page].description),
            )
        }
        Column(
            modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            HorizontalPagerIndicator(
                modifier = androidx.compose.ui.Modifier.padding(bottom = 32.dp),
                pagerState = pagerState,
                pageCount = pages.size,
                activeColor = colorScheme.primary,
                inactiveColor = colorScheme.secondary
            )
            dev.aggregate.app.ui.BigActionButton(
                modifier = androidx.compose.ui.Modifier
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                text = if (pagerState.currentPage < pages.size) androidx.compose.ui.res.stringResource(
                    id = dev.aggregate.app.R.string.next
                )
                else androidx.compose.ui.res.stringResource(id = dev.aggregate.app.R.string.get_started),
                onClick = onButtonClick
            )
        }
    }

}

@androidx.compose.runtime.Composable
fun PagerScreen(
    image: androidx.compose.ui.graphics.painter.Painter,
    title: String,
    description: String
) {
    Column(
        modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = androidx.compose.ui.Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(0.75f),
            painter = image,
            contentDescription = title
        )
        Text(
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth(0.75f),
            text = title,
            style = typography.headlineLarge,
            color = colorScheme.onSurfaceVariant,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        Text(
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth(0.6f)
                .padding(top = 16.dp),
            text = description,
            style = typography.bodyMedium,
            color = colorScheme.onSecondary,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun OnBoardingScreenPreview() {
    dev.aggregate.app.designsystem.theme.AggregateTheme {
        OnBoardingScreenContent(
            pages = listOf(
                dev.aggregate.app.presentation.model.UiOnBoardingPage(
                    dev.aggregate.app.R.drawable.img_onboarding,
                    dev.aggregate.app.R.string.on_board_first_title,
                    dev.aggregate.app.R.string.on_board_first_description
                ),
                dev.aggregate.app.presentation.model.UiOnBoardingPage(
                    dev.aggregate.app.R.drawable.img_onboarding,
                    dev.aggregate.app.R.string.on_board_second_title,
                    dev.aggregate.app.R.string.on_board_second_description
                ),
                dev.aggregate.app.presentation.model.UiOnBoardingPage(
                    dev.aggregate.app.R.drawable.img_onboarding,
                    dev.aggregate.app.R.string.on_board_third_title,
                    dev.aggregate.app.R.string.on_board_third_description
                )
            ),
            pagerState = rememberPagerState(),
            onButtonClick = {}
        )
    }
}