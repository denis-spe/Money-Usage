package com.example.moneyusage.helper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * Authorization Error message
 */
data class AuthError(
    val icon: ImageVector = Icons.Outlined.Warning,
    val message: String){
    @Composable
    fun Show(){
        // Error color
        val color = Color.Red

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            // Error icon
            Icon(
                imageVector = icon,
                contentDescription = "Error icon",
                tint = color)

            Spacer(modifier = Modifier.width(10.dp))

            // Error message
            Text(
                text = message,
                color = color)
        }
    }
}
