package com.example.moneyusage.backend.models.services.interf

import com.example.moneyusage.backend.models.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface StorageService {
    /**
     * Return the list of data for the current user.
     */
    val dataset: Flow<List<Data>>

    /**
     * Return the loading state of the database.
     */
    val isLoading: StateFlow<Boolean>

    /**
     * Add a new data to the database
     * @param data The data to add.
     */
    suspend fun addData(data: Data)
    /**
     * Get a data from the database
     * @param dataId The id of the data to get.
     * @return The data if it exists, null otherwise.
     */
    suspend fun getData(dataId: String): Data?

    /**
     * Delete a data from the database
     * @param dataId The data to delete.
     */
    suspend fun deleteData(dataId: String)
    /**
     * Update a data from the database
     * @param data The data to update.
     */
    suspend fun updateData(data: Data)
}