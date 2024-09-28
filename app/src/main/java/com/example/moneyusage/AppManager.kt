package com.example.moneyusage

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneyusage.frontend.pages.PageManager
import com.example.moneyusage.ui.theme.MoneyUsageTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AppManager(
    currentUser: FirebaseUser? = null,
    auth: FirebaseAuth? = null,
    firestore: FirebaseFirestore? = null
){
    PageManager(
        currentUser = currentUser,
        auth = auth
    )
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MoneyUsageTheme {
        // Start the app
        AppManager()
    }
}