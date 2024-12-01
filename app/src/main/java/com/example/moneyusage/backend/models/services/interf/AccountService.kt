package com.example.moneyusage.backend.models.services.interf

import com.example.moneyusage.backend.models.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    /**
     * Get the user in the current session
     */
    val currentUser: Flow<User?>

    /**
     * Get the current user id
     */
    val currentUserId: String

    /**
     * Has the user logged in
     */
    fun hasUser(): Boolean

    /**
     * Sign in with email and password
     * @param email The email of the user.
     * @param password The password of the user.
     */
    suspend fun login(email: String, password: String)
    /**
     * Sign up with email and password
     * @param email The email of the user.
     * @param password The password of the user.
     * @param displayNames The display names of the user.
     */
    suspend fun register(email: String, password: String, displayNames: List<String>)
    /**
     * Sign out the current user
     */
    suspend fun signOut()

    /**
     * Delete the current user
     */
    suspend fun deleteAccount()
}