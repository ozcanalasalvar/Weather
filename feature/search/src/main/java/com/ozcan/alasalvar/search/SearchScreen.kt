package com.ozcan.alasalvar.search

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ozcan.alasalvar.designsystem.theme.component.bounceClick
import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.search.component.SearchField
import weather.feature.search.R

@Composable
fun SearchScreen(
    onCancelClick: () -> Unit,
    onCityClicked: (City) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val uiState: SearchUiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchContent(uiState, onCancelClick, onCityClicked) {
        viewModel.onSearch(it)
    }
}


@Composable
fun SearchContent(
    uiState: SearchUiState,
    onCancelClick: () -> Unit,
    onCityClicked: (City) -> Unit,
    onTextChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = 30.dp, start = 20.dp, end = 20.dp)
    ) {

        SearchField(
            onTextChanged = onTextChanged,
            onCancelClick = onCancelClick,
            hint = stringResource(id = R.string.search_city)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp)
        ) {
            itemsIndexed(uiState.results) { _, city ->
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = city.name + " ," + city.country,
                    color = MaterialTheme.colors.secondary,
                    fontSize = 15.sp,
                    modifier = Modifier.bounceClick { onCityClicked(city) })
                Spacer(modifier = Modifier.height(10.dp))

            }

        }
    }
}


@Preview
@Composable
fun SearchContentPreview() {
    SearchContent(
        uiState = SearchUiState(),
        onCancelClick = {},
        onCityClicked = {},
        onTextChanged = {},
    )
}