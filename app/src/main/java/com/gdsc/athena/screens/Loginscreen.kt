package com.gdsc.athena

import android.text.style.StyleSpan
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.G
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(
    onNextButtonClicked: () -> Unit,
    onPrevButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val auth = FirebaseAuth.getInstance()
    val emailVal = remember { mutableStateOf(TextFieldValue()) }
    val passwdVal = remember { mutableStateOf(TextFieldValue()) }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF1D1D1D))
            .padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFFDFCF7),
                            Color(0xFF323232),
                            Color(0xFF343434),
                            Color.Transparent
                        )
                    )
                )
                .padding(bottom = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.Transparent)
                    .clickable { }
            ) {
                Image(
                    painter = painterResource(R.drawable.logo2__1_),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,            // crop the image if it's not a square
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color.Yellow,
                                    Color.Transparent
                                )
                            )
                        )
                )
            }
        }
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFFF772A), unfocusedBorderColor = Color(0xFFFF772A)),
            label = {
                Text(
                    text = "Email",
                    style = TextStyle(color = Color.Gray),
                    modifier = Modifier.fillMaxWidth(0.2f)
                )
            },
            value = emailVal.value,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            onValueChange = {
                emailVal.value = it
            }
        )
        Spacer(modifier = Modifier.size(10.dp))
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFFF772A), unfocusedBorderColor = Color(0xFFFF772A)),
            label = {
                Text(
                    text = "Password",
                    style = TextStyle(color = Color.Gray),
                    modifier = Modifier.fillMaxWidth(0.3f)
                )
            },
            value = passwdVal.value,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {
                passwdVal.value = it
            }
        )
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = {
//            navController.navigate(TitleSc.PromtS.name)
                auth.signInWithEmailAndPassword(
                    emailVal.value.text,
                    passwdVal.value.text
                ).addOnSuccessListener {
                    onNextButtonClicked()
                }.addOnFailureListener {
                    Log.d("FAILED!", it.toString())
                }
            },
            shape = RoundedCornerShape(24),
            colors = buttonColors(backgroundColor = Color(0XFFFF772A)),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.17f)
        )
        {
            Text(
                text = "Login", style = TextStyle(fontSize = 20.sp, color = Color(0xFFFCFBF7)),
                modifier = Modifier
                    .fillMaxWidth().fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(0.7f).clickable(onClick = {
                onPrevButtonClicked()
            }),
            text = buildAnnotatedString {
                append("Don't have an account ? ")
                withStyle(style = SpanStyle(
                        color = Color.Blue ,
                    textDecoration = TextDecoration.Underline
                )
                ){
                    append("sign up")
                }
            },
            style = TextStyle(color = Color.Gray, textAlign = TextAlign.Center),
        )
    }
}

//fun HomeScreen(navController: NavController) {
//    var text by remember {
//        mutableStateOf("")
//    }
//    Column(Modifier.fillMaxSize()) {
//        Box(
//            modifier = Modifier
//                .background(color = Color(0xFF3F4043)),
//            contentAlignment = Alignment.Center
//        )
//        {
//            TextField(value = "Homescreen", onValueChange = { text = it })
//        }
//        Button(onClick = { navController.navigate(Screen.SelectioScreen.withArgs(text)) }) {
//        }
//    }
//}
//
//@Composable
//@Preview(showBackground = true)
//fun homescreenpre(){
//    LoginScreen(onNextButtonClicked = {})
//}