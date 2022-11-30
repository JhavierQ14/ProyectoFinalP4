package com.example.proyectofinalprogramacioniv.UI.Activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinalprogramacioniv.R
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private var db = FirebaseFirestore.getInstance()
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText
    private lateinit var signUpBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        emailEdt = findViewById(R.id.edtEmailII)
        passwordEdt = findViewById(R.id.edtPasswordII)
        signUpBtn = findViewById(R.id.btnSignUpI)

        //SignUp()
    }

    fun SignUp(v: View) {

        val email = emailEdt.text.toString()
        var  password = passwordEdt.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {

                        if (it.isSuccessful) {
                         db.collection("Users").document(email).set(

                            hasMapOf("name" to name,
                            "Apellido" to Apellido, 
                            "email" to email,
                            "password" to password,

                            )
                         )
                            Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show()
                            Home()

                        } else {

                            AlertS()
                        }
                    }
            } else {

                Toast.makeText(this,"Campos vacios",Toast.LENGTH_LONG).show()
            }
    }

    private fun Home() {
        val Login: Intent = Intent(this, HomeActivity::class.java)
        startActivity(Login)

    }

    private fun AlertS() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se a pruducido un error")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun getInfo(){
    db.collection("Users").document(email).get().addOnSuccessListener{
        name.setText("name") has String?()
        Apellido.setText(""Apellido) has String?()
    }

    }
}