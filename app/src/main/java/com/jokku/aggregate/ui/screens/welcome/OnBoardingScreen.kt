package com.jokku.aggregate.ui.screens.welcome

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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.jokku.aggregate.ui.entity.OnBoardingPage
import com.jokku.aggregate.ui.nav.Screen
import com.jokku.aggregate.ui.viewmodel.WelcomeViewModel
import com.jokku.aggregate.ui.views.BigActionButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavHostController,
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val state = viewModel.onBoardingState.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HorizontalPager(
            pageCount = state.pages.size,
            state = pagerState,
            pageSpacing = (-65).dp
        ) { page ->
            PagerScreen(
                onBoardingPage = state.pages[page]
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
                pageCount = state.pages.size,
                activeColor = MaterialTheme.colorScheme.primary,
                inactiveColor = MaterialTheme.colorScheme.secondary
            )
            BigActionButton(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                text = if (!state.isLastPage) stringResource(id = R.string.next)
                else stringResource(id = R.string.get_started),
                onClick = {
                    // scrollToPage is suspend and therefore requires scope
                    scope.launch {
                        if (!state.isLastPage) {
                            viewModel.checkNextPageIsLast(nextPage = state.pages[pagerState.currentPage + 1])
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        } else {
                            viewModel.setLaunchScreen(screen = Screen.SelectFavoriteTopics.route)
                            navController.popBackStack()
                            navController.navigate(Screen.SelectFavoriteTopics.route)
                        }
                    }
                }
            )
        }
    }
    LaunchedEffect(key1 = pagerState.isScrollInProgress) {
        viewModel.checkNextPageIsLast(nextPage = state.pages[pagerState.currentPage])
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(0.75f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(id = onBoardingPage.title)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(0.75f),
            text = stringResource(id = onBoardingPage.title),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(top = 16.dp),
            text = stringResource(id = onBoardingPage.description),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FirstPage() {

}

@Preview(showBackground = true)
@Composable
fun SecondPage() {

}

@Preview(showBackground = true)
@Composable
fun ThirdPage() {

}

