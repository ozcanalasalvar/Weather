package com.ozcan.alasalvar.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import weather.feature.home.R

@Composable
fun HomeToolbar(
    onSearchClick: () -> Unit, modifier: Modifier = Modifier
) {

    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(
                text = stringResource(id = R.string.hello),
                fontSize = 25.sp,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                maxLines = 1,
            )
            Text(
                text = stringResource(id = R.string.discover_the_weather),
                fontSize = 15.sp,
                color = MaterialTheme.colors.onBackground,
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
                    BorderStroke(1.dp, MaterialTheme.colors.onBackground), CircleShape
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