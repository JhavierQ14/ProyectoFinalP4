package com.example.proyectofinalprogramacioniv.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinalprogramacioniv.R
import com.example.proyectofinalprogramacioniv.databinding.ActivityResultadoBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ResultadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoBinding

    private var db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        GetInfoUser()




    }

    private fun GetInfoUser(){

        val user = Firebase.auth.currentUser
        user?.let {

            val email = user.email

            db.collection("Users").document(email.toString()).get().addOnSuccessListener {

                var n = it.get("nameUser"+ " " + "lastNameUser") as String?
                binding.tvName.setText(n)

            }

        }
    }
}