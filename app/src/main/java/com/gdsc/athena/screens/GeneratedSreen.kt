package com.gdsc.athena
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@Composable
fun genratedtScreen(){
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Card(
        Modifier
            .fillMaxSize(),
        backgroundColor = Color.Black
    ) {
        Card(
            Modifier
                .fillMaxSize()
                .padding(top = 60.dp, bottom = 60.dp, start = 30.dp, end = 30.dp),
            backgroundColor = Color.Black,
            shape = RoundedCornerShape(35.dp),
            border = BorderStroke(4.dp, color = Color(0xFFEF5B0C))
        ) {
            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                placeholder = { Text(text = "Enter your prompt here...", modifier = Modifier.padding(start = 7.dp, bottom = 470.dp), color = Color.White) },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    textColor = Color.White,

                    ),
                maxLines = 50,
                singleLine = false,

                )


        }

    }
}