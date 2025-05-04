package com.example.assignment.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment.ui.screen.HomeScreen
import com.example.assignment.ui.screen.SplashScreen
import com.example.assignment.ui.screen.detail.DetailProduct
import com.example.assignment.ui.viewmodel.HomeViewModel
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: HomeViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash"){
            viewModel.onScreenOpened("SplashScreen")
            SplashScreen(navHostController = navController)

        }
        composable("home") {
            viewModel.onScreenOpened("HomeScreen")
            HomeScreen(viewModel = viewModel, navHostController = navController)

        }
        composable("detail_agent") {
            DetailProduct(viewModel = viewModel, navHostController = navController)
            viewModel.onScreenOpened("DetailProductScreen")
        }

    }
}