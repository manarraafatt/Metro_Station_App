package com.example.metro_station_app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.metro_station_app.R

@Composable
fun BottomStats(
    stationCount: Int,
    time: Int,
    fare: Int,
    isArabic: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(0xFF1A2C4E).copy(alpha = 0.8f),
                RoundedCornerShape(20.dp)
            )
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatUnit(
            icon = R.drawable.stations_icon,
            label = if (isArabic) "محطات" else "Stations",
            value = "$stationCount",
            color = Color(0xFF4CAF50)
        )

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(40.dp)
                .background(Color.White.copy(0.1f))
        )

        StatUnit(
            icon = R.drawable.time_icon,
            label = if (isArabic) "الوقت" else "Time",
            value = if (isArabic) "$time دقيقة" else "$time min",
            color = Color(0xFFFF9800)
        )

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(40.dp)
                .background(Color.White.copy(0.1f))
        )

        StatUnit(
            icon = R.drawable.mony_icon,
            label = if (isArabic) "السعر" else "Fare",
            value = if (isArabic) "$fare جنيه" else "$fare EGP",
            color = Color(0xFF03A9F4)
        )
    }
}

@Composable
fun StatUnit(icon: Int, label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Text(
            label,
            color = Color.White.copy(alpha = 0.6f),
            fontSize = 12.sp
        )
        Text(
            value,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}