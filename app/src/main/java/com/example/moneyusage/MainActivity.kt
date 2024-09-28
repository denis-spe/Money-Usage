package com.example.moneyusage

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneyusage.ui.theme.MoneyUsageTheme
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {

    // Declare the authentication
    private lateinit var authentication: FirebaseAuth
    private var currentUser: FirebaseUser? = null

    // Instantiate firestore
    private var firestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the app
        FirebaseApp.initializeApp(this)

        // Assign the authentication
        authentication = Firebase.auth

        setContent {
            MoneyUsageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Initialize the app
                    AppManager(
                        currentUser = currentUser,
                        auth = authentication,
                        firestore = firestore
                    )
                }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = authentication.currentUser

    }
}


@RequiresApi(Build.VERSION_CODES.S)
@Preview(showBackground = true)
@Composable
fun MainPreview() {

    MoneyUsageTheme {
        AppManager()
    }
}