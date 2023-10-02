package com.ozcan.alasalvar.detail.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ozcan.alasalvar.detail.DetailRoute

const val detailNavigationRoute = "detail_route"
internal const val cityIdArg = "cityId"

fun NavController.navigateToDetail(cityId: Int) {
    this.navigate("$detailNavigationRoute/$cityId")
}

fun NavGraphBuilder.detailScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = "$detailNavigationRoute/{$cityIdArg}",
        arguments = listOf(
            navArgument(cityIdArg) { type = NavType.IntType }
        )
    ) { entry ->
        val cityId = entry.arguments?.getInt(cityIdArg) ?: 0
        DetailRoute(
            cityId = cityId,
            onBackClick = onBackClick,
        )
    }
}