package com.example.metro_station_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.metro_station_app.presentation.screens.HomeScreen
import com.example.metro_station_app.presentation.screens.ResultScreen
import di.AppModule

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var isArabic by remember { mutableStateOf(false) }

            val viewModel = remember(isArabic) {
                AppModule.provideMetroViewModel(applicationContext, isArabic)
            }

            MaterialTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") {
                            HomeScreen(
                                navController = navController,
                                viewModel = viewModel,
                                isArabic = isArabic,
                                onToggleLanguage = { isArabic = !isArabic }
                            )
                        }

                        composable(route = "result") {
                            ResultScreen(
                                viewModel = viewModel,
                                navController = navController,
                                isArabic = isArabic
                            )
                        }
                    }
                }
            }
        }
    }
}
