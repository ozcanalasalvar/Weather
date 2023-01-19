package com.ozcan.alasalvar.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.ozcan.alasalvar.model.data.Hourly


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HourlyWeatherItem(
    modifier: Modifier = Modifier,
    hourly: Hourly
) {
    Column(
        modifier = modifier.padding(all = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = hourly.temperature,
            fontSize = 15.sp,
            color = MaterialTheme.colors.secondary,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(end = 5.dp),
        )

        GlideImage(
            model = hourly.icon,
            contentScale = ContentScale.FillBounds,
            contentDescription = "weather image",
            modifier = Modifier.size(40.dp),
        )


        Text(
            text = hourly.hour,
            fontSize = 11.sp,
            color = MaterialTheme.colors.secondary,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.alpha(0.5f)
        )


    }
}

