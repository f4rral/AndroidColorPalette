package com.example.colorpalette

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun ScreenMain() {
    ScreenLayout {
        val viewModel: ColorPaletteViewModel = viewModel()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier
                .height(64.dp)
            )

            ColorDisplay(color = viewModel.currentColor)

            Spacer(modifier = Modifier
                .height(32.dp)
            )

            Button(
                onClick = { viewModel.generateRandomColor() }
            ) {
                Text(text = "Сгенерировать цвет")
            }
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