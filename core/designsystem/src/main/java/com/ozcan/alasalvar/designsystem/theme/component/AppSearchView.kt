package com.ozcan.alasalvar.designsystem.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppSearchView(
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit,
    label: String
) {
    var query: String by rememberSaveable { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Rounded.Search,
            tint = MaterialTheme.colors.secondaryVariant,
            contentDescription = "Search Icon"
        )

        BasicTextField(
            value = query,
            onValueChange = { onQueryChanged ->
                query = onQueryChanged
                onTextChanged(onQueryChanged)
            },
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.secondary
            ),
            decorationBox = { innerTextField ->

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 5.dp, start = 10.dp),
                ) {

                    if (query.isNotEmpty())
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            tint = MaterialTheme.colors.secondaryVariant,
                            contentDescription = "Search Icon",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .clickable {
                                    query = ""
                                    onTextChanged("")
                                }
                        )

                    if (query.isEmpty()) {
                        Text(
                            text = label,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.LightGray
                        )
                    }
                    innerTextField()
                }
            },
            cursorBrush = Brush.verticalGradient(
                0.00f to Color.Transparent,
                0.20f to Color.Transparent,
                0.20f to MaterialTheme.colors.secondary,
                0.90f to MaterialTheme.colors.secondary,
                0.90f to Color.Transparent,
                1.00f to Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            modifier = Modifier
                .defaultMinSize(minWidth = 100.dp),
        )


    }
}