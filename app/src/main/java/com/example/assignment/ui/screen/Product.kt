package com.example.assignment.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.assignment.data.InsuranceProductItem
import com.example.assignment.ui.theme.blackV
import com.example.assignment.ui.theme.whiteV
import com.example.assignment.ui.viewmodel.HomeViewModel

@Composable
fun AgentsCard(dataItem: InsuranceProductItem, navHostController: NavHostController, viewModel: HomeViewModel) {
    Box(
        Modifier
            .height(200.dp)
            .background(whiteV, shape = RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(horizontal = 2.dp)
            .clickable {
                viewModel.selectedAgents(dataItem)
                navHostController.navigate("detail_agent")
                viewModel.onItemClicked(""+dataItem.type)

            }
    ) {

        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 4.dp)
        ){


            Image(
                                contentScale = ContentScale.Fit,
                painter = rememberAsyncImagePainter(dataItem.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .height(110.dp)
                    .width(97.dp)
                    .padding(horizontal = 2.dp)
                    .graphicsLayer {
                        translationY = (20).toFloat()
                        translationX = (-0).toFloat()
                    }
                    .padding(start = 0.dp)
            )
            Column(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .padding(start = 10.dp)
            ) {

                Text(
                    ""+dataItem.type ?: "Error",
                    style = TextStyle(
                        // fontFamily = valorantFont,
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                    )


                Row(modifier = Modifier
                    .padding(top = 5.dp)) {
                    Text(
                        ""+  dataItem.title ?: "Error",
                        style = TextStyle(
                            // fontFamily = valorantFont,
                            color = Color.Black,
                            fontSize = 14.sp
                        )

                    )
                }

                Row(modifier = Modifier
                    .padding(top = 5.dp)) {
                    Text(
                        "Monthly Premium : " ?: "Error",
                        style = TextStyle(
                            // fontFamily = valorantFont,
                            color = Color.DarkGray,
                            fontSize = 14.sp
                        )

                    )
                    Text(
                        "₹ "+  dataItem.monthlyPremium ?: "Error",
                        style = TextStyle(
                            // fontFamily = valorantFont,
                            color = Color.Blue,
                            fontSize = 14.sp
                        )

                    )
                }

                Row(modifier = Modifier
                    .padding(top = 5.dp)) {
                    /*Text(
                        "Description : " ?: "Error",
                        style = TextStyle(
                            // fontFamily = valorantFont,
                            color = Color.DarkGray,
                            fontSize = 14.sp
                        )

                    )*/
                    Text(
                        "Description : "+  dataItem.description ?: "Error",
                        style = TextStyle(
                            // fontFamily = valorantFont,
                            color = Color.Black,
                            fontSize = 14.sp
                        )

                    )
                }



            }

        }
        }


}

@Composable
fun ProductsList(navHostController: NavHostController, viewModel: HomeViewModel) {

    val agentsData = viewModel.agentsData.collectAsState()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(top = 65.dp)
            .background(blackV)
            .fillMaxSize()
    ) {
        items(count = agentsData.value.size) {
            AgentsCard(agentsData.value[it], navHostController, viewModel)
        }



    }


}