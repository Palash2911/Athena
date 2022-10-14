package com.gdsc.athena.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun createAccount( onNextButtonClicked: () -> Unit,
                   modifier: Modifier = Modifier
){
    var firstname by remember { mutableStateOf(TextFieldValue("")) }
    var lastname by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF1D1D1D))
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFFF772A), unfocusedBorderColor = Color(0xFFFF772A)
            ),
            label = {
                Text(
                    text = "first name",
                    style = TextStyle(color = Color.Gray),
                    modifier = Modifier.fillMaxWidth(0.27f)
                )
            },
            value = firstname,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            onValueChange = {
                firstname = it
            }
        )
        Spacer(modifier = Modifier.size(20.dp))
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFFF772A), unfocusedBorderColor = Color(0xFFFF772A)
            ),
            label = {
                Text(
                    text = "last name",
                    style = TextStyle(color = Color.Gray),
                    modifier = Modifier.fillMaxWidth(0.27f)
                )
            },
            value = lastname,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            onValueChange = {
                lastname = it
            }
        )
        Spacer(modifier = Modifier.size(20.dp))
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFFF772A), unfocusedBorderColor = Color(0xFFFF772A)
            ),
            label = {
                Text(
                    text = "Email",
                    style = TextStyle(color = Color.Gray),
                    modifier = Modifier.fillMaxWidth(0.16f)
                )
            },
            value = email,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            onValueChange = {
                email = it
            }
        )
        Spacer(modifier = Modifier.size(20.dp))
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFFF772A), unfocusedBorderColor = Color(0xFFFF772A)
            ),
            label = {
                Text(
                    text = "Password",
                    style = TextStyle(color = Color.Gray),
                    modifier = Modifier.fillMaxWidth(0.27f)
                )
            },
            value = password,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {
                password = it
            }
        )
        Spacer(modifier = Modifier.size(60.dp))
        Button(
            onClick = {


            },
            shape = RoundedCornerShape(24),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFFFF772A)),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.17f)
        )
        {
            Text(
                text = "Create account", style = TextStyle(fontSize = 20.sp, color = Color(0xFFFCFBF7)),
                modifier = Modifier
                    .fillMaxWidth().fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
    }
}
