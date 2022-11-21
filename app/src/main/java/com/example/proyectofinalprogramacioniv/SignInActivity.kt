package com.example.proyectofinalprogramacioniv

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinalprogramacioniv.Domain.Authentication
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var auth: FirebaseAuth
    private lateinit var loginBtn: Button
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_main)

        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics

        // Initialize Firebase Auth
        //auth = Firebase.auth

        loginBtn = findViewById(R.id.loginbtn)
        emailEdt = findViewById(R.id.username)
        passwordEdt = findViewById(R.id.password)

        //emailEdt.setText(auth.toString())
        IniciarSesion()
    }

    fun LogIn() {

        var oAuthentication = Authentication()
    }


    fun btnSigResgiter(Vistas: View) {
        val ventanaSiguiente: Intent = Intent(applicationContext, SignUpActivity::class.java)
        startActivity(ventanaSiguiente)
    }

    fun home(Vistas: View) {

        val ventanaSiguiente: Intent = Intent(applicationContext, DesignHome::class.java)
        startActivity(ventanaSiguiente)
    }



    fun IniciarSesion() {

    loginBtn.setOnClickListener {

            var e = emailEdt.text.toString()
            var p = passwordEdt.text.toString()

            if (e.isNotEmpty() && p.isNotEmpty()) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    e,
                    p
                ).addOnCompleteListener {

                    if (it.isSuccessful) {

                        NextDesing()


                    } else {

                        AlertS()
                    }
                }

            } else {
                Toast.makeText(
                    applicationContext,
                    "Debes rellenar los campos", Toast.LENGTH_SHORT
                )
            }
        }

    }

    private fun NextDesing() {
        val DesingIntent: Intent = Intent(this, DesignHome::class.java)
        startActivity(DesingIntent)

    }

    private fun AlertS() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se a pruducido un error")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}