package com.example.proyectofinalprogramacioniv

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinalprogramacioniv.Domain.ClsAuthentication
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {

    //Variables Globales
    private lateinit var firebaseAnalytics: FirebaseAnalytics /* */
    private lateinit var auth: FirebaseAuth
    private lateinit var signInBtn: Button
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText
    private lateinit var signUpTv: TextView
    private lateinit var signGoogleBtn: ImageView
    //private lateinit var signFacebookBtn: ImageView

    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest
    private val REQ_ONE_TAP = 2
    private var showOneTapUI = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_main)

        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Asignacion de eventos a variables Globales
        signInBtn = findViewById(R.id.btnSignIn)
        emailEdt = findViewById(R.id.edtEmail)
        passwordEdt = findViewById(R.id.edtPassword)
        signUpTv = findViewById(R.id.tvGoSignUp)
        signGoogleBtn = findViewById(R.id.imgSignGoogle)
        //signFacebookBtn = findViewById(R.id.imgSignFacebook)

        //Inicializando Metodos
        SignIn()


        oneTapClient = Identity.getSignInClient(this)
        signInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                .setSupported(true)
                .build())
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(getString(R.string.default_web_client_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            // Automatically sign in when exactly one credential is retrieved.
            .setAutoSelectEnabled(true)
            .build()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_ONE_TAP -> {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken
                    val username = credential.id
                    val password = credential.password
                    when {
                        idToken != null -> {
                            // Got an ID token from Google. Use it to authenticate
                            // with your backend.
                            Log.d(TAG, "Got ID token.")
                        }
                        password != null -> {
                            // Got a saved username and password. Use them to authenticate
                            // with your backend.
                            Log.d(TAG, "Got password.")
                        }
                        else -> {
                            // Shouldn't happen.
                            Log.d(TAG, "No ID token or password!")
                        }
                    }
                } catch (e: ApiException) {
                    // ...
                }
            }
        }

        when (requestCode) {
            REQ_ONE_TAP -> {
                try {
                    // ...
                } catch (e: ApiException) {
                    when (e.statusCode) {
                        CommonStatusCodes.CANCELED -> {
                            Log.d(TAG, "One-tap dialog was closed.")
                            // Don't re-prompt the user.
                            showOneTapUI = false
                        }
                        CommonStatusCodes.NETWORK_ERROR -> {
                            Log.d(TAG, "One-tap encountered a network error.")
                            // Try again or just ignore.
                        }
                        else -> {
                            Log.d(TAG, "Couldn't get credential from result." +
                                    " (${e.localizedMessage})")
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        // Objeto referencia a ClsAuthentication
        var oClsAuthentication: ClsAuthentication = ClsAuthentication()

        // Capturar respuesta de usuario iniciado
        var response = oClsAuthentication.VerificationSignIn()

        // Verificar respuesta
        if (response != 0){

            GoHome()
        }
        /*else{

            val intent: Intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
        }*/
    }

    // Funciones ***********************************************************************************

    fun GoSignUp(view: View) {

        val intent: Intent = Intent(applicationContext, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun GoHome() {

        val intent: Intent = Intent(applicationContext, DesignHome::class.java)
        startActivity(intent)
    }

    private fun Alerts() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se a pruducido un error")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // Metodo de verificacion de inicio de sesion
    private fun SignIn() {

        //var response = 0
        //var oClsAuthentication: ClsAuthentication = ClsAuthentication()

        signInBtn.setOnClickListener {

            var email = emailEdt.text.toString()
            var password = passwordEdt.text.toString()
            /*var response = oClsAuthentication.SignIn(email, password)

            if (response != 0){

                GoHome()

            } else{

                Alerts()
            }*/

            if (email.isNotEmpty() && password.isNotEmpty()) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    email,
                    password
                ).addOnCompleteListener {

                    if (it.isSuccessful) {

                        GoHome()

                    } else {

                        Alerts()
                    }
                }

            } else {

                Toast.makeText(
                    applicationContext,
                    "Debes rellenar los campos", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun SignInGoogle(view: View){

        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(this) { result ->
                try {
                    startIntentSenderForResult(
                        result.pendingIntent.intentSender, REQ_ONE_TAP,
                        null, 0, 0, 0, null)
                } catch (e: IntentSender.SendIntentException) {
                    Log.e(TAG, "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
            .addOnFailureListener(this) { e ->
                // No saved credentials found. Launch the One Tap sign-up flow, or
                // do nothing and continue presenting the signed-out UI.
                Log.d(TAG, e.localizedMessage)
            }
    }
}