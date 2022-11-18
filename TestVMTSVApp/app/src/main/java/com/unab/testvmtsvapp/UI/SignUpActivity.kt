package com.unab.testvmtsvapp.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.unab.testvmtsvapp.Domain.ClsAuthentication
import com.unab.testvmtsvapp.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var _emailEdt: EditText
    private lateinit var _passwordEdt: EditText
    private lateinit var _signUpBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        _emailEdt = findViewById(R.id.edtEmailI)
        _passwordEdt = findViewById(R.id.edtPasswordI)
        _signUpBtn = findViewById(R.id.btnSignUp)
    }

    private fun AlertSign(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Lo sentimos se a producido un error interno")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun SignUp(V: View) {

        var oAuthentication = ClsAuthentication()

        var email: String = _emailEdt.text.toString()
        var password: String = _passwordEdt.text.toString()

        var response: Int = oAuthentication.SignUp(email, password)

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