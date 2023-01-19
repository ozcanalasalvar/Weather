package com.ozcan.alasalvar.detail

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.ozcan.alasalvar.designsystem.theme.ui.*
import com.ozcan.alasalvar.detail.component.DailyWeatherItem
import com.ozcan.alasalvar.detail.component.HourlyWeatherItem
import com.ozcan.alasalvar.detail.component.Info
import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.Hourly
import com.ozcan.alasalvar.model.data.WeatherDetail
import weather.feature.detail.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState

    val scrollState = rememberLazyListState()
    if (uiState.data == null) {
        Text(text = "boşş")
        return
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            HeaderContent(weatherDetail = uiState.data, lazyScrollState = scrollState)
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background),
            state = scrollState
        ) {

            itemsIndexed(uiState.data.dailyWeather) { _, daily ->
                DailyWeatherItem(daily)
            }

            itemsIndexed(uiState.data.dailyWeather) { _, daily ->
                DailyWeatherItem(daily)
            }

        }
    }


}


@SuppressLint("FrequentlyChangedStateReadInComposition")
@OptIn(ExperimentalMotionApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun HeaderContent(
    weatherDetail: WeatherDetail,
    modifier: Modifier = Modifier,
    lazyScrollState: LazyListState
) {

    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }


    val progress by animateFloatAsState(
        targetValue = if (lazyScrollState.firstVisibleItemIndex == 0) 0f else 1f,//if (lazyScrollState.firstVisibleItemIndex in 0..4) 0f else 1f
        tween(500)
    )
    val motionHeight by animateDpAsState(
        targetValue = if (lazyScrollState.firstVisibleItemIndex == 0) 570.dp else 300.dp,// if (lazyScrollState.firstVisibleItemIndex in 0..4) 300.dp else 56.dp
        tween(500)
    )


    Card(
        shape = RoundedCornerShape(30.dp),
        elevation = 10.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp),
        backgroundColor = BlueLight,
    ) {
        MotionLayout(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Blue40,
                            BlueLight,
                            BlueLight,
                            Blue30,
                            Blue20,
                        )
                    )
                )
                .padding(start = 10.dp, end = 10.dp, top = 20.dp)
                .fillMaxWidth()
                .height(motionHeight),
            motionScene = MotionScene(content = motionScene),
            progress = progress,
        ) {

            Header(
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("header"),
                city = weatherDetail.city!!,
                onBackClick = {},
                onAddFavoriteClick = {}
            )

            GlideImage(
                model = weatherDetail.weatherIcon,
                contentScale = ContentScale.FillBounds,
                contentDescription = "weather image",
                modifier = Modifier
                    .size(250.dp)
                    .background(Color.Transparent)
                    .layoutId("weatherIcon"),
            )



            Text(
                text = weatherDetail.currentTemperature,
                fontSize = motionFontSize("weatherTemp", "fontSize"),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.layoutId("weatherTemp")
            )

            Text(
                text = weatherDetail.weatherStatus,
                fontSize = motionFontSize("weatherName", "fontSize"),
                color = Color.White,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.layoutId("weatherName")
            )


            Text(
                text = weatherDetail.todayDate,
                fontSize = 13.sp,
                color = Color.White,
                modifier = Modifier
                    .layoutId("dateText")
                    .alpha(0.5f)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("hourlyList")
            ) {
                itemsIndexed(weatherDetail.hourlyWeather) { _, hourly ->
                    HourlyWeatherItem(hourly = hourly, modifier = Modifier.padding(top = 30.dp))

                }
            }


            Info(
                weatherDetail = weatherDetail,
                modifier = Modifier
                    .layoutId("info")
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
internal fun Header(
    modifier: Modifier = Modifier,
    city: City,
    onBackClick: () -> Unit,
    onAddFavoriteClick: (city: City) -> Unit,
) {
    ConstraintLayout(modifier = modifier) {
        val (startIcon, cityText, endIcon) = createRefs()

        if (city.isFavorite) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                tint = Color.White,
                contentDescription = "back Icon",
                modifier = Modifier
                    .constrainAs(startIcon) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable {
                        onBackClick()
                    }
            )
        } else {
            Text(
                text = "Cancel",
                fontSize = 15.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .constrainAs(startIcon) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable {
                        onBackClick()
                    },
                maxLines = 1,
            )
        }



        Row(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .constrainAs(cityText) {
                    start.linkTo(startIcon.end)
                    end.linkTo(endIcon.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Rounded.LocationOn,
                tint = Color.White,
                contentDescription = "location Icon",
            )

            Text(
                text = city.name,
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 3.dp),
                maxLines = 1,
            )
        }



        if (city.isFavorite) {
            Icon(
                imageVector = Icons.Rounded.Info,
                tint = Color.White,
                contentDescription = "back Icon",
                modifier = Modifier
                    .constrainAs(endIcon) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
        } else {
            Text(
                text = "Add",
                fontSize = 15.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .constrainAs(endIcon) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable {
                        onAddFavoriteClick(city)
                    },
                maxLines = 1,
            )
        }

    }
}