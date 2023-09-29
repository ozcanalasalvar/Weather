package com.ozcan.alasalvar.search

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ozcan.alasalvar.designsystem.theme.component.AppSearchView
import com.ozcan.alasalvar.designsystem.theme.component.bounceClick
import com.ozcan.alasalvar.model.data.City
import weather.feature.search.R

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
            hint =stringResource(id = R.string.search_city)
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
                    fontSize = 15.sp,
                    modifier = Modifier.bounceClick { onCityClicked(city) }
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
    hint: String
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        AppSearchView(
            onTextChanged = {
                onTextChanged(it)
            },
            label = hint,
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Text(
            text = stringResource(id = R.string.cancel),
            fontSize = 15.sp,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth()
                .clickable { onCancelClick() },
            textAlign = TextAlign.End,
        )
    }

}