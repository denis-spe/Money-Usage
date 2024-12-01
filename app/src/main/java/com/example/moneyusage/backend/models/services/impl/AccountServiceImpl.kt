package com.example.moneyusage.backend.models.services.impl

import com.example.moneyusage.backend.models.User
import com.example.moneyusage.backend.models.services.interf.AccountService
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor() : AccountService {
    /**
     * Get the user in the current session
     */
    override val currentUser: Flow<User?>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                trySend(auth.currentUser?.let { User(it.uid) }).isSuccess // Safely send User or null
            }
            Firebase.auth.addAuthStateListener(listener)
            awaitClose { Firebase.auth.removeAuthStateListener(listener) }
        }

    /**
     * Get the current user id
     */
    override val currentUserId: String
        get() = Firebase.auth.currentUser?.uid.orEmpty()

    /**
     * Has the user logged in
     */
    override fun hasUser(): Boolean {
        return Firebase.auth.currentUser != null
    }

    /**
     * Sign in with email and password
     * @param email The email of the user.
     * @param password The password of the user.
     */
    override suspend fun login(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password).await()
    }

    /**
     * Sign up with email and password
     * @param email The email of the user.
     * @param password The password of the user.
     * @param displayNames The display names of the user.
     */
    override suspend fun register(email: String, password: String, displayNames: List<String>) {
        // Create a new user with the provided email and password
        val authResult = Firebase.auth.createUserWithEmailAndPassword(email, password).await()

        // Retrieve the FirebaseUser from object
        val user = authResult.user ?: throw IllegalStateException("User creation failed")

        displayNames.joinToString { " " }
            .trim()
            .let {
                val profileUpdates = userProfileChangeRequest {
                    this.displayName = it
                }
                user.updateProfile(profileUpdates).await()
            }



    }

    /**
     * Sign out the current user
     */
    override suspend fun signOut() {
        Firebase.auth.signOut()
    }

    /**
     * Delete the current user
     */
    override suspend fun deleteAccount() {
        Firebase.auth.currentUser!!.delete().await()
    }
}