package com.ozcan.alasalvar.designsystem.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import weather.core.designsystem.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WarningDialog(
    header: String = stringResource(id = R.string.warning),
    content: String,
    isCancelAble: Boolean = false,
    state: MutableState<Boolean> = mutableStateOf(false),
    onCloseClick: () -> Unit
) {

    Dialog(
        onDismissRequest = {
            state.value = false
            onCloseClick()
        }, properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier.background(Color.Transparent)
        ) {

            //MaterialTheme.colors.secondaryVariant

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(IntrinsicSize.Max)
                    .align(Alignment.Center)
                    .background(
                        MaterialTheme.colors.primary
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                        .padding(20.dp)
                ) {
                    Text(
                        text = header,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.secondary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = content,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.secondaryVariant
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd
                    ) {

                        Text(text = stringResource(id = R.string.okey),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.secondary,
                            modifier = Modifier.clickable {
                                state.value = false
                                onCloseClick()
                            })

                    }
                }

            }
        }

    }

}

@Preview
@Composable
fun WarningDialogPreView() {
    WarningDialog(
        content = "test tes",
        isCancelAble = false,
        onCloseClick = {

        },
    )
}