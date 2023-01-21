package com.ozcan.alasalvar.weather

import android.Manifest
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.ozcan.alasalvar.designsystem.theme.ui.WeatherTheme
import com.ozcan.alasalvar.detail.DetailScreen
import com.ozcan.alasalvar.home.HomeScreen
import com.ozcan.alasalvar.search.SearchScreen
import dagger.hilt.android.AndroidEntryPoint
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ozcan.alasalvar.weather.navigation.AppNavHost

@OptIn(ExperimentalPermissionsApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        WindowCompat.setDecorFitsSystemWindows(window, true)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            //viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )


        setContent {
            WeatherTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavHost()
                }

//                val multiplePermissionsState = rememberMultiplePermissionsState(
//                    listOf(
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.ACCESS_COARSE_LOCATION,
//                    )
//                )
//
//                if (!multiplePermissionsState.allPermissionsGranted)
//                    multiplePermissionsState.launchMultiplePermissionRequest()

              //  HomeScreen(onSearchClick = {}, onWeatherClick = {})
//                DetailScreen(
//                    onBackClick = {
//
//                    },
//                    onAddFavoriteClick = {
//
//                    }
//                )

//                SearchScreen(
//                    onCancelClick = {
//
//                    },
//                    onCityClicked = {
//
//                    })
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    WeatherTheme {
//        Greeting("Android")
//    }
//}