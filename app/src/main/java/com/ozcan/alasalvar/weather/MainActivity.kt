package com.ozcan.alasalvar.weather

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.ozcan.alasalvar.designsystem.theme.ui.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint
import com.ozcan.alasalvar.weather.navigation.AppNavHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            WeatherTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavHost()
                }

            }
        }
    }
}