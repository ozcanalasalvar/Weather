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
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.ozcan.alasalvar.detail.component.Loading
import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.WeatherDetail
import weather.feature.detail.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    cityId: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onAddFavoriteClick: () -> Unit,
) {

    LaunchedEffect(Unit) {
        viewModel.init(cityId)
    }
    val uiState = viewModel.uiState

    val scrollState = rememberLazyListState()
    if (uiState.isLoading) {
        Loading()
    }

    if (uiState.data != null) {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            HeaderContent(weatherDetail = uiState.data,
                lazyScrollState = scrollState,
                onBackClick = onBackClick,
                onFavoriteClick = {
                    viewModel.onFavoriteClick(city = uiState.data.city)
                    onAddFavoriteClick()
                })
        }) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background),
                state = scrollState
            ) {

                item {
                    Box(modifier = Modifier.height(20.dp))
                }
                item {
                    Box(modifier = Modifier.height(20.dp))
                }

                itemsIndexed(uiState.data.dailyWeather) { _, daily ->
                    DailyWeatherItem(daily)
                }

                if (uiState.data.dailyWeather.size < 10) {
                    item { Box(modifier = Modifier.height((((10 - uiState.data.dailyWeather.size)) * 40).dp)) }
                }

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
    lazyScrollState: LazyListState,
    onBackClick: () -> Unit,
    onFavoriteClick: (city: City) -> Unit,
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
        targetValue = if (lazyScrollState.firstVisibleItemIndex == 0) 590.dp else 280.dp,// if (lazyScrollState.firstVisibleItemIndex in 0..4) 300.dp else 56.dp
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
                onBackClick = onBackClick,
                onFavoriteClick = onFavoriteClick
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
                weatherDetail = weatherDetail, modifier = Modifier
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
    onFavoriteClick: (city: City) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp)
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    onBackClick()
                }, contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                tint = Color.White,
                contentDescription = "back Icon",
            )
        }


        Box(modifier = Modifier.weight(5f), contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier.width(IntrinsicSize.Max),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    imageVector = Icons.Rounded.LocationOn,
                    tint = Color.White,
                    contentDescription = "location Icon",
                )

                Text(
                    text = city.name,
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 3.dp),
                    maxLines = 1,
                )
            }
        }


        Box(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    if (!city.isCurrentLocation) onFavoriteClick(city)
                }, contentAlignment = Alignment.CenterEnd
        ) {
            if (!city.isCurrentLocation) {
                Image(
                    painter = painterResource(id = if (city.isFavorite) R.drawable.favorite else R.drawable.add_favorite),
                    contentDescription = "back Icon",
                )
            }
        }


    }
}