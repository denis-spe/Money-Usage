package com.example.moneyusage

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneyusage.pages.RegistrationPage
import com.example.moneyusage.pages.landingPage.LandingPage
import com.example.moneyusage.ui.theme.MoneyUsageTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Application
 */
class App(
    private val auth: FirebaseAuth? = null,
    private val currentUser: FirebaseUser? = null,
    private val firestore: FirebaseFirestore? = null
){
    /**
     * App starting point
     */
    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun Start(){
        // MutableState object
        val input = mutableStateOf(TextFieldValue(""))

        // Run the sign in page
        LandingPage().LandPage()
    }

    /**
     * Manage page changes
     */
    @Composable
    private fun PageManager(){

    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MoneyUsageTheme {
        // Start the app
    }
}