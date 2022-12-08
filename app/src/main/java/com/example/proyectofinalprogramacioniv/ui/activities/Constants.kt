package com.example.proyectofinalprogramacioniv.ui.activities

import com.example.proyectofinalprogramacioniv.R

object Constants {

    fun RandomNumber(): Int{return (0..9).random()}

    fun getQuestionTeorico(): ArrayList<ModelQuestionTeorico>{

        var listQuestions = arrayListOf<ModelQuestionTeorico>()

        val que1 = ModelQuestionTeorico( 1,"Que significa la siguiente senal de transito?",
        R.drawable.devolver,
            "Devolverse",
            "Zona de Juegos",
            "Ciclista en la via",
            "Cruce de ferrocarril",
            1
        )

        listQuestions.add(que1)

        val que2 = ModelQuestionTeorico( 1,"¿Por cuántos años se otorga la concesión para prestar el servicio de Transporte Colectivo de pasajeros, Escolar y especiales?",
           0,
            "2 años",
            "10 años",
            "prorrogables 5 años",
            "1 año prorrogables",
            2
        )
        listQuestions.add(que2)


        val que3 = ModelQuestionTeorico( 1,"Las infracciones de tránsito y seguridad vial se clasifican en:",
            0,
            "Leves, graves y muy graves",
            "Leves, suaves y peligrosas",
            "Justas, injustas y aceptables",
            "Muy graves, semi-graves y simples",
            4
        )
        listQuestions.add(que3)

        val que4 = ModelQuestionTeorico( 1," La señales pintadas sobre el pavimento tal como se muestra en la gráfica, son llamadas.?",
            R.drawable.horizontal,
            "Señales horizontales",
            " Señales verticales",
            "Señales en diagonal",
            "Señales de paso e intersecciones",
            1
        )
        listQuestions.add(que4)

        val que5 = ModelQuestionTeorico( 1,"Cual es el significado de esta señal?",
            R.drawable.stop,
            " Para y luego seguir",
            "Detener por completo la marcha y ceder el paso ",
            "Solo dejar pasar a los vehículos con sirena encendida",
            "Retroceder y seguir recto",
            2
        )
        listQuestions.add(que5)


        val que6 = ModelQuestionTeorico( 1,"Cual es el significado de esta señal?",
            R.drawable.cedaelpaso,
            " Que sustituya a la señal de alto",
            "Que usted debe observar en ambas direcciones de la vía a la cual se incorporara y permitir la circulación de los vehículos que ya estén en éstas ",
            "Pitar y acelerar para poder pasar una intersección",
            "Solo dejar pasar vehículos grandes",
            2
        )
        listQuestions.add(que6)


        val que7 = ModelQuestionTeorico( 1,"Cual es el nombre de esta señal?",
            R.drawable.question7,
            " Prohibido la vuelta cerrada",
            "Prohibido girar a la derecha ",
            "Prohibido regresarse",
            "Prohibido salir por la intersección",
            2
        )
        listQuestions.add(que7)

        val que8 = ModelQuestionTeorico( 1," Cual es el nombre de esta señal?",
            R.drawable.nopeatones,
            " No pasar con el semáforo en rojo",
            "No bloquear el paso de vehículos",
            "Prohibida la circulación de pasajeros rápidamente",
            "No peatones ",
            4
        )
        listQuestions.add(que8)

        val que9 = ModelQuestionTeorico( 1," Se prohíbe la maniobra de marcha hacia atrás:",
            0,
            " En caminos vecinales",
            "En centros comerciales",
            "Estacionamientos públicos",
            " La red vial urbana principal",
            1
        )
        listQuestions.add(que9)

        val que10 = ModelQuestionTeorico( 1,"Las personas Naturales o Jurídicas que se dediquen a la venta de vehículos, que clase de seguro deben de tomar:",
           0,
            " Póliza global",
            " Seguro temporal ",
            " Seguro por cada vehículo",
            "No deben asegurarlos",
            1
        )
        listQuestions.add(que10)

        var ramdomList = arrayListOf<ModelQuestionTeorico>()
        var position: Int = RandomNumber()

        for (i in 1..9){

            var position: Int = RandomNumber()
            var list = listQuestions[position]
            ramdomList.add(list)
        }



        return  ramdomList

    }

}