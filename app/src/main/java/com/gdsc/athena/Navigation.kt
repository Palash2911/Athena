package com.gdsc.athena

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gdsc.athena.screens.createAccount
import com.gdsc.athena.ui.PromtScreen
import com.google.firebase.auth.FirebaseAuth
enum class TitleSc(val title: String) {
    Start(title = R.string.app_name.toString()),
    PromtS(title = "Prompt Screen"),
    SelectionSc(title = "Choose Category"),
    GeneratedS(title = "Generated story"),
    ProfileS(title = "Profile"),
    CreateS(title = "Create new account")
//    Summary(title = R.string.order_summary)
}
@Composable
fun HomeScreen(
    currentScreen: TitleSc,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,) {
    TopAppBar(
        title = { Text((currentScreen.title)) },
        modifier = Modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back button"
                    )
                }
            }
        }
    )
}
@Composable
fun LoginScreens(
    modifier: Modifier = Modifier,
//    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
)
{
    val auth = FirebaseAuth.getInstance()
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = TitleSc.valueOf(
        backStackEntry?.destination?.route ?: TitleSc.Start.name
    )
    NavHost(
        navController = navController,
        startDestination = if(auth.currentUser!=null){TitleSc.ProfileS.name}else{TitleSc.Start.name},
    ) {
        composable(route = TitleSc.Start.name) {
            LoginScreen(
                onNextButtonClicked = {
                    navController.navigate(TitleSc.ProfileS.name)
                }, onPrevButtonClicked = {
                navController.navigate(TitleSc.CreateS.name)
            }
            )
        }
        composable(route = TitleSc.PromtS.name) {
            val context = LocalContext.current
            PromtScreen(
                onNextButtonClicked = {  navController.navigate(TitleSc.GeneratedS.name )},
            )
        }
        composable(route = TitleSc.SelectionSc.name) {
            val context = LocalContext.current
            SelectionScreen(
                onNextButtonClicked = { navController.navigate(TitleSc.PromtS.name) },
            )
        }
        composable(route = TitleSc.GeneratedS.name) {
            val context = LocalContext.current
            genretedtScreen(
                onNextButtonClicked = { navController.navigate(TitleSc.ProfileS.name) },
            )
        }
        composable(route = TitleSc.ProfileS.name) {
            val context = LocalContext.current
            ProfileScreen(
                onNextButtonClicked = { navController.navigate(TitleSc.SelectionSc.name) },
                     onPrevButtonClicked = {
                navController.navigate(TitleSc.Start.name)
            }
            )
        }
        composable(route = TitleSc.CreateS.name) {
            val context = LocalContext.current
            createAccount(
                onNextButtonClicked = { navController.navigate(TitleSc.ProfileS.name) },
                    onPrevButtonClicked = {
                navController.navigate(TitleSc.Start.name)
            }
            )
        }
    }
}