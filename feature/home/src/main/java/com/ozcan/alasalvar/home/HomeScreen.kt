package com.ozcan.alasalvar.home

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.ozcan.alasalvar.designsystem.theme.component.bounceClick
import com.ozcan.alasalvar.designsystem.theme.ui.*
import com.ozcan.alasalvar.model.data.Weather

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(
    onSearchClick: () -> Unit,
    onWeatherClick: (Weather) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val scrollState = rememberLazyListState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = 20.dp, start = 20.dp, end = 20.dp),
        topBar = {
            Header(onSearchClick = onSearchClick)
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            state = scrollState,
        ) {

            uiState.current?.let { weather ->
                item {
                    WeatherListItem(weather = weather, isCurrent = true, onClick = {
                        onWeatherClick(weather)
                    })
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }


            uiState.favorites?.let { weathers ->

                item {
                    Text(
                        text = "Favorites",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxLines = 1,
                    )
                }

                itemsIndexed(weathers) { _, weather ->
                    WeatherListItem(weather = weather, onClick = {
                        onWeatherClick(weather)
                    })
                }
            }


        }

    }

}

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
                            BlueLight,
                            Blue30,
                            Blue20,
                            Blue10
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
                text = if (isCurrent) "Current Location" else weather.city.name,
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .constrainAs(cityName) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    },
                maxLines = 1,
            )



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


@Composable
fun Header(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(
                text = "Hello",
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                maxLines = 1,
            )
            Text(
                text = "Discover the weather",
                fontSize = 15.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                modifier = Modifier,
                maxLines = 1,
            )
        }

        Box(
            modifier = Modifier
                .clip(
                    CircleShape
                )
                .border(
                    BorderStroke(1.dp, MaterialTheme.colors.onBackground),
                    CircleShape
                )
                .background(MaterialTheme.colors.surface)
                .align(Alignment.CenterVertically)
                .clickable { onSearchClick() },
        ) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "main search",
                modifier = Modifier
                    .padding(all = 5.dp)
                    .size(20.dp),
                tint = MaterialTheme.colors.onBackground
            )
        }


    }

}
