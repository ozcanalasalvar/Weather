package com.ozcan.alasalvar.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ozcan.alasalvar.model.data.WeatherDetail
import weather.feature.detail.R


@Composable
internal fun Info(
    weatherDetail: WeatherDetail,
    modifier: Modifier = Modifier,
) {

    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(5.dp))

        Divider(
            Modifier
                .alpha(0.3f)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White,
                            Color.White,
                            Color.White,
                            Color.White,
                            Color.Transparent,
                        )
                    )
                )
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoItem(
                drawable = R.drawable.wind,
                name = "Wind",
                value = weatherDetail.wind,
                colorFilter = ColorFilter.tint(Color.White)
            )
            InfoItem(
                drawable = R.drawable.humidity,
                name = "Humidity",
                value = weatherDetail.humidity
            )
            InfoItem(
                drawable = R.drawable.air_pressure,
                name = "Air Pressure",
                value = weatherDetail.pressure,
                colorFilter = ColorFilter.tint(Color.White)
            )
        }


        Spacer(modifier = Modifier.height(30.dp))
    }

}


@Composable
internal fun InfoItem(drawable: Int, name: String, value: String, colorFilter: ColorFilter? = null) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(drawable),
            contentDescription = "info icon",
            Modifier
                .size(20.dp)
                .alpha(0.8f),
            colorFilter = colorFilter
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = value, fontSize = 13.sp, color = Color.White, fontWeight = FontWeight.Normal)

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = name,
            fontSize = 11.sp,
            color = Color.White,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.alpha(0.5f)
        )
    }

}