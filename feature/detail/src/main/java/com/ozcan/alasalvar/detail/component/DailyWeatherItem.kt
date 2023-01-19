package com.ozcan.alasalvar.detail.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.ozcan.alasalvar.model.data.Daily


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DailyWeatherItem(daily: Daily, modifier: Modifier = Modifier) {

    Spacer(modifier = Modifier.height(5.dp))
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 40.dp, end = 40.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = daily.date,
            fontSize = 13.sp,
            color = MaterialTheme.colors.secondaryVariant,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(2f)
        )

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(2f)) {
            GlideImage(
                model = daily.icon,
                contentScale = ContentScale.FillBounds,
                contentDescription = "weather image",
                modifier = Modifier.size(50.dp),
            )


            Text(
                text = daily.weatherStatus,
                fontSize = 13.sp,
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.Normal,
            )

        }


        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {

            Text(
                text = daily.temperatureMax,
                fontSize = 15.sp,
                color = MaterialTheme.colors.secondary,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(end = 5.dp),
            )
            Text(
                text = daily.temperatureMin,
                fontSize = 14.sp,
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.Medium,
            )

        }

    }

}