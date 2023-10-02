package com.ozcan.alasalvar.search

import androidx.activity.ComponentActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.ozcan.alasalvar.model.data.City
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import weather.feature.search.R


class SearchScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var noResult:String
    @Before
    fun setup() {
        composeTestRule.activity.apply {
             noResult= getString(R.string.no_result)
        }
    }

    @Test
    fun search_no_result_is_visible() {
        composeTestRule.setContent {
            MaterialTheme {
                SearchScreen(uiState = SearchUiState(results = emptyList(), noResult = true))
            }
        }

        composeTestRule
            .onNodeWithText(noResult)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("NoResultText")
            .assertExists()
    }

    @Test
    fun search_result_is_visible() {
        composeTestRule.setContent {
            MaterialTheme {
                SearchScreen(uiState = SearchUiState(results = cities, noResult = false))
            }
        }

        composeTestRule
            .onNodeWithTag("${cities[0].id}")
            .assertExists()
    }


    private val cities: List<City> = mutableListOf(
        City(
            id = 1,
            country = "test1",
            lat = 12453.0,
            lon = 12453.0,
            name = "City1",
            isFavorite = true,
            isCurrentLocation = true,
        ),
        City(
            id = 2,
            country = "test2",
            lat = 12453.0,
            lon = 12453.0,
            name = "City2",
            isFavorite = false,
            isCurrentLocation = false,
        ),
        City(
            id = 3,
            country = "test3",
            lat = 12453.0,
            lon = 12453.0,
            name = "City3",
            isFavorite = false,
            isCurrentLocation = false,
        ),
    )
}