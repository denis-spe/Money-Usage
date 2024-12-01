package com.example.moneyusage

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneyusage.frontend.screens.ScreenManager
import com.example.moneyusage.ui.theme.MoneyUsageTheme

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun App(){
    MoneyUsageTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            ScreenManager()

        }
    }
}


@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun AppPreview() {
    // Start the app
    App()
}