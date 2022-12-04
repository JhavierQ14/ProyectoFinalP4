package com.example.proyectofinalprogramacioniv.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
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

    fun Loquesea(v: View){

       /* val collectionQuestion = db.collection("Questions")

        val data1 = hashMapOf(
            "cod_state_test" to 1,
            "test_name" to "Teorico",
            "img_question" to "urlimg",
            "description_question" to "En las vías de doble sentido de circulación y tres carriles separados por marcas longitudinales discontinuas, debemos circular por el carril:",
            "answer_question" to listOf("No esta reglamentado",
                "No esta reglamentado",
                "Por el centro de la vía",
                "Por el carril izquierdo",
                "Por el carril derecho",
                "2")
        )
        collectionQuestion.document().set(data1)

        val data2 = hashMapOf(
            "cod_state_test" to 1,
            "test_name" to "Teorico",
            "img_question" to "urlimg",
            "description_question" to "Cuál es el color utilizado en las señales informativas",
            "answer_question" to listOf("west_coast",
                "Rojas con blanco",
                "violeta Azul",
                "blanco Amarillas con negro Café",
                "azul y verde",
                "2")
        )
        collectionQuestion.document().set(data2)

        val data3 = hashMapOf(
            "cod_state_test" to 1,
            "test_name" to "Teorico",
            "img_question" to "urlimg",
            "description_question" to "Uno de los requisitos que deben tener los talleres que realizan la revisión mecánica es:",
            "answer_question" to listOf("Deben ser talleres del Gobierno",
                "Deben dedicarse solo a estas revisiones",
                "Poseer equipo técnico adecuado para efectuar dichas revisiones",
                "Deben estar fuera de la capital",
                "Deben ser talleres del Gobierno",
                "1")
        )
        collectionQuestion.document().set(data3)*/
    }

}