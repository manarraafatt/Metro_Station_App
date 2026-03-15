package com.example.metro_station_app.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.metro_station_app.R
import com.example.metro_station_app.presentation.components.BottomStats
import com.example.metro_station_app.presentation.components.StationItem
import com.example.metro_station_app.presentation.components.TopBar
import com.example.metro_station_app.presentation.viewModel.MetroViewModel
import domain.model.RouteResult

@Composable
fun ResultScreen(
    viewModel: MetroViewModel,
    navController: NavController,
    isArabic: Boolean
) {

    val result = viewModel.result
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(Color.Black.copy(0.7f), BlendMode.Darken)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            TopBar(navController, isArabic)
            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                shape = RoundedCornerShape(25.dp),
                colors = CardDefaults.cardColors(Color.White.copy(0.05f)),
                border = BorderStroke(1.dp, Color.White.copy(0.1f))
            ) {
                Column(modifier = Modifier.fillMaxSize()) {

                    if (result is RouteResult.Success) {
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 15.dp),
                            contentPadding = PaddingValues(vertical = 10.dp)
                        ) {
                            val distinctStations = result.stations.distinctBy { it.name }
                            itemsIndexed(distinctStations) { index, station ->
                                StationItem(
                                    station = station.name,
                                    isStart = index == 0,
                                    isEnd = index == distinctStations.lastIndex,
                                    isTransfer = station.isTransfer,
                                    showLine = index != result.stations.lastIndex,
                                    isArabic = isArabic
                                )
                            }
                        }
                        Box(modifier = Modifier.padding(15.dp)) {
                            BottomStats(
                                stationCount = result.stations.size,
                                time = result.time,
                                fare = result.fare,
                                isArabic = isArabic
                            )
                        }
                    } else if (result is RouteResult.Error) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (isArabic) "لم يتم العثور على مسار" else result.message,
                                color = Color.Red,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}