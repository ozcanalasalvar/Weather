package com.ozcan.alasalvar.home

import androidx.activity.ComponentActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.ozcanalasalvar.testing.data.listOfWeatherTestData
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setup() {
        composeTestRule.activity.apply {
        }
    }


    @Test
    fun homeScreen_init_ui_show_loading() {
        composeTestRule.setContent {
            MaterialTheme {
                HomeScreen()
            }
        }

        composeTestRule
            .onNodeWithTag("loading")
            .assertExists()
    }

    @Test
    fun homeScreen_fetch_favorites_show_on_screen() {
        composeTestRule.setContent {
            MaterialTheme {
                HomeScreen(
                    uiState = HomeUiState(
                        isLoading = false,
                        favorites = listOfWeatherTestData,
                        error = null
                    ),
                )
            }
        }

        composeTestRule
            .onNodeWithText(listOfWeatherTestData[0].city.name)
            .assertIsDisplayed()


        composeTestRule
            .onNodeWithTag("favoriteTitle")
            .assertExists()

    }

    @Test
    fun homeScreen_fetch_current_location_show_on_screen(){
        composeTestRule.setContent {
            MaterialTheme {
                HomeScreen(
                   locationUiState = CurrentLocationUiState(listOfWeatherTestData[0]),
                )
            }
        }

        composeTestRule
            .onNodeWithText(listOfWeatherTestData[0].currentTemperature)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Current Location")
            .assertIsDisplayed()

    }
}