package com.ozcan.alasalvar.detail.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ozcan.alasalvar.detail.DetailScreen
import com.ozcan.alasalvar.model.data.City

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
        DetailScreen(
            cityId = cityId,
            onBackClick = onBackClick,
        )
    }
}