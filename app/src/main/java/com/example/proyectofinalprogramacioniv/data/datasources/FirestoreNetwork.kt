package com.example.proyectofinalprogramacioniv.data.datasources

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
/*import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton*/

//@Module
//@InstallIn(SingletonComponent::class)
object FirestoreNetwork {

    // constante a inyectar para utilizar FirebaseAuth
    private val authenticationFirebase: FirebaseAuth by lazy { Firebase.auth }
    //@Singleton
    //@Provides
    fun ProviderFirebaseAuth():FirebaseAuth{ return authenticationFirebase }

    // constante a inyectar para utilizar FirebaseFirestore
    private val dbFirestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    //@Singleton
    //@Provides
    fun ProviderFirebaseFirestore():FirebaseFirestore{ return dbFirestore }
}