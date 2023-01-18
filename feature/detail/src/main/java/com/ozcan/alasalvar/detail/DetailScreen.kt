package com.ozcan.alasalvar.detail

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.WeatherDetail
import weather.feature.detail.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState

    val scrollState = rememberLazyListState()
    if (uiState.data==null){
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

            item {
                Text(
                    text = "Days",
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.secondary,
                    fontWeight = FontWeight.Bold
                )
            }

            for (i in 0..100)
                item {
                    WeatherItem()

                }

        }
    }


}


@SuppressLint("FrequentlyChangedStateReadInComposition")
@OptIn(ExperimentalMotionApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun HeaderContent(weatherDetail: WeatherDetail,modifier: Modifier = Modifier, lazyScrollState: LazyListState) {

    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }


    val progress by animateFloatAsState(
        targetValue = if (lazyScrollState.firstVisibleItemIndex == 0) 0f else 1f,//if (lazyScrollState.firstVisibleItemIndex in 0..4) 0f else 1f
        tween(450)
    )
    val motionHeight by animateDpAsState(
        targetValue = if (lazyScrollState.firstVisibleItemIndex == 0) 450.dp else 300.dp,// if (lazyScrollState.firstVisibleItemIndex in 0..4) 300.dp else 56.dp
        tween(450)
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
                onBackClick = {},
                cityName = weatherDetail.city.name
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


            Info(
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
    cityName: String,
    onBackClick: () -> Unit,
) {
    ConstraintLayout(modifier = modifier) {
        val (backIcon, cityText, moreIcon) = createRefs()

        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            tint = Color.White,
            contentDescription = "back Icon",
            modifier = Modifier
                .constrainAs(backIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .clickable {
                    onBackClick()
                }
        )


        Row(
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .constrainAs(cityText) {
                    start.linkTo(backIcon.end)
                    end.linkTo(moreIcon.start)
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
                text = cityName,
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(start = 3.dp)
            )
        }



        Icon(
            imageVector = Icons.Rounded.Info,
            tint = Color.White,
            contentDescription = "back Icon",
            modifier = Modifier
                .constrainAs(moreIcon) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )


    }
}

@Composable
internal fun Info(modifier: Modifier = Modifier) {

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
            InfoItem(icon = Icons.Rounded.Info, name = "Wind", value = "13 km/h")
            InfoItem(icon = Icons.Rounded.Info, name = "Humidity", value = "24%")
            InfoItem(icon = Icons.Rounded.Info, name = "Pressure", value = "13 Pa")
        }


        Spacer(modifier = Modifier.height(30.dp))
    }

}


@Composable
fun InfoItem(icon: ImageVector, name: String, value: String) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = "info icon",
            Modifier.size(20.dp),
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = value, fontSize = 15.sp, color = Color.White, fontWeight = FontWeight.Normal)

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = name,
            fontSize = 13.sp,
            color = Color.White,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.alpha(0.5f)
        )


    }

}


@Composable
fun WeatherItem(modifier: Modifier = Modifier) {

    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 5.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Monday, 16",
            fontSize = 13.sp,
            color = MaterialTheme.colors.secondaryVariant,
            fontWeight = FontWeight.Normal
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = "info icon",
                Modifier.size(20.dp),
            )
            Text(
                text = "Storm",
                fontSize = 13.sp,
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.Normal
            )

        }


        Row {

            Text(
                text = "+25°",
                fontSize = 13.sp,
                color = MaterialTheme.colors.secondary,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = "+19°",
                fontSize = 13.sp,
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.Normal
            )

        }

    }

}