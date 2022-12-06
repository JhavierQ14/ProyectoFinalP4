package com.example.proyectofinalprogramacioniv.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinalprogramacioniv.core.SignUserStatics
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

    }

}