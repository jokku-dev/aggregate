package com.jokku.aggregate.presentation.screens.welcome

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.jokku.aggregate.R
import com.jokku.aggregate.presentation.model.UiOnBoardingPage
import com.jokku.aggregate.presentation.nav.Screen
import com.jokku.aggregate.presentation.theme.AggregateTheme
import com.jokku.aggregate.presentation.views.BigActionButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavHostController,
    viewModel: MainWelcomeViewModel = hiltViewModel()
) {
    val state by viewModel.onBoardingState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    OnBoardingScreenContent(
        pages = state.pages,
        isLastPage = state.isLastPage,
        pagerState = pagerState,
        onButtonClick = {
            // scrollToPage is suspend and therefore requires scope
            scope.launch {
                if (!state.isLastPage) {
                    viewModel.checkNextPageIsLast(state.pages[pagerState.currentPage + 1])
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                } else {
                    viewModel.setLaunchScreen(Screen.SelectFavoriteTopics.route)
                    navController.popBackStack(route = Screen.OnBoarding.route, inclusive = true)
                    navController.navigate(route = Screen.SelectFavoriteTopics.route)
                }
            }
        }
    )
    LaunchedEffect(key1 = pagerState.isScrollInProgress) {
        viewModel.checkNextPageIsLast(state.pages[pagerState.currentPage])
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreenContent(
    pages: List<UiOnBoardingPage>,
    isLastPage: Boolean,
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
            pageCount = pages.size,
            state = pagerState,
            pageSpacing = (-65).dp
        ) { page ->
            PagerScreen(
                image = painterResource(id = pages[page].image),
                title = stringResource(id = pages[page].title),
                description = stringResource(id = pages[page].description),
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            HorizontalPagerIndicator(
                modifier = Modifier.padding(bottom = 32.dp),
                pagerState = pagerState,
                pageCount = pages.size,
                activeColor = colorScheme.primary,
                inactiveColor = colorScheme.secondary
            )
            BigActionButton(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                text = if (!isLastPage) stringResource(id = R.string.next)
                else stringResource(id = R.string.get_started),
                onClick = onButtonClick
            )
        }
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

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    AggregateTheme {
        OnBoardingScreenContent(
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
            isLastPage = false,
            pagerState = rememberPagerState(),
            onButtonClick = {}
        )
    }
}