package com.example.moneyusage

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moneyusage.backend.Data
import com.example.moneyusage.backend.DataRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.moneyusage", appContext.packageName)
    }

    @Test
    fun testAddingDataInFirestore(){
        val firestore = Firebase.firestore

        val db = DataRepository(
            db = firestore,
            collectionName = "database",
            documentName = "test"
        )
        db.addData(
            Data(
                name = "income",
                amount = 23.23,
                date = "23/02/2023"
            )
        )
    }
}