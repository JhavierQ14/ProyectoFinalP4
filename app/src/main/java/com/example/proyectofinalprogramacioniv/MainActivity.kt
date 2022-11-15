package com.example.proyectofinalprogramacioniv

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var Entrar: Button
    private lateinit var NombreDeUsuarioEdt: EditText
    private lateinit var PassEdt: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Entrar = findViewById(R.id.loginbtn)
        NombreDeUsuarioEdt = findViewById(R.id.username)
        PassEdt = findViewById(R.id.password)
        IniciarSesion()

    }

    fun btnSigResgiter(Vistas: View){
        val ventanaSiguiente: Intent = Intent(applicationContext,RegistrarseActivity::class.java)
        startActivity(ventanaSiguiente)
    }

    fun loginbtn(Vistas: View){

        val ventanaSiguiente: Intent = Intent(applicationContext,DesignHome::class.java)
        startActivity(ventanaSiguiente)
    }

    fun btnMyPerfil(Vistas: View){

        val ventanaSiguiente: Intent = Intent(applicationContext,user_perfil::class.java)
        startActivity(ventanaSiguiente)
    }

    fun IniciarSesion(){
        Entrar.setOnClickListener {


            if (NombreDeUsuarioEdt.text.isNotEmpty() && PassEdt.text.isNotEmpty()){

                FirebaseAuth.getInstance().signInWithEmailAndPassword(NombreDeUsuarioEdt.text.toString(),PassEdt.text.toString()).addOnCompleteListener{

                    if (it.isSuccessful){

                        NextDesing()


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
    private fun NextDesing(){
        val DesingIntent:Intent = Intent(this,DesignHome::class.java)
     startActivity(DesingIntent)

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