package com.example.proyectofinalprogramacioniv.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.EditText
import android.widget.TextView
import com.example.proyectofinalprogramacioniv.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class PerfilActivity : AppCompatActivity() {


    private lateinit var nameUserTv: TextView
    private lateinit var  EmailUserTV: TextView
    private lateinit var emailUserEdt: EditText
    private  lateinit var  NameUserEdt: EditText
    private  lateinit var  LastNameUserEdt: EditText

    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        emailUserEdt = findViewById(R.id.edtEmailUser)
        nameUserTv = findViewById(R.id.tvNameUser)
        EmailUserTV = findViewById(R.id.tvemailUser)
        NameUserEdt = findViewById(R.id.edtNombreUser)
        LastNameUserEdt = findViewById(R.id.edtLastNameUser)



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

            emailUserEdt.setText(email)

            db.collection("Users").document(email.toString()).get().addOnSuccessListener{
                nameUserTv.setText(it.get("nameUser"+ " " + "lastNameUser") as String?)
                NameUserEdt.setText(it.get("nameUser")as String?)
                LastNameUserEdt.setText(it.get("lastNameUser") as String?)


            }


        }
    }

    fun GetInfo(){

        var email = emailUserEdt.text.toString()
        var name = nameUserTv.text.toString()




    }
}