package com.jokku.aggregate.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.jokku.aggregate.R
import com.jokku.aggregate.ui.data.OnBoardingPage
import com.jokku.aggregate.ui.nav.Screen
import com.jokku.aggregate.ui.viewmodel.WelcomeViewModel
import com.jokku.aggregate.ui.views.BigActionButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val pages = viewModel.pages.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HorizontalPager(
            count = pages.value.size,
            state = pagerState,
            itemSpacing = (-65).dp
        ) { position ->
            PagerScreen(
                onBoardingPage = pages.value[position]
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
                activeColor = MaterialTheme.colorScheme.primary,
                inactiveColor = MaterialTheme.colorScheme.secondary
            )
            BigActionButton(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                text = if (pagerState.currentPage < pages.value.size - 1) {
                    stringResource(id = R.string.next)
                } else {
                    stringResource(id = R.string.get_started)
                }
            ) {
                viewModel.setLaunchScreen(screen = Screen.SelectFavoriteTopics.route)
                scope.launch {
                    if (pagerState.currentPage < pages.value.size - 1) {
                        pagerState.scrollToPage(pagerState.currentPage + 1)
                    } else {
                        navController.popBackStack()
                        navController.navigate(Screen.SignIn.route)
                    }
                }
            }
        }
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
fun WelcomeScreenPreview() {
}

@Preview(showBackground = true)
@Composable
fun FirstPage() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
    }
}

@Preview(showBackground = true)
@Composable
fun SecondPage() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Second)
    }
}

@Preview(showBackground = true)
@Composable
fun ThirdPage() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Third)
    }
}

