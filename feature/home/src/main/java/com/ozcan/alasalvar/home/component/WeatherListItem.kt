package com.ozcan.alasalvar.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.ozcan.alasalvar.designsystem.theme.component.bounceClick
import com.ozcan.alasalvar.designsystem.theme.ui.Blue10
import com.ozcan.alasalvar.designsystem.theme.ui.Blue20
import com.ozcan.alasalvar.designsystem.theme.ui.Blue30
import com.ozcan.alasalvar.designsystem.theme.ui.BlueLight
import com.ozcan.alasalvar.model.data.Weather
import weather.feature.home.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherListItem(
    modifier: Modifier = Modifier,
    weather: Weather,
    isCurrent: Boolean = false,
    onClick: (Weather) -> Unit
) {

    Card(
        shape = RoundedCornerShape(25.dp),
        elevation = 5.dp,
        modifier = modifier
            .height(110.dp)
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 20.dp)
            .bounceClick {
                onClick(weather)
            },
        backgroundColor = Color.Transparent,
    ) {

        ConstraintLayout(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            BlueLight, Blue30, Blue20, Blue10
                        )
                    )
                )
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                .fillMaxWidth(),
        ) {

            val (country, cityName, icon, temp, tempInfo) = createRefs()


            Text(
                text = weather.todayDate,
                fontSize = 13.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .constrainAs(country) {
                        start.linkTo(parent.start)
                        top.linkTo(cityName.bottom)
                    }
                    .alpha(0.7f),
                maxLines = 1,
            )

            Text(
                text = if (isCurrent) stringResource(id = R.string.current_location) else weather.city.name,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.constrainAs(cityName) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
                maxLines = 1,
            )



            weather.weatherIcon?.let {
                GlideImage(
                    model = weather.weatherIcon,
                    contentScale = ContentScale.Crop,
                    contentDescription = "weather image",
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Transparent)
                        .constrainAs(icon) {
                            end.linkTo(parent.end, -20.dp)
                            top.linkTo(parent.top, -10.dp)
                        },
                )
            }



            Text(
                text = weather.currentTemperature,
                fontSize = 15.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .constrainAs(temp) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(icon.bottom)
                    }
                    .alpha(0.7f),
                maxLines = 1,
            )


            Text(
                text = weather.weatherStatus,
                fontSize = 15.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .constrainAs(tempInfo) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(icon.bottom)
                    }
                    .alpha(0.7f),
                maxLines = 1,
            )

        }

    }


}