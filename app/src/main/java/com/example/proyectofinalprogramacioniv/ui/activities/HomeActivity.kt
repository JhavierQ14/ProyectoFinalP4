package com.example.proyectofinalprogramacioniv.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.proyectofinalprogramacioniv.R
import com.example.proyectofinalprogramacioniv.core.ResultTestStatics
import com.example.proyectofinalprogramacioniv.core.SignUserStatics
import com.example.proyectofinalprogramacioniv.data.datasources.FirestoreNetwork
import com.example.proyectofinalprogramacioniv.data.localdb.QuestionsProvider
import com.example.proyectofinalprogramacioniv.data.repository.QuestionsRepository
import com.example.proyectofinalprogramacioniv.databinding.ActivityHomeBinding
import com.example.proyectofinalprogramacioniv.domain.ClsAuthentication
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    //
    private lateinit var binding: ActivityHomeBinding
    //
    private lateinit var builder: AlertDialog.Builder

    //
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    //
    private val connectionFirebase = FirestoreNetwork.ProviderFirebaseFirestore()

    private val oQuestioRepossitory = QuestionsRepository()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        //
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        title = "Principal"

        //
        builder = AlertDialog.Builder(this)


        //*********************************************
        var listQ = QuestionsProvider.questions

        CoroutineScope(Dispatchers.IO).launch{

            if (listQ.isEmpty()){

                //oQuestioRepossitory.GetAllQuestions()
            }
        }

        //
        var nameUser: String? = SignUserStatics.nameUser
        var lastNameUser: String? = SignUserStatics.lastNameUser
        var emailUser: String? = SignUserStatics.emailUser
        if(nameUser!!.isEmpty() && lastNameUser!!.isEmpty() && emailUser!!.isEmpty()){

            SignUserStatics.GetDataUser()
        }
        Menu()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true

        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    private fun Menu(){

        drawerLayout = binding.activityHome
        navView = binding.navMenu
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle?.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {

            when(it.itemId) {

                R.id.itemHome -> {

                    val intent: Intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                R.id.itemT -> {

                    TestTheoretical()
                }
                R.id.itemPs -> {

                    TestPsychological()
                }
                R.id.itemHis -> {

                    btnHistory()
                }
                R.id.itemPro -> {

                    MyPerfil()
                }
                R.id.itemSettings -> {

                    btnConfiguracion()
                }
                R.id.itemLogOut -> {

                    // Objeto referencia a ClsAuthentication
                    var oClsAuthentication: ClsAuthentication = ClsAuthentication()

                    builder.setTitle("Alerta!")
                        .setMessage("Quieres cerrar sesion?")
                        .setCancelable(true)

                        .setPositiveButton("Si"){dialogInterface,it ->
                            finish()
                            // Ejecucion de funcion
                            oClsAuthentication.SignOut()
                            // Ejecucion de funcion SignIn()
                            var oClsAuthentication: ClsAuthentication = ClsAuthentication()
                            oClsAuthentication.SignOut()
                            val intent: Intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)

                        }
                        .setNegativeButton("No") { dialogInterface, it ->
                            dialogInterface.cancel()

                        }.show()
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        //
        var nameUser: String? = SignUserStatics.nameUser
        var lastNameUser: String? = SignUserStatics.lastNameUser
        var nameCompleteU: String? = nameUser+ "" +lastNameUser
        var emailUser: String? = SignUserStatics.emailUser
        var nHMenu = binding.navMenu.getHeaderView(0)
        var nameU: TextView = nHMenu.findViewById(R.id.nameU)
        var emailU: TextView = nHMenu.findViewById(R.id.emU)
        nameU.setText(nameCompleteU)
        emailU.setText(emailUser)
    }

    fun TestPsychological() {

        builder.setTitle("Alerta!")
            .setMessage("Quieres entrar a Test Psicologico?, es contra reloj")
            .setCancelable(true)
            .setPositiveButton("Si") { dialogInterface, it ->
                finish()
                val ventanaSiguiente: Intent = Intent(this, PsychologicalActivity::class.java)
                startActivity(ventanaSiguiente)
            }
            .setNegativeButton("No") { dialogInterface, it ->
                dialogInterface.cancel()

            }.show()
    }

    fun TestTheoretical() {

        builder.setTitle("Alerta!")
            .setMessage("Quieres entrar a Test Teorico?, es contra reloj")
            .setCancelable(true)

            .setPositiveButton("Si") { dialogInterface, it ->
                finish()
                val ventanaSiguiente: Intent = Intent(this, TheoreticalActivity::class.java)
                startActivity(ventanaSiguiente)
            }
            .setNegativeButton("No") { dialogInterface, it ->
                dialogInterface.cancel()

            }.show()
    }

    fun MyPerfil() {

        val ventanaSiguiente: Intent = Intent(this, PerfilActivity::class.java)
        startActivity(ventanaSiguiente)
    }

    fun btnConfiguracion() {

        val ventanaSiguiente: Intent = Intent(this, ConfigActivity::class.java)
        startActivity(ventanaSiguiente)
    }

    fun btnHistory() {

        var correctAw: String = ResultTestStatics.CORRECT_ANSWERST
        var sizeQuest: String = ResultTestStatics.TOTAL_QUESTIONT

        var correctAnP: String = ResultTestStatics.CORRECT_ANSWERSP
        var sizeQuesP: String = ResultTestStatics.TOTAL_QUESTIONP

        if (correctAw.isNotEmpty() && sizeQuest.isNotEmpty() && correctAnP.isNotEmpty() && sizeQuesP.isNotEmpty()) {

            val ventanaSiguiente: Intent = Intent(this, HistoryActivity::class.java)
            startActivity(ventanaSiguiente)
        }else {
            builder.setTitle("Alerta!")
                .setMessage("Debes realizar primero los test")
                .setCancelable(true)

                .setPositiveButton("Ok") { dialogInterface, it ->


                }
                .show()
        }

    }

}

/*fun GoStudingTest(view: View) {

    val cities = connectionFirebase.collection("cities")

    val data1 = hashMapOf(
        "cod_state_test" to 1,
        "test_name" to "Teorico",
        "description_question" to "Que significa la siguiente se??al de transito?",
        "img_question" to "urlimagen",
        "answer_question" to listOf<String>("1","Devolverse","Zona de Juegos","Ciclista en la via","Cruce de ferrocarril")
    )
    cities.document("Pregunta1").set(data1)

    val data2 = hashMapOf(
        "cod_state_test" to 1,
        "test_name" to "Teorico",
        "description_question" to "??Por cu??ntos a??os se otorga la concesi??n para prestar el servicio de Transporte Colectivo de pasajeros, Escolar y especiales?",
        "img_question" to "urlimagen",
        "answer_question" to listOf<String>("2","2 a??os","10 a??os","prorrogables 5 a??os","1 a??o prorrogables")
    )
    cities.document("Pregunta2").set(data2)

    val data3 = hashMapOf(
        "cod_state_test" to 1,
        "test_name" to "Teorico",
        "description_question" to "Las infracciones de tr??nsito y seguridad vial se clasifican en:",
        "img_question" to "urlimagen",
        "answer_question" to listOf<String>("1","Leves, graves y muy graves","Leves, suaves y peligrosas","Justas, injustas y aceptables","Muy graves, semi-graves y simples")
    )
    cities.document("Pregunta3").set(data3)

    val data4 = hashMapOf(
        "cod_state_test" to 1,
        "test_name" to "Teorico",
        "description_question" to "La se??ales pintadas sobre el pavimento tal como se muestra en la gr??fica, son llamadas",
        "img_question" to "urlimagen",
        "answer_question" to listOf<String>("1","Se??ales horizontales", " Se??ales verticales", "Se??ales en diagonal", "Se??ales de paso e intersecciones")
    )
    cities.document("Pregunta4").set(data4)

    val data5 = hashMapOf(
        "cod_state_test" to 1,
        "test_name" to "Teorico",
        "description_question" to "Cual es el significado de esta se??al?",
        "img_question" to "urlimagen",
        "answer_question" to listOf<String>(
            "2",
            "Para y luego seguir",
            "Detener por completo la marcha y ceder el paso",
            "Solo dejar pasar a los veh??culos con sirena encendida",
            "Retroceder y seguir recto")
    )
    cities.document("Pregunta5").set(data5)

    val data6 = hashMapOf(
        "cod_state_test" to 1,
        "test_name" to "Teorico",
        "description_question" to "Cual es el significado de esta se??al?",
        "img_question" to "urlimagen",
        "answer_question" to listOf<String>(
            "2",
            "Que sustituya a la se??al de alto",
            "Que usted debe observar en ambas direcciones de la v??a a la cual se incorporara y permitir la circulaci??n de los veh??culos que ya est??n en ??stas ",
            "Pitar y acelerar para poder pasar una intersecci??n",
            "Solo dejar pasar veh??culos grandes")
    )
    cities.document("Pregunta6").set(data6)

    val data7 = hashMapOf(
        "cod_state_test" to 1,
        "test_name" to "Teorico",
        "description_question" to "Cual es el nombre de esta se??al?",
        "img_question" to "urlimagen",
        "answer_question" to listOf<String>("correcta","respuesta1","respuesta2","respuesta3","respuesta14")
    )
    cities.document("Pregunta7").set(data7)

    val data8 = hashMapOf(
        "cod_state_test" to 1,
        "test_name" to "Teorico",
        "description_question" to "enunciado pregunta",
        "img_question" to "urlimagen",
        "answer_question" to listOf<String>("correcta","respuesta1","respuesta2","respuesta3","respuesta14")
    )
    cities.document("Pregunta8").set(data8)

    val data9 = hashMapOf(
        "cod_state_test" to 1,
        "test_name" to "Teorico",
        "description_question" to "enunciado pregunta",
        "img_question" to "urlimagen",
        "answer_question" to listOf<String>("correcta","respuesta1","respuesta2","respuesta3","respuesta14")
    )
    cities.document("Pregunta9").set(data9)

    val data10 = hashMapOf(
        "cod_state_test" to 1,
        "test_name" to "Teorico",
        "description_question" to "enunciado pregunta",
        "img_question" to "urlimagen",
        "answer_question" to listOf<String>(
            "correcta",
            "respuesta1",
            "respuesta2",
            "respuesta3",
            "respuesta14"
        )
    )
    cities.document("Pregunta10").set(data10)

    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show()

}*/