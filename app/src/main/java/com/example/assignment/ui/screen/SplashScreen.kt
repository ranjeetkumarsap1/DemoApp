package com.example.assignment.ui.screen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.assignment.R
import com.example.assignment.ui.theme.blackV
import com.example.assignment.ui.theme.whiteV
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navHostController: NavHostController) {

    val alpha = remember { androidx.compose.animation.core.Animatable(0f) }
    LaunchedEffect(key1 = true) {
        alpha.animateTo(1f, animationSpec = tween(durationMillis = 1500))
        delay(2000)
        navHostController.navigate("home") {
            popUpTo("splash") {
                inclusive = true
            }
        }
    }
    Box(
        Modifier
            .fillMaxSize().background(whiteV).alpha(alpha.value)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "SplashScreen Logo",
            modifier = Modifier
                .align(Alignment.Center)
                .size(128.dp)
        )
    }
}