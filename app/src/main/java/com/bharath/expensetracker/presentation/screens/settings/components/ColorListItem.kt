package com.bharath.expensetracker.presentation.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorListItem(color:Color,onclick:() -> Unit) {

    Box(modifier = Modifier
        .size(45.dp)
        .clip(CircleShape)
        .clickable {
                   onclick()
        }
        ,){
        Box(Modifier.fillMaxSize().background(color))
    }

}