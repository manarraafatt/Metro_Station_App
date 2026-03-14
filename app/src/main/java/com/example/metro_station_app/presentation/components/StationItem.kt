package com.example.metro_station_app.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StationItem(
    station: String,
    isStart: Boolean,
    isEnd: Boolean,
    isTransfer: Boolean,
    showLine: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),  // 2
        verticalAlignment = Alignment.CenterVertically
    ) {
            Box(
                modifier = Modifier
                    .width(40.dp),
                contentAlignment = Alignment.Center
            ){

            if (showLine) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(50.dp)
                        .align(Alignment.BottomCenter)
                        .background(Color.White.copy(0.5f))
                )
            }
                val circleColor = when{
                    isTransfer -> Color(0xFFFBC02D)
                    isStart || isEnd -> Color(0xFF03A9F4)
                    else -> Color(0xFF4CAF50)
                }

                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .background(Color(0xFF1A2C4E), CircleShape)
                        .border(
                            width = 3.dp,
                            color = circleColor,
                            shape = CircleShape
                        )
                )
        }
        Spacer(modifier = Modifier.width(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = station,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            if (isTransfer){
                Spacer(modifier = Modifier.width(10.dp))
                Surface(
                    color = Color(0xFFFBC02D).copy(alpha = 0.2f),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp,Color(0xFFFBC02D))
                ) {
                    Text(
                        text = "Transfer",
                        color = Color(0xFFFBC02D),
                        fontSize = 10.sp,
                        modifier = Modifier.padding(horizontal = 6.dp , vertical = 2.dp)
                    )
                }
            }
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint = Color.White.copy(0.5f),
            modifier = Modifier.size(20.dp)
        )

    }
}