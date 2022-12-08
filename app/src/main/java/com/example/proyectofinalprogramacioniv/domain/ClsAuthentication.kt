package com.example.proyectofinalprogramacioniv.domain

import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ClsAuthentication {

    // Variables Globales
    private lateinit var auth: FirebaseAuth
    private var _responseVSign: Int = 0
    private var _responseSignIn: Int = 0
    private var _responseSignUp: Int = 0


    private fun ResponseSignIn(responseSignIn: Int){

        this._responseSignIn = responseSignIn
    }

    fun VerificationSignIn(): Int{

        // Initialize Firebase Auth
        auth = Firebase.auth

        val currentUser = auth.currentUser
        if (currentUser != null){

            _responseVSign = 1
        }

        return _responseVSign
    }

    fun SignIn(email: String, password: String): Int{

        // Initialize Firebase Auth
        auth = Firebase.auth

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {

                if (it.isSuccessful){

                    ResponseSignIn(1)
                    //return _responseSignIn
                }
            }

        return _responseSignIn
    }

    fun SignOut(){

        Firebase.auth.signOut()
        LoginManager.getInstance().logOut()
    }

    fun GetPerfil(){

    }
}