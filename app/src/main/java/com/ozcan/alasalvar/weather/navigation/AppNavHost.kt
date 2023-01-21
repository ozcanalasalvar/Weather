package com.ozcan.alasalvar.weather.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ozcan.alasalvar.detail.navigation.detailScreen
import com.ozcan.alasalvar.detail.navigation.navigateToDetail
import com.ozcan.alasalvar.home.navigation.homeNavigationRoute
import com.ozcan.alasalvar.home.navigation.homeScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationRoute
) {
//    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        homeScreen(
            onSearchClick = {

            },
            onWeatherClick = { weather ->
//                navController.navigate(Screen.Details.withArgs(weather.city.id))
                navController.navigateToDetail(cityId = weather.city.id)
            }
        )

        detailScreen(onBackClick = {
            navController.popBackStack()
        }, onAddFavoriteClick = {})

    }

}