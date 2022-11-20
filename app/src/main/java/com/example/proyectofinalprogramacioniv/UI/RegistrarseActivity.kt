package com.example.proyectofinalprogramacioniv.UI

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.proyectofinalprogramacioniv.R
import com.google.firebase.auth.FirebaseAuth

class RegistrarseActivity : AppCompatActivity() {
    private lateinit var Correotxt : TextView
    private lateinit var NombreUsuarioRigister: TextView
    private lateinit var PasswordRegister: TextView
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)
        Correotxt = findViewById(R.id.Email)
        NombreUsuarioRigister = findViewById(R.id.nombreDeUsuario)
        PasswordRegister = findViewById(R.id.Pass)
        btnRegister = findViewById(R.id.Regitrarbtn)

    }
    fun IniciarSesion(){
        btnRegister.setOnClickListener {


            if (NombreUsuarioRigister.text.isNotEmpty() && PasswordRegister.text.isNotEmpty()){

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(NombreUsuarioRigister.text.toString(),PasswordRegister.text.toString()).addOnCompleteListener{

                    if (it.isSuccessful){

                        NextLogin()


                    } else{

                        AlertS()
                    }
                }
            }
            else{
                Toast.makeText(getApplicationContext(),
                    "Debes rellenar los campos", Toast.LENGTH_SHORT);
            }
        }

    }
    private fun NextLogin(){
        val Login:Intent = Intent(this, MainActivity::class.java)
        startActivity(Login)

    }
    private fun AlertS(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se a pruducido un error")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}