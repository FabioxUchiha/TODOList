package com.electiva1.todolist

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ArrayAdapter
import android.widget.ListView
import android.view.View
import android.widget.AdapterView
import java.io.PrintStream
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private var tareas: ArrayList<String>? = null
    private var adaptador1: ArrayAdapter<String>? = null
    private var lv1: ListView? = null
    private var et1: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tareas = ArrayList()
        adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tareas!!)
        lv1 = findViewById(R.id.listView) as ListView
        lv1!!.adapter = adaptador1

        et1 = findViewById(R.id.editText) as EditText

        lv1!!.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, i, l ->
            val dialogo1 = AlertDialog.Builder(this@MainActivity)
            dialogo1.setTitle("Importante")
            dialogo1.setMessage("¿ Elimina esta tarea?")
            dialogo1.setCancelable(false)
            dialogo1.setPositiveButton("Confirmar") { dialogo1, id ->
                tareas!!.removeAt(i)
                adaptador1!!.notifyDataSetChanged()

            }
            dialogo1.setNegativeButton("Cancelar") { dialogo1, id -> }
            dialogo1.show()

            false
        }

        leerArchivo()

    }

    fun leerArchivo(){
        val scan = Scanner(
            getResources().openRawResource(R.raw.tareas))
        var allText = ""
        while (scan.hasNextLine()) {
            val line = scan.nextLine()
            allText += line
            tareas!!.add(allText)
        }
        scan.close()
        adaptador1!!.notifyDataSetChanged()
    }


    fun agregar(v: View) {
        tareas!!.add(et1!!.text.toString())
        adaptador1!!.notifyDataSetChanged()
        et1!!.setText("")

        val output = PrintStream(openFileOutput("tareas.txt", MODE_PRIVATE))

        val scan = Scanner(openFileInput("tareas.txt"))
        var allText = ""
        while (scan.hasNextLine()) {
            val line = scan.nextLine()
            allText += line
        }
        tareas!!.add(allText)
        scan.close()
        adaptador1!!.notifyDataSetChanged()
    }


}
