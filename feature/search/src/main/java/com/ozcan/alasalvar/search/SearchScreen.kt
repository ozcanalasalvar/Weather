package com.ozcan.alasalvar.search

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ozcan.alasalvar.designsystem.theme.component.AppSearchView
import com.ozcan.alasalvar.model.data.City

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SearchScreen(
    onCancelClick: () -> Unit,
    onCityClicked: (City) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val uiState: SearchUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = 30.dp, start = 20.dp, end = 20.dp)
    ) {

        SearchField(
            onTextChanged = viewModel::onSearch,
            onCancelClick = onCancelClick,
            label = "Search for a city" //TODO stringResource(id = R.string.search_city)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp)
        ) {
            itemsIndexed(uiState.results) { _, city ->
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = city.name + " ," + city.country,
                    color = MaterialTheme.colors.secondary,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

            }

        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit,
    onCancelClick: () -> Unit,
    label: String
) {

    var query: String by rememberSaveable { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        AppSearchView(
            onTextChanged = {
                query = it
                onTextChanged(it)
            },
            label = label
        )

        AnimatedVisibility(
            visible = !query.isEmpty(),
            enter = slideInHorizontally(
                initialOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 250,
                    easing = LinearEasing // interpolator
                )
            ),
            exit = slideOutHorizontally(
                targetOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 250,
                    easing = LinearEasing
                )
            )
        ) {
            Text(
                text = "Cancel",
                fontSize = 18.sp,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth()
                    .clickable { onCancelClick() },
                textAlign = TextAlign.End,
            )
        }


    }

}