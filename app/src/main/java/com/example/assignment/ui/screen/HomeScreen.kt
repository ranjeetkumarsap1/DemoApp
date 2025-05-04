package com.example.assignment.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.assignment.ui.theme.whiteV
import com.example.assignment.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navHostController: NavHostController){
    Scaffold(topBar = {
        CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            whiteV
        ),
            title = {
                Text(
                    "Insurance Products",
                    style = TextStyle(
                       // fontFamily = valorantFont,
                        color = Color.Black,
                        fontSize = 30.sp
                    )
                )
            })
    }) {
        TabScreen(modifier = Modifier.padding(it), navHostController, viewModel)
    }
}



@Composable
fun TabScreen(modifier: Modifier, navHostController: NavHostController, viewModel: HomeViewModel) {
    viewModel.onScreenOpened("ProductsListScreen")
    ProductsList(navHostController, viewModel)


}
