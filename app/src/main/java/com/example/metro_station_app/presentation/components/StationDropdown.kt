package com.example.metro_station_app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StationDropdown(
    label: String,
    station: List<String>,
    selectedStation: String,
    onStationSelected: (String) -> Unit,
    ) {

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded =! expanded }
    ) {
        OutlinedTextField(
            value = selectedStation,
            onValueChange = {},
            readOnly = true,
            placeholder = {Text(label , color = Color.White.copy(0.5f))},
            leadingIcon = {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.White.copy(0.7f)
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Black.copy(alpha = 0.2f),
                unfocusedContainerColor = Color.Black.copy(alpha = 0.2f),
                focusedBorderColor = Color.White.copy(alpha = 0.3f),
                unfocusedBorderColor = Color.White.copy(alpha = 0.1f),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(
                Color(0xFF1A2C4E)
            )
        ) {
            station.forEach { station ->
                DropdownMenuItem(

                    text = {
                        Text(station , color = Color.White)
                           },
                    onClick = {
                        onStationSelected(station)
                        expanded = false
                    }
                )
            }
        }
    }
}