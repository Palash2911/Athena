package com.gdsc.athena

sealed class Screen(val route : String){

    object HomeScreen : Screen("home_screen")
    object  SelectioScreen : Screen("selectio_screen")

    fun withArgs(vararg args : String): String{
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}
