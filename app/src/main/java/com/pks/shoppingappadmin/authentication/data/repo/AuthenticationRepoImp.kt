package com.pks.shoppingappadmin.authentication.data.repo

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingappadmin.authentication.domain.repo.AuthenticationRepo
import com.pks.shoppingappadmin.authentication.presentation.UpdateUserDataState
import com.pks.shoppingappadmin.authentication.presentation.profile.ProfileScreenState
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.domain.model.UserData
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ShoppingAppRepoImp @Inject constructor(private val db:FirebaseFirestore, private val auth:FirebaseAuth):
    AuthenticationRepo {
    override fun registerUserWithEmailAndPassword(
        userData: UserData
    ): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        auth.createUserWithEmailAndPassword(userData.email!!, userData.password!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser!!.uid
                    db.collection("Users").document(uid).set(userData)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                trySend(ResultState.Success(data = "User Created"))
                            } else {
                                trySend(ResultState.Error(message = it.exception!!.message.toString()))
                            }
                        }

                } else {
//                Log.d("error",it.exception!!.message.toString())
                    trySend(ResultState.Error(message = task.exception!!.message.toString()))
                }
            }
        awaitClose {
            close()
        }
    }

    override fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {

                trySend(ResultState.Success(data = it.result.user!!.uid))
            } else {
                Log.d("error", it.exception!!.message.toString())
                trySend(ResultState.Error(message = it.exception!!.message.toString()))
            }
        }
        awaitClose {
            close()
        }
    }

    override fun getUserDataByUid(id: String): Flow<ResultState<ProfileScreenState>> = callbackFlow {
        trySend(ResultState.Loading)
        db.collection("Users").document(id).get().addOnCompleteListener {
            if (it.isSuccessful) {
                val data = it.result.toObject(UserData::class.java)
                if (data != null) {
                    Log.d("Data is loading","from repo")
                    Log.d("Data is loading",data.toString())
                    trySend(ResultState.Success(ProfileScreenState(data = data)))
                }
            } else {
                trySend(ResultState.Error(message = it.exception?.localizedMessage.toString()))
            }
        }
        awaitClose {
            close()
        }
    }

    override fun updateUserData(userData: UserData): Flow<ResultState<UpdateUserDataState>> =  callbackFlow {
        trySend(ResultState.Loading)
        val uid = auth.currentUser!!.uid
        db.collection("Users").document(uid).set(userData)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    trySend(ResultState.Success(UpdateUserDataState(isLoaded=true)))
                } else {
                    trySend(ResultState.Error(message = it.exception!!.message.toString()))
                }
            }
        awaitClose {
            close()
        }
    }

//    fun saveUserData(data:UserData):Boolean{
//
//    }

}