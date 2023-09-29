package com.ozcan.alasalvar.home

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ozcan.alasalvar.designsystem.theme.component.Loading
import com.ozcan.alasalvar.designsystem.theme.ui.*
import com.ozcan.alasalvar.home.component.HomeToolbar
import com.ozcan.alasalvar.home.component.WeatherListItem
import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.Weather
import weather.feature.home.R

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(
    onSearchClick: () -> Unit,
    onWeatherClick: (Weather) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
    )


    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            viewModel.startLocationTrack()
        }
    }

    LaunchedEffect(Unit) {
        if (permissions.all {
                ContextCompat.checkSelfPermission(
                    context, it
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            // Get the location
            viewModel.startLocationTrack()
        } else {
            launcherMultiplePermissions.launch(permissions)
        }
    }


    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val locationUiState by viewModel.locationUiState.collectAsStateWithLifecycle()


    if (uiState.isLoading) {
        Loading()
    }

    HomeContent(
        uiState = uiState,
        locationUiState = locationUiState,
        onSearchClick = onSearchClick,
        onWeatherClick = onWeatherClick,
    )
}

@Composable
fun HomeContent(
    uiState: HomeUiState,
    locationUiState: CurrentLocationUiState,
    onSearchClick: () -> Unit,
    onWeatherClick: (Weather) -> Unit,
) {
    val scrollState = rememberLazyListState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = 20.dp, start = 20.dp, end = 20.dp),
        topBar = {
            HomeToolbar(onSearchClick = onSearchClick)
        },
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            state = scrollState,
        ) {

            locationUiState.current?.let { weather ->
                item {
                    WeatherListItem(weather = weather, isCurrent = true, onClick = {
                        onWeatherClick(weather)
                    })
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }


            if (!uiState.favorites.isNullOrEmpty()) {

                item {
                    Text(
                        text = stringResource(id = R.string.favorites),
                        fontSize = 18.sp,
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        maxLines = 1,
                    )
                }

                itemsIndexed(uiState.favorites) { _, weather ->
                    WeatherListItem(weather = weather, onClick = {
                        onWeatherClick(weather)
                    })
                }
            }


        }

    }
}

@Preview
@Composable
fun HomeContentPreview() {
    HomeContent(uiState = HomeUiState(
        favorites = listOf(
            Weather(
                city = City(
                    id = 1,
                    country = "Turkey",
                    lat = 1234.0,
                    lon = 1234.0,
                    name = "Istanbul",
                    isFavorite = true,
                    isCurrentLocation = false,
                ),
                weatherIcon = null,
                weatherStatus = "cloudy",
                currentTemperature = "28C",
                todayDate = "12/12/222",
            ), Weather(
                city = City(
                    id = 1,
                    country = "Turkey",
                    lat = 1234.0,
                    lon = 1234.0,
                    name = "Istanbul",
                    isFavorite = true,
                    isCurrentLocation = false,
                ),
                weatherIcon = null,
                weatherStatus = "cloudy",
                currentTemperature = "28C",
                todayDate = "12/12/222",
            )
        )
    ), locationUiState = CurrentLocationUiState(
        Weather(
            city = City(
                id = 1,
                country = "Turkey",
                lat = 1234.0,
                lon = 1234.0,
                name = "Istanbul",
                isFavorite = true,
                isCurrentLocation = false,
            ),
            weatherIcon = null,
            weatherStatus = "cloudy",
            currentTemperature = "28C",
            todayDate = "12/12/222",
        )
    ), onSearchClick = { }, onWeatherClick = {})
}
