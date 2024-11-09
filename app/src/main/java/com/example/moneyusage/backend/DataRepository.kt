package com.example.moneyusage.backend


import android.os.Build
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

/**
 * A class that represents a database.
 * @param db The FirebaseFirestore object.
 * @param collectionName The name of the collection.
 * @param documentName The name of the document.
 */
class DataRepository(
    private val collectionName: String,
    private val documentName: String
) {

    //    var snapshotData: Any? = null
    private val db = FirebaseFirestore.getInstance()

    /**
     * Create a new document in the collection
     */
    fun createData(){
        db.collection(collectionName)
            .document(documentName)
            .set(Fields())
    }

    /**
     * Add a value to a field in the document
     * @param fieldName The name of the field.
     * @param value The value to add to the field.
     */
    fun addValueToField(fieldName: String, value: Data){
        val fieldNames = listOf(
            "income", "expense",
            "debt", "lend")

        // Check if the field name is in the list
        if (fieldName in fieldNames)
            db.collection(collectionName)
                .document(documentName)
                .update(fieldName, FieldValue.arrayUnion(value))
    }

    // Function to get users from Firestore and expose it as LiveData
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    fun getData(fieldName: String): LiveData<Any?> {
        val liveData = MutableLiveData<Any?>()

        // Fetch data asynchronously
        db.collection(collectionName)
            .document(documentName)
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val data = snapshot.get(fieldName)
                    val income = (data as List<*>)
                        .map { it as MutableMap<*, *> }
                        .map {
                            Data(
                                amount = it["amount"].toString().toDouble(),
                                date = it["date"].toString(),
                                description = it["description"].toString()
                            )
                        }

                    // Get the document data as a map
                    Log.d("Success", "Fetched data: $income")
                    liveData.postValue(income)  // Post the fetched data to LiveData
                } else {
                    Log.d("No data", "Document does not exist")
                    liveData.postValue(null)  // No document, post null
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Error getting documents", "Error getting documents: ", exception)
                 // You can handle the error more gracefully

            }

        return liveData
    }

    @RequiresApi(Build.VERSION_CODES.R)
    class LiveDatabase(
        private val collectionName: String,
        private val documentName: String
    ): ViewModel() {
        private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        private val _data = MutableLiveData<MutableList<Any>>()
        val data: MutableLiveData<MutableList<Any>> get() = _data
        private var listenerRegistration: ListenerRegistration? = null


        @RequiresApi(Build.VERSION_CODES.R)
        fun fetchData() {
            listenerRegistration = db.collection(collectionName)
                .document(documentName)
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    if (snapshot != null && snapshot.exists()) {
                        _data.value = mutableListOf(snapshot)
                    } else {
                        Log.d(TAG, "Current data: null")
                    }
                }
        }
    }
}