package com.example.proyectofinalprogramacioniv.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.proyectofinalprogramacioniv.R
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class TimeQuestions : AppCompatActivity() {


    lateinit var questionsList:ArrayList<ModelQuestion>
    private var index:Int = 0
    lateinit var questionModel: ModelQuestion

    private var correctAnswerCount:Int=0
    private var wrongAnswerCount:Int=0

    lateinit var countDown: TextView
    lateinit var questions: TextView
    lateinit var option1: Button
    lateinit var option2: Button
    lateinit var option3: Button
    lateinit var option4: Button

    private var backPressedTime: Long = 0
    private var backToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_questions)

        supportActionBar?.hide()

        countDown=findViewById(R.id.contador)
        questions=findViewById(R.id.questions)
        option1=findViewById(R.id.option1)
        option2=findViewById(R.id.option2)
        option3=findViewById(R.id.option3)
        option4=findViewById(R.id.option4)


      //Agregamos las preguntas en un Array de mientras ponemos la bd
        questionsList= ArrayList()
        questionsList.add(ModelQuestion("Se encuentra en un semáforo con intermitencia de luz verde para peatones: ?","Sigue la marca con precaucion","Acelera y pasa antes que ellos","Tranquilamente espera a que pase el ultimo"," Espera impacientemente","Tranquilamente espera a que pase el ultimo"))
        questionsList.add(ModelQuestion("Cuando tengo prisa: ?","Me muestro un poco correcto con los demás peatones","o presto demasiada atención a las señales","Soy mas impaciente","Insulto a otros conductores aunque no lo oigan.","Me muestro un poco correcto con los demás peatones"))
        questionsList.add(ModelQuestion("Intenta paquearse en el lugar que acaba de quedar libre, pero otro automovilista mas listo se le adelanta. ?","Atraviesa su vehículo y discute del asunto","Reacciona airadamente y toca la bocina","Busca sin mas otro paqueo","Trata de indicarle que usted. ha llegado antes y se va a paquear","Busca sin mas otro paqueo"))
        questionsList.add(ModelQuestion("Al intentar salir encuentra un vehículo paqueado en doble fila, que le impide salir durante un buen rato: ?","Acepta sus disculpas ","Ve al dueño y lo insulta","Se enoja mucho y toca la bocina","Toca la bocina para avisar y espera un poco","Toca la bocina para avisar y espera un poco"))
        questionsList.add(ModelQuestion("Deja su vehículo paqueado en una zona prohibida, al regresar se encuentra con un agente multandolo","Paga la multa y procura paquearse mejor otro día","Se niega rotundamente a pagar la multa","Intenta convencer al agente que su error fue involuntario","Le llama la atención al agente y no le hace caso","Nintendo"))



        questionsList.add(ModelQuestion("Which company is known for publishing the Mario video game?","Xbox","Nintendo","SEGA","Electronic","Nintendo"))
        questionsList.add(ModelQuestion("Which company is known for publishing the Mario video game?","Xbox","Nintendo","SEGA","Electronic","Nintendo"))
        questionsList.add(ModelQuestion("Which company is known for publishing the Mario video game?","Xbox","Nintendo","SEGA","Electronic","Nintendo"))
        questionsList.add(ModelQuestion("Which company is known for publishing the Mario video game?","Xbox","Nintendo","SEGA","Electronic","Nintendo"))
        questionsList.add(ModelQuestion("Which company is known for publishing the Mario video game?","Xbox","Nintendo","SEGA","Electronic","Nintendo"))
        questionsList.add(ModelQuestion("Which company is known for publishing the Mario video game?","Xbox","Nintendo","SEGA","Electronic","Nintendo"))
        questionsList.add(ModelQuestion("Which company is known for publishing the Mario video game?","Xbox","Nintendo","SEGA","Electronic","Nintendo"))
        questionsList.add(ModelQuestion("Which company is known for publishing the Mario video game?","Xbox","Nintendo","SEGA","Electronic","Nintendo"))
        questionsList.add(ModelQuestion("Which company is known for publishing the Mario video game?","Xbox","Nintendo","SEGA","Electronic","Nintendo"))
        questionsList.add(ModelQuestion("Which company is known for publishing the Mario video game?","Xbox","Nintendo","SEGA","Electronic","Nintendo"))
        questionsList.add(ModelQuestion("Which company is known for publishing the Mario video game?","Xbox","Nintendo","SEGA","Electronic","Nintendo"))
        questionsList.add(ModelQuestion("Which company is known for publishing the Mario video game?","Xbox","Nintendo","SEGA","Electronic","Nintendo"))
        questionsList.add(ModelQuestion("Which company is known for publishing the Mario video game?","Xbox","Nintendo","SEGA","Electronic","Nintendo"))
        questionsList.add(ModelQuestion("Which company is known for publishing the Mario video game?","Xbox","Nintendo","SEGA","Electronic","Nintendo"))



        //questionsList.shuffle()
        questionModel= questionsList[index]

        setAllQuestions()

        contador()


    }

    fun contador(){
        var duracion:Long= TimeUnit.SECONDS.toMillis(15)

        object :CountDownTimer(duracion, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                var sDuration:String= String.format(Locale.ENGLISH,
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))

                countDown.text = sDuration

            }
            override fun onFinish() {
                index++
                if (index<questionsList.size){
                    questionModel=questionsList[index]
                    setAllQuestions()
                    resetBackground()
                    enableButton()
                    contador()

                }
                else{

                    //gameResult()

                }


            }



        }.start()



    }

    private fun RespuestaCorrecta(option: Button){
        option.background=getDrawable(R.drawable.right_bg)

        correctAnswerCount++



    }
    private fun RespuestaIncorrecta(option:Button){

        option.background=resources.getDrawable(R.drawable.wrong_bg)

        wrongAnswerCount++


    }

   /* private fun gameResult(){
        var intent= Intent(this,ResultActivity::class.java)

        intent.putExtra("Correctas",correctAnswerCount.toString())
        intent.putExtra("total",questionsList.size.toString())

        startActivity(intent)
    }*/


    private fun setAllQuestions() {
        questions.text=questionModel.question
        option1.text=questionModel.option1
        option2.text=questionModel.option2
        option3.text=questionModel.option3
        option4.text=questionModel.option4
    }
    private fun enableButton(){
        option1.isClickable=true
        option2.isClickable=true
        option3.isClickable=true
        option4.isClickable=true
    }
    private fun disableButton(){
        option1.isClickable=false
        option2.isClickable=false
        option3.isClickable=false
        option4.isClickable=false
    }
    private fun resetBackground(){
        option1.background=resources.getDrawable(R.drawable.option_bg)
        option2.background=resources.getDrawable(R.drawable.option_bg)
        option3.background=resources.getDrawable(R.drawable.option_bg)
        option4.background=resources.getDrawable(R.drawable.option_bg)
    }

    fun option1Clicked(view: View){
        disableButton()
        if(questionModel.option1==questionModel.answer){
            option1.background=resources.getDrawable(R.drawable.right_bg)


            RespuestaCorrecta(option1)

        }
        else{
            RespuestaIncorrecta(option1)
        }
    }

    fun option2Clicked(view: View){
        disableButton()
        if(questionModel.option2==questionModel.answer){
            option2.background=resources.getDrawable(R.drawable.right_bg)


            RespuestaCorrecta(option2)

        }
        else{
            RespuestaIncorrecta(option2)
        }
    }

    fun option3Clicked(view: View){
        disableButton()
        if(questionModel.option3==questionModel.answer){
            option3.background=resources.getDrawable(R.drawable.right_bg)


            RespuestaCorrecta(option3)

        }
        else{
            RespuestaIncorrecta(option3)
        }
    }

    fun option4Clicked(view: View){
        disableButton()
        if(questionModel.option4==questionModel.answer){
            option4.background=resources.getDrawable(R.drawable.right_bg)


            RespuestaCorrecta(option4)

        }
        else{
            RespuestaIncorrecta(option4)
        }
    }

    override fun onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast?.cancel()
            finish()
        }
        else {
            backToast = Toast.makeText(baseContext, "Presiona dos veces para salir", Toast.LENGTH_SHORT)
            backToast?.show()
        }
        backPressedTime = System.currentTimeMillis()
    }

}