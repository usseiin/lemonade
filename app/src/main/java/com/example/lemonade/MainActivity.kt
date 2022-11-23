package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun TextWithTappableImage(modifier: Modifier) {
    var index by remember {
        mutableStateOf(1)
    }

    var numberOfRepeatSet by remember {
        mutableStateOf(2)
    }

    var numberOfRepeat by remember {
        mutableStateOf(1)
    }

    val text = when (index) {
        1 -> R.string.text_1
        2 -> R.string.text_2
        3 -> R.string.text_3
        else -> R.string.text_4
    }

    val imageResource = when (index) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(text), fontSize = 18.sp)
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                index = when (index) {
                    1 -> 2
                    3 -> 4
                    4 -> 1
                    else -> {
                        if (numberOfRepeat == numberOfRepeatSet) {
                            numberOfRepeatSet = (2..3).random()
                            numberOfRepeat = 0
                            3
                        } else {
                            numberOfRepeat++
                            2
                        }
                    }
                }
            }, colors = ButtonDefaults.buttonColors(Color.Transparent), border = BorderStroke(
                5.dp,
                Color(105, 205, 216)
            )
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = imageResource.toString()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    TextWithTappableImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}