package com.ozcan.alasalvar.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.Daily
import com.ozcan.alasalvar.model.data.Hourly
import com.ozcan.alasalvar.model.data.WeatherDetail
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()


    @Before
    fun setup() {
        composeTestRule.activity.apply {

        }
    }

    @Test
    fun detailScreen_initial_state_loading_visible() = runTest {
        composeTestRule.setContent {
            DetailScreen()
        }

        composeTestRule.onNodeWithTag("loading").assertExists()
    }


    @Test
    fun detailScreen_data_fetched_successfully() = runTest {
        composeTestRule.setContent {
            DetailScreen(
                uiState = DetailUiState(
                    isLoading = false,
                    data = detail,
                    error = null,
                )
            )
        }

        composeTestRule
            .onNodeWithText(detail.city!!.name)
            .assertIsDisplayed()
    }



    val detail = WeatherDetail(
        city =  City(
            id = 1,
            country = "test1",
            lat = 12.0,
            lon = 12.0,
            name = "City1",
            isFavorite = true,
            isCurrentLocation = true,
        ),
        weatherIcon = "",
        weatherStatus = "Cloudy",
        currentTemperature = "28°C",
        todayDate = "12/12/12",
        wind = "123",
        humidity = "123",
        pressure = "123",
        hourlyWeather = listOf(
            Hourly(
                temperature = "28°C",
                icon = "",
                hour = "12:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "13:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "14:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "15:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "16:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "17:00",
            )
        ),
        dailyWeather = listOf(
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "12/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "13/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "12/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "14/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "15/12/2022",
            ),
        ),
    )

}