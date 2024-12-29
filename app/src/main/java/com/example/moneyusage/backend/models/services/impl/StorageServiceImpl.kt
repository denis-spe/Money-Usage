package com.example.moneyusage.backend.models.services.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyusage.backend.models.Data
import com.example.moneyusage.backend.models.services.interf.AccountService
import com.example.moneyusage.backend.models.services.interf.StorageService
import com.google.firebase.Firebase
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl@Inject constructor(
    private val auth: AccountService
): StorageService, ViewModel() {

    private val _dataset = MutableStateFlow<List<Data>>(emptyList())
    override val dataset: StateFlow<List<Data>> = _dataset

    private val _isLoading = MutableStateFlow(true)
    override val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    @OptIn(ExperimentalCoroutinesApi::class)
    val data: Flow<List<Data>>
        get() =
            auth.currentUser.flatMapLatest { user ->
                if (user == null) {
                    // If there's no user logged in, return an empty flow
                    flowOf(emptyList())
                } else {
                    // Firestore query that emits a flow of data
                    Firebase.firestore
                        .collection(DATA_COLLECTION)
                        .whereEqualTo(USER_ID_FIELD, user.id)  // Use the Firebase user ID
                        .dataObjects()
                }
            }

    fun fetchData() {
        // Collect data and update _data state flow
        viewModelScope.launch {
            data.collect { fetchedData ->
                _isLoading.value = true
                _dataset.value = fetchedData  // Update the data state
                _isLoading.value = false
            }
        }
    }

    /**
     * Add a new data to the database
     * @param data The data to add.
     */
    override suspend fun addData(data: Data) {
        val dataWithUserId = data.copy(userId = auth.currentUserId)
        Firebase.firestore
            .collection(DATA_COLLECTION)
            .add(dataWithUserId)
            .await()
    }

    /**
     * Get a data from the database
     * @param dataId The id of the data to get.
     * @return The data if it exists, null otherwise.
     */
    override suspend fun getData(dataId: String): Data? {
        return Firebase.firestore
            .collection(DATA_COLLECTION)
            .document(dataId)
            .get()
            .await()
            .toObject()
    }

    /**
     * Delete a data from the database
     * @param dataId The data to delete.
     */
    override suspend fun deleteData(dataId: String) {
        Firebase.firestore
            .collection(DATA_COLLECTION)
            .document(dataId)
            .delete()
            .await()
    }

    /**
     * Update a data from the database
     * @param data The data to update.
     */
    override suspend fun updateData(data: Data) {
        Firebase.firestore
            .collection(DATA_COLLECTION)
            .document(data.dataId)
            .set(data)
            .await()
    }

    companion object {
        private const val USER_ID_FIELD = "userId"
        private const val DATA_COLLECTION = "datasets"
    }
}