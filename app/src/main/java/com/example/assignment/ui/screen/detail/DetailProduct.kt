package com.example.assignment.ui.screen.detail


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.assignment.ui.theme.blackV
import com.example.assignment.ui.theme.tungstenFont
import com.example.assignment.ui.theme.whiteV
import com.example.assignment.ui.viewmodel.HomeViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailProduct(viewModel: HomeViewModel, navHostController: NavHostController) {
    val pagerState = rememberPagerState(initialPage = 0)
    VerticalPager(pageCount = 1, state = pagerState) {
        WeaponHeader(viewModel = viewModel, it, pagerState, navHostController)


    }
} 

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeaponHeader(
    viewModel: HomeViewModel,
    pagerIndex: Int,
    pagerState: PagerState,
    navHostController: NavHostController
) {
    val shouldShowDialog = remember { mutableStateOf(false) } // 1

    if (shouldShowDialog.value) {
        MyAlertDialog(shouldShowDialog = shouldShowDialog)
    }
    val data = viewModel.selectedWeapon
    val mContext = LocalContext.current
    when (pagerIndex) {
        0 -> {
            BoxWithConstraints() {
                Box(
                    Modifier
                        .background(blackV)


                ) {

                    Column(Modifier.height(this@BoxWithConstraints.maxHeight)) {

                        Box(
                            Modifier
                                .fillMaxHeight(0.5f)
                                .fillMaxWidth(1f)
                                .background(
                                    whiteV,
                                    RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                                )

                        )
                        Box(
                            Modifier
                                .fillMaxHeight(0.4f)
                                .fillMaxWidth(1f)
                                .background(blackV)
                        )
                    }
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .height(this@BoxWithConstraints.maxHeight)
                    ) {
                        Image(
                            contentScale = ContentScale.Fit,
                            painter = rememberAsyncImagePainter(model = data?.imageUrl),
                            contentDescription = "Product Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.55f)

                        )
                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(verticalArrangement = Arrangement.Center) {
                                Text(
                                    data?.type ?: "",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 50.sp,
                                        fontFamily = tungstenFont
                                    )
                                )
                                Text(
                                    "(${data?.title})" ?: "",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 30.sp,
                                        fontFamily = tungstenFont
                                    )
                                )

                            }
                        }

                        Text(
                            ("Premium   : ₹" + data?.monthlyPremium?.toString()) ?: "",
                            style = TextStyle(color = Color.White),
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Text(
                            ("Coverage  : " + data?.covrage?.toString()) ?: "",
                            style = TextStyle(color = Color.White)
                        )
                        Text(
                            ("Tenure      : " + data?.tenure?.toString()) ?: "",
                            style = TextStyle(color = Color.White)
                        )

                        Text(
                            ("Description : " + data?.description?.toString()) ?: "",
                            style = TextStyle(color = Color.White)
                        )


                    }
                    AnimatedVisibility(
                        visible = !pagerState.isScrollInProgress,
                        enter = fadeIn(),
                        exit = fadeOut(),
                        modifier = Modifier.align(
                            Alignment.BottomCenter
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(bottom = 5.dp)
                                .clickable { shouldShowDialog.value = true }

                        ) {
                            Text(
                                "Buy Now",
                                style = TextStyle(fontSize = 20.sp),
                                color = Color.White
                            )
                            Icon(
                                Icons.Default.KeyboardArrowUp,
                                contentDescription = "Arrow Down",
                                tint = Color.White
                            )
                        }
                    }
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Arrow Back",
                            modifier = Modifier.size(50.dp)
                        )
                    }

                }
            }
        }


    }




}


@Composable
fun MyAlertDialog(shouldShowDialog: MutableState<Boolean>) {
    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                shouldShowDialog.value = false
            },

            title = { Text(text = "Success") },
            text = { Text(text = "Thank you! Buy successfully.") },
            confirmButton = {
                Button(
                    onClick = {
                        shouldShowDialog.value = false
                    }
                ) {
                    Text(
                        text = "Ok",
                        color = Color.White
                    )
                }
            }
        )
    }
}




