package com.ozcan.alasalvar.search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ozcan.alasalvar.designsystem.theme.component.AppSearchView
import weather.feature.search.R

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit,
    onCancelClick: () -> Unit,
    hint: String
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        AppSearchView(
            onTextChanged = {
                onTextChanged(it)
            }, label = hint, modifier = Modifier.fillMaxWidth(0.8f)
        )

        Text(
            text = stringResource(id = R.string.cancel),
            fontSize = 15.sp,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth()
                .clickable { onCancelClick() },
            textAlign = TextAlign.End,
        )
    }

}