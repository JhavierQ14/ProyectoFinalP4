package com.example.proyectofinalprogramacioniv.Domain

import com.google.firebase.auth.FirebaseAuth

class Authentication {

    fun LogIn(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        NextDesing()

                    } else {

                        //AlertS()

                    }
                }
        } else {
            //Toast.makeText(
              //  getApplicationContext(),
              //  "Debes rellenar los campos", Toast.LENGTH_SHORT
            //)
        }


    }

    private fun NextDesing() {
        //val DesingIntent: Intent = Intent(this, DesignHome::class.java)
        //startActivity(DesingIntent)

    }

    /*private fun AlertS() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se a pruducido un error")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }*/
}