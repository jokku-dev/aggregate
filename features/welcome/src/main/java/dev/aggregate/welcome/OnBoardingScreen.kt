package dev.aggregate.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.aggregate.designsystem.Screen
import dev.aggregate.designsystem.component.BigActionButton
import dev.aggregate.designsystem.theme.AggregateTheme
import dev.aggregate.model.ui.UiOnBoardingPage
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    navController: NavHostController,
    viewModel: WelcomeViewModel = hiltViewModel<MainWelcomeViewModel>()
) {
    val state by viewModel.onBoardingUiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState { state.pages.size }
    val scope = rememberCoroutineScope()

    OnBoardingScreenContent(
        pages = state.pages,
        pagerState = pagerState,
        onButtonClick = {
            // scrollToPage is suspend and therefore requires scope
            scope.launch {
                if (pagerState.currentPage + 1 < state.pages.size) {
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

@Composable
fun OnBoardingScreenContent(
    pages: List<UiOnBoardingPage>,
    pagerState: PagerState,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HorizontalPager(
            state = pagerState,
            pageSpacing = (-65).dp,
        ) { page ->
            PagerScreen(
                image = painterResource(id = pages[page].image),
                title = stringResource(id = pages[page].title),
                description = stringResource(id = pages[page].description),
            )
        }
        PagerSelectorAndButton(
            pages = pages,
            pagerState = pagerState,
            onButtonClick = onButtonClick
        )
    }
}

@Composable
fun PagerScreen(
    image: Painter,
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(0.75f),
            painter = image,
            contentDescription = title
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(0.75f),
            text = title,
            style = typography.headlineLarge,
            color = colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(top = 16.dp),
            text = description,
            style = typography.bodyMedium,
            color = colorScheme.onSecondary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PagerSelectorAndButton(
    pages: List<UiOnBoardingPage>,
    pagerState: PagerState,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) {
                    colorScheme.primary
                } else {
                    colorScheme.secondary
                }
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp)
                )
            }
        }
        BigActionButton(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .padding(horizontal = 16.dp),
            text = if (pagerState.currentPage + 1 < pages.size) {
                stringResource(id = R.string.next)
            } else {
                stringResource(id = R.string.get_started)
            },
            onClick = onButtonClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    AggregateTheme {
        PagerScreen(
            painterResource(R.drawable.img_onboarding),
            stringResource(R.string.on_board_first_title),
            stringResource(R.string.on_board_first_description)
        )
        PagerSelectorAndButton(
            pages = listOf(
                UiOnBoardingPage(
                    R.drawable.img_onboarding,
                    R.string.on_board_first_title,
                    R.string.on_board_first_description
                ),
                UiOnBoardingPage(
                    R.drawable.img_onboarding,
                    R.string.on_board_second_title,
                    R.string.on_board_second_description
                ),
                UiOnBoardingPage(
                    R.drawable.img_onboarding,
                    R.string.on_board_third_title,
                    R.string.on_board_third_description
                )
            ),
            pagerState = rememberPagerState { 1 },
            onButtonClick = {}
        )
    }
}
