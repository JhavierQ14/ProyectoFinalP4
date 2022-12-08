package com.example.proyectofinalprogramacioniv.core

import com.example.proyectofinalprogramacioniv.data.datasources.FirestoreNetwork
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUserStatics constructor(

    private var oFirebaseConnectionDS: FirestoreNetwork
) {

    companion object {

        var emailUser: String? = ""
        var nameUser: String? = ""
        var lastNameUser: String? = ""
        var avatarUser: String? = ""

        fun GetDataUser() {

            val user = Firebase.auth.currentUser
            user?.let {

                val name = user.displayName
                val email = user.email
                val photoUrl = user.photoUrl

                //emailUserEdt.setText(email)
                if (email != null) {
                    emailUser = email


                    val oFirebaseConnectionDS = FirestoreNetwork
                    oFirebaseConnectionDS.ProviderFirebaseFirestore().collection("Users")
                        .document(email.toString()).get().addOnSuccessListener {

                            nameUser = it.get("nameUser") as String?
                            lastNameUser = it.get("lastNameUser") as String?
                            avatarUser = it.get("avatarUser") as String?
                        }
                }
            }
        }
    }
}