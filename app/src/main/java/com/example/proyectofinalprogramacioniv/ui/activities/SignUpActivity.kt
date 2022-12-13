package com.example.proyectofinalprogramacioniv.ui.activities

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
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {
    private var db = FirebaseFirestore.getInstance()
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText
    private lateinit var  lastNameEdt: EditText
    private  lateinit var  nameEdt: EditText
    private lateinit var signUpBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        emailEdt = findViewById(R.id.edtEmailII)
        passwordEdt = findViewById(R.id.edtPasswordII)
        signUpBtn = findViewById(R.id.btnSignUpI)
        lastNameEdt = findViewById(R.id.edtLastNameR)
        nameEdt = findViewById(R.id.edtNameR)

        //SignUp()
    }

    fun SignUp(v: View) {

        var email = emailEdt.text.toString()
        var  password = passwordEdt.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {

                        if (it.isSuccessful) {
                        db.collection("Users").document(email).set(

                            hashMapOf("nameUser" to nameEdt.text.toString(),
                            "lastNameUser" to lastNameEdt.text.toString(),
                            "avatarUser" to "https://drive.google.com/file/d/1vovHfPW6mnOH7UkBVgDMvrbzhDlXiFCf/view?usp=share_link"

                            )
                         )
                            Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show()
                            SignIn()

                        } else {

                            AlertS()
                        }
                    }
            } else {

                Toast.makeText(this,"Campos vacios",Toast.LENGTH_LONG).show()
            }
    }

    private fun SignIn() {

        val Login: Intent = Intent(this, SignInActivity::class.java)
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

  /*  private fun getInfo(){
    db.collection("Users").document(email).get().addOnSuccessListener{
        name.setText("name") has String?()
        Apellido.setText(""Apellido) has String?()
    }

    }*/
}