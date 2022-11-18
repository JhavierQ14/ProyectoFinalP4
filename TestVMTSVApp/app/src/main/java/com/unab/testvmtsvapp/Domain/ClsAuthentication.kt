package com.unab.testvmtsvapp.Domain

import com.google.firebase.auth.FirebaseAuth

class ClsAuthentication {

    fun SignIn(email: String, password: String): Int {

        //var email: String = "prueba@gmail.com"
        //var pass: String = "12345678"
        var response: Int = 0

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {

                if (it.isSuccessful) {

                    response = 1

                } else  {

                    response = 0
                }
            }

        return response
    }

    fun SignUp(email: String, password: String): Int {

        //var email: String = "prueba@gmail.com"
        //var pass: String = "12345678"
        var response: Int = 0

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {

                if (it.isSuccessful) {

                    response = 1

                } else {

                    response = 0
                }
            }

        return response
    }

}