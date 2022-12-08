package com.example.proyectofinalprogramacioniv.ui.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinalprogramacioniv.domain.ClsAuthentication
import com.example.proyectofinalprogramacioniv.R
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity: AppCompatActivity() {

    //Variables Globales
    private lateinit var firebaseAnalytics: FirebaseAnalytics /* */
    private lateinit var auth: FirebaseAuth
    private lateinit var signInBtn: Button
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText
    private lateinit var signUpTv: TextView
    private lateinit var signGoogleBtn: ImageView
    //private lateinit var signFacebookBtn: ImageView
    private lateinit var btnForgetPas: TextView

    // Variable para conocer estado de inicio de secion con google
    private var go = 100

    // variable de inicio de secion de facebook
    private lateinit var callbackManager: CallbackManager

    // estados de activity *************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.splashtheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Instancia de objeto callbackManager para gestionar las respuestas del inicio de sesión.
        callbackManager = CallbackManager.Factory.create()

        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Asignacion de eventos a variables
        signInBtn = findViewById(R.id.btnSignIn)
        emailEdt = findViewById(R.id.edtEmail)
        passwordEdt = findViewById(R.id.edtPassword)
        signUpTv = findViewById(R.id.tvGoSignUp)
        signGoogleBtn = findViewById(R.id.imgSignGoogle)
        //signFacebookBtn = findViewById(R.id.imgSignFacebook)
        btnForgetPas = findViewById(R.id.forgetPass)


        //Inicializando Metodos
        SignIn()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == go) {

            val tas: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account: GoogleSignInAccount = tas.getResult(ApiException::class.java)

                if (account != null) {

                    val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {

                            if (it.isSuccessful) {

                                GoHome()

                            } else {

                                Alerts()
                            }
                        }
                }
            }catch (e: ApiException){

                Alerts()
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
    }

    // Funciones ***********************************************************************************

    fun GoSignUp(view: View) {

        val intent: Intent = Intent(applicationContext, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun GoHome() {

        val intent: Intent = Intent(applicationContext, HomeActivity::class.java)
        startActivity(intent)
    }

    //Metodo para mostrar alertas
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

    // funcion para mostrar One Tap y obtener cuenta
    fun SignInGoogle(view: View) {

      //  signGoogleBtn.setOnClickListener {

            val googleConfig: GoogleSignInOptions =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

            val googleClient: GoogleSignInClient = GoogleSignIn.getClient(this, googleConfig)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, this.go)
        //}
    }

    fun SignFacebook (view: View){

        LoginManager.getInstance().registerCallback(callbackManager,
            object: FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {

                    result?.let {
                        val tokenFacebook = it.accessToken
                        val credential: AuthCredential = FacebookAuthProvider.getCredential(tokenFacebook.token,)
                        FirebaseAuth.getInstance().signInWithCredential(credential)
                            .addOnCompleteListener {

                                if (it.isSuccessful) {

                                    GoHome()

                                } else {
                                    Alerts()
                                }
                            }
                    }
                    //GoHome()
                }

                override fun onCancel() {

                }

                override fun onError(error: FacebookException) {
                    Alerts()
                }
            })

        LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile"))
    }

    fun ForgetPass(view: View) {

        val ventanaSiguiente: Intent = Intent(this, ForgetPassActivity::class.java)
        startActivity(ventanaSiguiente)
    }
}