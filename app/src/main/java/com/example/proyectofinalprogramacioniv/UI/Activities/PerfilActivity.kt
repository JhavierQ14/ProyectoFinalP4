package com.example.proyectofinalprogramacioniv.UI.Activities

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.proyectofinalprogramacioniv.Data.Entities.Questions
import com.example.proyectofinalprogramacioniv.Data.Repository.QuestionsRepository
import com.example.proyectofinalprogramacioniv.Data.Services.QuestionsService
import com.example.proyectofinalprogramacioniv.databinding.ActivityPerfilBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class PerfilActivity : AppCompatActivity() {

    //binding
    private lateinit var _binding: ActivityPerfilBinding

    //
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Implentacion de binding para conectar con vistas o elementos
        _binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        //
        GetInfoUser()
        //getI()
    }

    private fun GetInfoUser(){

        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid

            //emailUserEdt.setText(email)
            _binding.tvemailUser.setText(email)

            db.collection("Users").document(email.toString()).get().addOnSuccessListener {

                var n = it.get("nameUser"+ " " + "lastNameUser") as String?
                var nn = ""
                _binding.tvNameUser.setText(n)
                //nameUserTv.setText(it.get("nameUser"+ " " + "lastNameUser") as String?)
                _binding.edtNombreUser.setText(it.get("nameUser")as String?)
                //NameUserEdt.setText(it.get("nameUser")as String?)
                _binding.edtLastNameUser.setText(it.get("lastNameUser") as String?)
                //LastNameUserEdt.setText(it.get("lastNameUser") as String?)

            }

        }
    }

    /*fun getI(){

        var collection = mutableListOf<Questions>()

        db.collection("Questions")
            .get()
            .addOnSuccessListener { result ->

                for ( list in result){
                    Log.d("Preguntas", "$collection")
                    val listQ: Questions = list.toObject(Questions::class.java)
                    collection.add(listQ)
                }
            }.addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }

        //Log.d("Preguntas", "$collection")
    }*/

    /*db.collection("Users").document(email.toString()).get().addOnSuccessListener {

                var n = it.get("nameUser"+ " " + "lastNameUser") as String?
                var nn = ""
                _binding.tvNameUser.setText(n)
                //nameUserTv.setText(it.get("nameUser"+ " " + "lastNameUser") as String?)
                _binding.edtNombreUser.setText(it.get("nameUser")as String?)
                //NameUserEdt.setText(it.get("nameUser")as String?)
                _binding.edtLastNameUser.setText(it.get("lastNameUser") as String?)
                //LastNameUserEdt.setText(it.get("lastNameUser") as String?)

            }*/

}