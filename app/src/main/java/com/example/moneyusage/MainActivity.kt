package com.example.moneyusage

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import com.example.moneyusage.backend.Data
import com.example.moneyusage.backend.DataRepository
import com.example.moneyusage.backend.DataViewModel
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

    @RequiresApi(Build.VERSION_CODES.R)
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
//                    AppManager(
//                        currentUser = currentUser,
//                        auth = authentication,
//                        firestore = firestore
//                    )
                    Column {
                        val db = DataRepository(
                            collectionName = "database",
                            documentName = "test"
                        )
                        db.createData()

                        db.addValueToField("expense", Data(
                            amount = 100.23,
                            date = "11/12/2023",
                            description = "First expense"
                        )
                        )

                        val viewModel = DataViewModel("database", "test")
                        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                            viewModel.getData("income").observeAsState(initial = null)
                        } else {
                            TODO("VERSION.SDK_INT < UPSIDE_DOWN_CAKE")
                        }
                        Text(data.value.toString())

//                        LaunchedEffect(Unit) {
//                            state.value = db.getData().toString()
//                        }

//                        firestore.collection("database")
//                            .document("test")
//                            .get()
//                            .addOnSuccessListener {
//                                Log.d("GoogleService", it.data.toString())
//                            }

                        Log.d("GoogleService", "Trying to fetch data")
                    }
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