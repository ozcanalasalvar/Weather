package com.ozcan.alasalvar.detail

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
import androidx.compose.ui.platform.testTag
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
import com.ozcan.alasalvar.designsystem.theme.component.bounceClick
import com.ozcan.alasalvar.designsystem.theme.ui.*
import com.ozcan.alasalvar.detail.component.DailyWeatherItem
import com.ozcan.alasalvar.detail.component.HourlyWeatherItem
import com.ozcan.alasalvar.detail.component.Info
import com.ozcan.alasalvar.designsystem.theme.component.Loading
import com.ozcan.alasalvar.designsystem.theme.component.WarningDialog
import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.WeatherDetail
import weather.feature.detail.R


@Composable
fun DetailRoute(
    cityId: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.init(cityId)
    }
    val uiState = viewModel.uiState
    DetailScreen(uiState = uiState, onBackClick = onBackClick, onFavoriteClick = {
        viewModel.onFavoriteClick(city = uiState.data?.city)
    })
}


@Composable
fun DetailScreen(
    uiState: DetailUiState = DetailUiState(),
    onBackClick: () -> Unit = {},
    onFavoriteClick: (City?) -> Unit = {},
) {

    val scrollState = rememberLazyListState()
    val firstVisibleIndex = remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }

    if (uiState.isLoading) {
        Loading(modifier = Modifier.testTag("loading"))
    }

    val errorState = remember {
        mutableStateOf( false)
    }

    if (uiState.error !=null) {
        errorState.value = true
        WarningDialog(content = uiState.error, state = errorState, onCloseClick = {
            onBackClick()
        })
    }

    if (uiState.data != null) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                HeaderContent(
                    weatherDetail = uiState.data,
                    firstVisibleIndex = firstVisibleIndex.value,
                    onBackClick = onBackClick,
                    onFavoriteClick = {
                        onFavoriteClick(uiState.data.city)
                    },
                )
            },
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)
                    .background(MaterialTheme.colors.background),
                state = scrollState,
                contentPadding = PaddingValues(top = 40.dp)
            ) {


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


@OptIn(ExperimentalMotionApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun HeaderContent(
    weatherDetail: WeatherDetail,
    modifier: Modifier = Modifier,
    firstVisibleIndex: Int,
    onBackClick: () -> Unit,
    onFavoriteClick: (city: City) -> Unit,
) {

    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }


    val progress by animateFloatAsState(
        targetValue = if (firstVisibleIndex == 0) 0f else 1f, tween(500), label = ""
    )
    val motionHeight by animateDpAsState(
        targetValue = if (firstVisibleIndex == 0) 590.dp else 280.dp, tween(500), label = ""
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
            .padding(start = 10.dp, end = 10.dp)
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .bounceClick {
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
                .bounceClick {
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