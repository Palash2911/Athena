package com.gdsc.athena

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController , startDestination = Screen.HomeScreen.route
    ){
        composable(route = Screen.HomeScreen.route + "/{name}"){
                HomeScreen(navController = navController)
        }
        composable(
            route = Screen.SelectioScreen.route,
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    defaultValue = "aihrar"
                    nullable = true
                }
            )
        ){
            entry -> SelectioScreen()
        }
    }
}
//
//@Composable
//fun SelectioScreen(name: String?){
//    Box(
//        modifier = Modifier.fillMaxSize()
//            .background(color = Color(0xFF3F4043)),
//        contentAlignment = Alignment.Center
//    )
//    {
//        Text(
//            text = "home",
//            color = Color.Red,
//            fontWeight = FontWeight.Bold,
//            fontSize = MaterialTheme.typography.h3.fontSize,
//        )
//    }
//}
//
//@Composable
//fun HomeScreen(navController: NavController){
//    var text by remember{
//        mutableStateOf("")
//    }
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//
//            .background(color = Color(0xFF3F4043)),
//        contentAlignment = Alignment.Center
//    )
//    {
//        TextField(value = "Homescreen", onValueChange = {text = it} )
//        Button(onClick = {navController.navigate(Screen.SelectioScreen.withArgs(text))}) {
//
//        }
//    }
//}
