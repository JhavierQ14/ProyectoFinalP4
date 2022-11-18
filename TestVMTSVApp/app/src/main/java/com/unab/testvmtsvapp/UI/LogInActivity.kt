package com.unab.testvmtsvapp.UI

//import com.google.firebase.analytics.FirebaseAnalytics
//import com.google.firebase.analytics.ktx.analytics
//import com.google.firebase.ktx.Firebase
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.unab.testvmtsvapp.Domain.ClsAuthentication
import com.unab.testvmtsvapp.R

class LogInActivity : AppCompatActivity() {

    //private lateinit var firebaseAnalytics: FirebaseAnalytics

    private lateinit var _emailEdt: EditText
    private lateinit var _passwordEdt: EditText
    private lateinit var _signInBtn: Button
    private lateinit var _signUpBtn: TextView
    private lateinit var _googleSignInBtn: ImageView
    private lateinit var _facebookSignInBtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Modificacion de activity
        title = "Inicio Sesion"

        //Implementacion de FirebaseAnalytics
        //firebaseAnalytics = Firebase.analytics
        //var bundle = Bundle()
        //bundle.putString("message", "Dispositivo prueba")
        //firebaseAnalytics.logEvent("InitScreen", bundle)

        //Capturar eventos de componentes
        _emailEdt = findViewById(R.id.edtEmail)
        _passwordEdt = findViewById(R.id.edtPassword)
        _signInBtn = findViewById(R.id.btnSignIn)
        _signUpBtn = findViewById(R.id.tvSignUp)
        _googleSignInBtn = findViewById(R.id.btnGoogle)
        //facebookSignInBtn = findViewById(R.id.btnFacebook)

    }

    private fun ClearFields() {

        _emailEdt.setText("")
        _passwordEdt.setText("")
    }

    private fun SignUp() {

        val intent: Intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun AlertSign(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Lo sentimos se a producido un error interno")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun SignIn(V: View) {

        var oAuthentication = ClsAuthentication()

        var email: String = _emailEdt.text.toString()
        var password: String = _passwordEdt.text.toString()

        var response: Int = oAuthentication.SignIn(email, password)

        if (response == 0) {

            AlertSign()

        } else if(response == 1){

            val intent: Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

        } else{

            AlertSign()
        }
    }
}