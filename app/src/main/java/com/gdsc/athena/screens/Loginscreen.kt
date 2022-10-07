package com.gdsc.athena

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginScreen(
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF1D1D1D))
        .padding(top = 120.dp),
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
                        .size(100.dp)
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
        Spacer(modifier = Modifier.size(100.dp))
        Button(onClick = {
//            navController.navigate(TitleSc.PromtS.name)
            onNextButtonClicked()
        },shape = RoundedCornerShape(24), colors = buttonColors(backgroundColor = Color(0XFFFF772A)), modifier = Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.24f)) {
            Row(modifier = Modifier.fillMaxSize()){
                Text(text = "G" , style = TextStyle(fontSize = 45.sp , color = Color(0xFFFCFBF7) , fontWeight = FontWeight.ExtraBold) , modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.CenterStart),textAlign = TextAlign.Center)
                Text(text = "Login using Google" ,style = TextStyle(fontSize = 25.sp , color = Color(0xFFFCFBF7)), modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.Center), textAlign = TextAlign.Center,)
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "This account will be used to save your generated stories." , style = TextStyle(color = Color.Gray , textAlign = TextAlign.Center) , modifier = Modifier.fillMaxWidth(0.7f))
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
//    HomeScreen(null)
//}