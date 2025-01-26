package com.example.colorpalette

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.colorpalette.ui.theme.ColorPaletteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ScreenMain()
        }
    }
}

@Composable
fun ColorDisplay(color: Color) {
    Column(
        modifier = Modifier
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(250.dp)
                .background(
                    color = color,
                    shape = RoundedCornerShape(8.dp)
                )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "RGB(" +
            "${(color.red * 255).toInt()}, " +
            "${(color.green * 255).toInt()}, " +
            "${(color.blue * 255).toInt()})"
        )
    }
}

@Composable
fun ColorPalette(
    colorDisplay: @Composable () -> Unit,
    colorSliders: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            colorDisplay()

            Spacer(modifier = Modifier
                .height(32.dp)
            )

            colorSliders()
        }
    } else {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            colorDisplay()

            Spacer(modifier = Modifier
                .width(32.dp)
            )

            colorSliders()
        }
    }
}

@Composable
fun ScreenMain() {
    ScreenLayout {
        val viewModel: ColorPaletteViewModel = viewModel()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            ColorPalette(
                colorDisplay = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ColorDisplay(color = Color(
                            red = viewModel.redColor,
                            green = viewModel.greenColor,
                            blue = viewModel.blueColor,
                        ))
                    }
                },
                colorSliders = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.width(250.dp)
                    ) {
                        Button(
                            onClick = { viewModel.generateRandomColor() }
                        ) {
                            Text(text = "Случайный")
                        }

                        Spacer(modifier = Modifier
                            .height(32.dp)
                        )

                        Slider(
                            value = viewModel.redColor.toFloat() / 255,
                            onValueChange = { viewModel.redColor = (it * 255).toInt() }
                        )
                        Slider(
                            value = viewModel.greenColor.toFloat() / 255,
                            onValueChange = { viewModel.greenColor = (it * 255).toInt() }
                        )
                        Slider(
                            value = viewModel.blueColor.toFloat() / 255,
                            onValueChange = { viewModel.blueColor = (it * 255).toInt() }
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun ScreenLayout(
    content: @Composable () -> Unit
) {
    ColorPaletteTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color(red = 192, green = 200, blue = 224)
                    )
                    .padding(innerPadding),
            ) {
                content()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun GreetingPreview() {
    ScreenMain()
}