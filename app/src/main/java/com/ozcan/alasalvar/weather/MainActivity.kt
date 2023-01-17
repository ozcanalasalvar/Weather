package com.ozcan.alasalvar.weather

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.ozcan.alasalvar.designsystem.theme.ui.WeatherTheme
import com.ozcan.alasalvar.detail.DetailScreen
import com.ozcan.alasalvar.search.SearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            WeatherTheme {
                DetailScreen()

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