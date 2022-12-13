package com.example.proyectofinalprogramacioniv.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinalprogramacioniv.core.SignUserStatics
import com.example.proyectofinalprogramacioniv.data.datasources.FirestoreNetwork
import com.example.proyectofinalprogramacioniv.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {

    //binding
    private lateinit var _binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Implentacion de binding para conectar con vistas o elementos
        _binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        var nameUser: String? = SignUserStatics.nameUser
        var lastNameUser: String? = SignUserStatics.lastNameUser
        var nameCompleteU: String? = nameUser+ "" +lastNameUser
        var emailUser: String? = SignUserStatics.emailUser
        _binding.tvNameUser.setText(nameCompleteU)
        _binding.tvemailUser.setText(emailUser)
        _binding.edtNombreUser.setText(nameUser)
        _binding.edtLastNameUser.setText(lastNameUser)

        Update()

    }
    private fun Update(){

        val oFirestoreNetwork = FirestoreNetwork.ProviderFirebaseFirestore()
        var emailUser: String? = SignUserStatics.emailUser

        _binding.btnUpdateInfoUser.setOnClickListener{

            oFirestoreNetwork.collection("Users").document(emailUser.toString()).set(

                hashMapOf("nameUser" to _binding.edtNombreUser.text.toString(),
                    "lastNameUser" to _binding.edtLastNameUser.text.toString(),
                    "avatarUser" to "https://drive.google.com/file/d/1vovHfPW6mnOH7UkBVgDMvrbzhDlXiFCf/view?usp=share_link"

                )
            )
            SignUserStatics.GetDataUser()
            Refresh()
            Toast.makeText(this,"Actualizado exitoso", Toast.LENGTH_LONG).show()
        }


    }

    private fun Refresh() {

        val intent: Intent = Intent(this, PerfilActivity::class.java)
        startActivity(intent)
    }

}