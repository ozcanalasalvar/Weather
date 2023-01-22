package com.ozcan.alasalvar.home.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ozcan.alasalvar.home.HomeScreen
import com.ozcan.alasalvar.model.data.Weather


const val homeNavigationRoute = "home_route"

fun NavController.navigateToHome(builder: NavOptionsBuilder.() -> Unit) {
    this.navigate(homeNavigationRoute, navOptions(builder))
}

fun NavGraphBuilder.homeScreen(
    onSearchClick: () -> Unit,
    onWeatherClick: (Weather) -> Unit,
) {
    composable(
        route = homeNavigationRoute,
    ) {
        HomeScreen(
            onSearchClick = onSearchClick,
            onWeatherClick = onWeatherClick
        )
    }
}