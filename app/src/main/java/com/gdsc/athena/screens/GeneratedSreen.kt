package com.gdsc.athena
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.End
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.End
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun genretedtScreen(
//    userspronmt : String,
    onNextButtonClicked: () -> Unit,
) {
    var text by remember { mutableStateOf(TextFieldValue("story Generated by AI")) }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(100.dp), verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.fillMaxWidth(0.5f))
            Button(onClick = { //
                //save to profile
                 }, Modifier.padding(all = 12.dp)) {
//                Icon(Icons.Filled.ArrowForward)
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
            Button(onClick = { onNextButtonClicked() }, Modifier.padding(all = 12.dp)) {
//                Icon(Icons.Filled.ArrowForward)
                Icon(
                    Icons.Filled.ArrowForward,
                    contentDescription = "Favorite",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        }
        Card(
            Modifier
                .fillMaxSize(),
            backgroundColor = Color.Black
        ) {
            Card(
                Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, bottom = 60.dp, start = 30.dp, end = 30.dp),
                backgroundColor = Color.Black,
                shape = RoundedCornerShape(35.dp),
                border = BorderStroke(4.dp, color = Color(0xFFEF5B0C))
            ) {
                TextField(
                    modifier = Modifier.padding(start = 4.dp, top = 10.dp, bottom = 18.dp),
                    label = {
                        Text(
                            text = "User's Entered Prompt",
                            style = TextStyle(color = Color.White, fontSize = 22.sp),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(all = 16.dp)
                        )
                    },
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        textColor = Color.Gray,
                    ),
                    maxLines = 50,
                    singleLine = false,
                )
            }

        }
    }
}