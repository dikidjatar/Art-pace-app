package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace() {
    var step by remember { mutableStateOf(1) }
    var showAlert by remember { mutableStateOf(false) }
    var changePos: Float

    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            title = {
                Text(text = "Alert")
            },
            text = {
                Text(text = "Item sudah terakhir!")
            },
            confirmButton = {
                Button(onClick = { showAlert = false }) {
                    Text(text = "Ok")
                }
            }
        )
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 20.dp)
            .safeDrawingPadding()
    ) {
        when (step) {
            1 -> {
                ImageBox(
                    imageResource = R.drawable.drink,
                    modifier = Modifier.padding(top = 20.dp),
                    ondrag = {change, _ ->
                        changePos = change.positionChange().x
                        if (changePos < 0) {
                            step = 2
                            changePos = 0f
                        } else if (changePos > 0) {
                            step = 1
                            showAlert = true
                            changePos = 0f
                        }
                    }
                )
                TextBox(
                    title = R.string.atwork_title_drink,
                    artist = R.string.atwork_artist_drink
                )
            }
            2 -> {
                ImageBox(
                    imageResource = R.drawable.moon,
                    modifier = Modifier.padding(top = 20.dp),
                    ondrag = {change, _ ->
                        changePos = change.positionChange().x
                        if (changePos < 0) {
                            step = 3
                            changePos = 0f
                        } else if (changePos > 0) {
                            step = 1
                            changePos = 0f
                        }
                    }
                )
                TextBox(
                    title = R.string.atwork_title_moon,
                    artist = R.string.atwork_artist_moon
                )
            }
            3 -> {
                ImageBox(
                    imageResource = R.drawable.brid,
                    modifier = Modifier.padding(top = 20.dp),
                    ondrag = {change, _ ->
                        changePos = change.positionChange().x
                        if (changePos < 0) {
                            step = 4
                            changePos = 0f
                        } else if (changePos > 0) {
                            step = 2
                            changePos = 0f
                        }
                    }
                )
                TextBox(
                    title = R.string.atwork_title_bird,
                    artist = R.string.atwork_artist_bird
                )
            }
            4 -> {
                ImageBox(
                    imageResource = R.drawable.mom,
                    modifier = Modifier.padding(top = 20.dp),
                    ondrag = {change, _ ->
                        changePos = change.positionChange().x
                        if (changePos < 0) {
                            step = 5
                            changePos = 0f
                        } else if (changePos > 0) {
                            step = 3
                            changePos = 0f
                        }
                    }
                )
                TextBox(
                    title = R.string.atwork_title_mom,
                    artist = R.string.atwork_artist_mom
                )
            }
            else -> {
                ImageBox(
                    imageResource = R.drawable.cool,
                    modifier = Modifier.padding(top = 20.dp),
                    ondrag = {change, _ ->
                        changePos = change.positionChange().x
                        if (changePos < 0) {
                            step = 5
                            showAlert = true
                            changePos = 0f
                        } else if (changePos > 0) {
                            step = 4
                            changePos = 0f
                        }
                    }
                )
                TextBox(
                    title = R.string.atwork_title_cool,
                    artist = R.string.atwork_artist_cool
                )
            }
        }
        Column (modifier = Modifier.padding(top = 20.dp)) {
            Row {
                ButtonBox(
                    label = R.string.button_previous,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        step--
                        if (step < 1) {
                            step = 1
                            showAlert = true
                        }
                    }
                )
                ButtonBox(
                    label = R.string.button_next,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        step++
                        if (step > 5) {
                            step = 5
                            showAlert = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ImageBox(
    @DrawableRes imageResource: Int,
    ondrag: (change: PointerInputChange, dragAmount: Offset) -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .shadow(elevation = 5.dp)
            .pointerInput(Unit) {
                detectDragGestures(onDrag = ondrag)
            }
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        )
    }
    Spacer(modifier = Modifier.height(80.dp))
}

@Composable
fun TextBox(
    @StringRes title: Int,
    @StringRes artist: Int,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFD8DEFF))
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(title),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier
        )
        Text(
            text = stringResource(artist, "2017")
        )
    }
}

@Composable
fun ButtonBox(
    @StringRes label: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(12.dp)
            .pointerInput(Unit) {
                detectTapGestures { }
            }
    ) {
        Text(text = stringResource(label))
    }
}

@Preview (
    showBackground = true
)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}