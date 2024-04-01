package com.example.moneyusage

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
    @Composable
    fun Start(){

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