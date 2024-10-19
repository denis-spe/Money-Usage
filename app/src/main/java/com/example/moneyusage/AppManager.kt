package com.example.moneyusage

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneyusage.frontend.pages.PageManager
import com.example.moneyusage.ui.theme.MoneyUsageTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

@RequiresApi(Build.VERSION_CODES.P)
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


@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MoneyUsageTheme {
        // Start the app
        AppManager()
    }
}